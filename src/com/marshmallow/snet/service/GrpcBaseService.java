package com.marshmallow.snet.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.marshmallow.snet.handler.GrpcAdminServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcBaseService implements IService {

  private final Server server;

  public GrpcBaseService(int port) throws IOException {
    this.server = ServerBuilder.forPort(port)
                               .addService(new GrpcAdminServiceImpl())
                               .build()
                               .start();
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        GrpcBaseService.this.server.shutdown();
      }
    });
  }

  @Override
  public void addMessageHandler(IMessageHandler handler) {
    throw new UnsupportedOperationException();
  }

  @Override
  public InetAddress getAddress() {
    try {
      return InetAddress.getLocalHost();
    } catch (UnknownHostException uhe) {
      return InetAddress.getLoopbackAddress();
    }
  }

  @Override
  public int getPort() {
    return this.server.getPort();
  }

  @Override
  public int getClientCount() {
    throw new UnsupportedOperationException();
  }
}
