package edu.cmu.cs.mvelezce.lc.hotspot.diff.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.vs.VSViewer;
import org.json.JSONObject;

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
    String config1 = json.getString("config1");
    String config2 = json.getString("config2");
    System.out.println(
        "Creating hotspot diff for : " + programName + " - " + config1 + " - " + config2);
    VSViewer vsViewer = new VSViewer(programName, config1, config2);
    vsViewer.analyze();
    System.out.println("Completed hotspot diff");

    StringBuilder contentBuilder = new StringBuilder();
    try (Stream<String> stream =
        Files.lines(Paths.get(VSViewer.HOTSPOT_DIFF_FILE), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    byte[] response = contentBuilder.toString().getBytes();
    //    byte[] response = "{option: \"wait\"}".getBytes();
    httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
    httpExchange.getResponseBody().write(response);
    httpExchange.close();
  }
}
