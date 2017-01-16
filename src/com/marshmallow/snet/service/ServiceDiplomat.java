package com.marshmallow.snet.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.marshmallow.snet.client.BaseClient;
import com.marshmallow.snet.core.Log;

public class ServiceDiplomat implements IServiceDiplomat {

  private final Socket socket;
  private final PrintWriter toService;
  private final LineNumberReader fromService;

  public ServiceDiplomat(final IService service) throws IOException {
    this.socket = new Socket(service.getAddress(), service.getPort());
    this.toService = new PrintWriter(this.socket.getOutputStream());
    this.fromService = new LineNumberReader(new InputStreamReader(this.socket.getInputStream()));
  }

  @Override
  public boolean send(String data) {
    this.toService.println(data);
    boolean error = this.toService.checkError();
    log("Sent data: " + data);
    return !error;
  }

  @Override
  public String receive() throws IOException {
    String data = (this.fromService.ready() ? this.fromService.readLine() : null);
    if (data != null) {
      log("Seceived data: " + data);
    }
    return data;
  }

  private static void log(final String message) {
    Log.instance().note(BaseClient.class, message);
  }
}  
