package com.marshmallow.snet.service;

import java.io.IOException;
import java.net.InetAddress;

import com.marshmallow.snet.handler.GrpcAdminServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcBaseService implements IService {

  public GrpcBaseService(int port) throws IOException {
    Server server = ServerBuilder.forPort(port)
                                 .addService(new GrpcAdminServiceImpl())
                                 .build()
                                 .start();
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        server.shutdown();
      }
    });
  }

  @Override
  public void addMessageHandler(IMessageHandler handler) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public InetAddress getAddress() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getPort() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getClientCount() {
    // TODO Auto-generated method stub
    return 0;
  }

}
