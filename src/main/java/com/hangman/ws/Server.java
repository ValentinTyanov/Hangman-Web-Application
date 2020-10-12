// package com.hangman.ws;
//
// import com.hangman.RankingPortType;
// import javax.xml.ws.Endpoint;
// import org.apache.cxf.ext.logging.LoggingFeature;
//
// public class Server {
//
//  public static void main(String[] args) throws Exception {
//    RankingPortType implementor = new RankingWsImpl();
//    Endpoint.publish("http://localhost:8080/RankingServerPort", implementor, new
// LoggingFeature());
//    System.out.println("Server ready...");
//    Thread.sleep(5 * 60 * 1000);
//    System.out.println("Server exiting");
//    System.exit(0);
//  }
// }
