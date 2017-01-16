package com.marshmallow.snet.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.marshmallow.snet.core.Log;

public class BaseClient implements IClient {
  private final String name;
  private final Socket socket;
  private final PrintWriter toService;
  private final LineNumberReader fromService;

  public BaseClient(final String name,
                    final InetAddress serviceAddress,
                    final int servicePort)
      throws IOException {
    this.name = name;
    this.socket = new Socket(serviceAddress, servicePort);
    this.toService = new PrintWriter(this.socket.getOutputStream());
    this.fromService = new LineNumberReader(new InputStreamReader(this.socket.getInputStream()));
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean send(final String data) {
    boolean error;
    this.toService.println(data);
    error = this.toService.checkError();
    log(this.name + " sent data: " + data);
    return !error;
  }

  @Override
  public String receive() throws IOException {
    String data = (this.fromService.ready() ? this.fromService.readLine() : null);
    if (data != null) {
      log(this.name + " received data: " + data);
    }
    return data;
  }

  @Override
  public String toString() {
    return this.name + ": " + this.socket;
  }

  private static void log(final String message) {
    Log.instance().note(BaseClient.class, message);
  }
}
