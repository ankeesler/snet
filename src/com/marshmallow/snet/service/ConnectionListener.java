package com.marshmallow.snet.service;

import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener implements Runnable {
  public static interface IHandler {
    public void connectionEstablished(final Socket socket);
    public void connectionException(final Exception exception);
  }

  private static int THREAD_ID = 1;

  private final ServerSocket socket;
  private final IHandler handler;
  private final Thread thread;
  private volatile boolean spin;

  public ConnectionListener(final ServerSocket socket, final IHandler handler) {
    this.socket = socket;
    this.handler = handler;
    this.thread = new Thread(this, this.getClass().getCanonicalName() + ":Thread-" + THREAD_ID++);

    this.spin = true;
    this.thread.start();    
  }

  @Override
  public void run() {
    while (this.spin) {
      try {
        Socket connection = this.socket.accept();
        this.handler.connectionEstablished(connection);
      } catch (Exception e) {
        this.handler.connectionException(e);
      }
    }
  }

}
