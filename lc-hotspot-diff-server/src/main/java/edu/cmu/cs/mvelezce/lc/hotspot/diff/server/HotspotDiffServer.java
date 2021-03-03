package edu.cmu.cs.mvelezce.lc.hotspot.diff.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HotspotDiffServer {

  public static final String HOSTNAME = "localhost";
  public static final int PORT = 8001;

  public static void main(String[] args) throws IOException {
    System.out.println(
        "Started "
            + HotspotDiffServer.class.getSimpleName()
            + " on hostname: "
            + HOSTNAME
            + " and port: "
            + PORT);
    HttpServer server = HttpServer.create(new InetSocketAddress(HOSTNAME, PORT), 0);
    server.createContext("/diff", new HotspotDiffHandler());
    server.setExecutor(Executors.newFixedThreadPool(1));
    server.start();
  }
}
