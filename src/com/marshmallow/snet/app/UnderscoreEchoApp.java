package com.marshmallow.snet.app;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.marshmallow.snet.handler.EchoHandler;
import com.marshmallow.snet.service.BaseService;

public class UnderscoreEchoApp {

  public static final String USAGE = "usage: ServiceApp <ip-address> <port>";

  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println(USAGE);
      System.exit(-1);
    }

    InetAddress address = null;
    try {
      address = InetAddress.getByName(args[0]);
    } catch (UnknownHostException uhe) {
      failure(uhe);
    }
    assert address != null;

    int port = Integer.parseInt(args[1]);

    try {
      new BaseService(address, port).addMessageHandler(new EchoHandler());
    } catch (IOException ioe) {
      failure(ioe);
    }
  }

  private static void failure(Throwable throwable) {
    System.err.println("FAILURE: " + throwable.getMessage());
    System.exit(1);
  }
}
