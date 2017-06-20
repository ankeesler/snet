package com.marshmallow.snet.client;

import java.io.IOException;

import com.marshmallow.snet.service.IService;
import com.marshmallow.snet.service.protobuf.InitRequest;
import com.marshmallow.snet.service.protobuf.Packet;
import com.marshmallow.snet.service.protobuf.RxRequest;
import com.marshmallow.snet.service.protobuf.RxResponse;
import com.marshmallow.snet.service.protobuf.SnetServiceGrpc;
import com.marshmallow.snet.service.protobuf.Status;
import com.marshmallow.snet.service.protobuf.TxRequest;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

public class BaseClient implements IClient {
  private final int address;
  private final SnetServiceGrpc.SnetServiceBlockingStub stub;

  public BaseClient(int address, final IService service)
      throws IOException {
    this.address = address;
    ManagedChannel channel
      = NettyChannelBuilder.forAddress(service.getAddress().getHostAddress(),
                                       service.getPort())
                           .usePlaintext(true)
                           .build();
    this.stub = SnetServiceGrpc.newBlockingStub(channel);
  }

  @Override
  public boolean init() {
    return (this.stub.init(InitRequest
                           .newBuilder()
                           .setAddress(this.address)
                           .build())
                     .getStatus()
                     .getId()
            == Status.Id.SUCCESS);
  }

  @Override
  public String getName() {
    return BaseClient.class.getCanonicalName() + "-" + this.address;
  }

  @Override
  public boolean tx(final Packet packet) {
    return (this.stub.tx(TxRequest
                         .newBuilder()
                         .setPacket(packet)
                         .build())
                     .getStatus()
                     .getId()
            == Status.Id.SUCCESS);
  }

  @Override
  public Packet rx() {
    RxResponse rxResponse
      = this.stub.rx(RxRequest
                     .newBuilder()
                     .setAddress(this.address)
                     .build());
    return (rxResponse.getStatus().getId() == Status.Id.SUCCESS
            ? rxResponse.getPacket()
            : null);
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
