package com.marshmallow.snet.client;

import java.io.IOException;

import com.marshmallow.snet.service.IService;
import com.marshmallow.snet.service.protobuf.Packet;
import com.marshmallow.snet.service.protobuf.SnetServiceGrpc;
import com.marshmallow.snet.service.protobuf.Status;
import com.marshmallow.snet.service.protobuf.TxConfig;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

public class BaseClient implements IClient {
  private final String name;
  private SnetServiceGrpc.SnetServiceBlockingStub stub;

  public BaseClient(final String name, final IService service)
      throws IOException {
    this.name = name;
    ManagedChannel channel
      = NettyChannelBuilder.forAddress(service.getAddress().getHostAddress(),
                                       service.getPort())
                           .usePlaintext(true)
                           .build();
    this.stub = SnetServiceGrpc.newBlockingStub(channel);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean tx(final Packet packet) {
    TxConfig txConfig = TxConfig.newBuilder().setPacket(packet).build();
    Status status = this.stub.tx(txConfig);
    return (status.getId() == Status.Id.SUCCESS);
  }

  @Override
  public Packet rx() {
    return null;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
