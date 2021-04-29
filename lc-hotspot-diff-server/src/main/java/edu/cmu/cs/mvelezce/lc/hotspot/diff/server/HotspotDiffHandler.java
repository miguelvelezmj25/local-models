package edu.cmu.cs.mvelezce.lc.hotspot.diff.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.vs.VSViewer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class HotspotDiffHandler implements HttpHandler {

  public HotspotDiffHandler() {
    System.out.println("Started HotspotDiffHandler");
  }

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    JSONObject json = new JSONObject();
    try (InputStreamReader inputStreamReader =
        new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8)) {
      try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
        int b;
        StringBuilder buffer = new StringBuilder(512);
        while ((b = bufferedReader.read()) != -1) {
          buffer.append((char) b);
        }

        json = new JSONObject(buffer.toString());
        System.out.println("json object received: " + json);
      }
    }

    if (json.isEmpty()) {
      System.out.println("json object is empty");
      json = new JSONObject();
      json.put("option", "error");
      json.put("config1", "error");
      json.put("config2", "error");
      byte[] response = json.toString().getBytes();
      httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
      httpExchange.getResponseBody().write(response);
      httpExchange.close();
      System.out.println("responded with error data");
      return;
    }

    System.out.println("json object is not empty");
    String programName = json.getString("programName");
    String config1;
    String config2;
    if (json.has("config1") && json.has("config2")) {
      config1 = json.getString("config1");
      config2 = json.getString("config2");
    } else {
      JSONArray values = json.getJSONArray("config");
      boolean userConfig = true;
      for (int i = 0; i < values.length(); i++) {
        JSONObject entry = (JSONObject) values.get(i);
        if ((entry.getString("option").equals("DUPLICATES")
                || entry.getString("option").equals("TRANSACTIONS"))
            && entry.getString("value").equals("false")) {
          userConfig = false;
          break;
        }
      }
      if (userConfig) {
        config1 = "user";
        config2 = "user";
      } else {
        config1 = "default";
        config2 = "default";
      }
    }

    System.out.println(
        "Creating hotspot diff for : " + programName + " - " + config1 + " - " + config2);
    try {
      VSViewer vsViewer = new VSViewer(programName, config1, config2);
      vsViewer.analyze();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Completed hotspot diff");

    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream =
        Files.lines(Paths.get(VSViewer.HOTSPOT_DIFF_FILE), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    JSONObject dataToSend = new JSONObject();
    try {
      JSONParser parser = new JSONParser();
      Object res = parser.parse("[" + contentBuilder.toString() + "]");
      dataToSend.put("data", res);
    } catch (ParseException e) {
      e.printStackTrace();
      dataToSend.put("data", "error");
    }
    byte[] response = dataToSend.toString().getBytes();
    //    byte[] response = "{option: \"wait\"}".getBytes();
    httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
    httpExchange.getResponseBody().write(response);
    httpExchange.close();

    System.out.println("\n\n\n\n");
    System.gc();
  }
}
