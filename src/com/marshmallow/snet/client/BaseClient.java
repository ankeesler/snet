package com.marshmallow.snet.client;

import java.io.IOException;

import com.marshmallow.snet.service.IService;
import com.marshmallow.snet.service.protobuf.ClientType;
import com.marshmallow.snet.service.protobuf.InfoRequest;
import com.marshmallow.snet.service.protobuf.InfoResponse;
import com.marshmallow.snet.service.protobuf.InitRequest;
import com.marshmallow.snet.service.protobuf.Packet;
import com.marshmallow.snet.service.protobuf.ResetRequest;
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
  public boolean init(ClientType clientType) {
    return (this.stub.init(InitRequest
                           .newBuilder()
                           .setType(clientType)
                           .setAddress(this.address)
                           .build())
                     .getStatus()
            == Status.SUCCESS);
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
            == Status.SUCCESS);
  }

  @Override
  public Packet rx() {
    RxResponse rxResponse
      = this.stub.rx(RxRequest
                     .newBuilder()
                     .setAddress(this.address)
                     .build());
    return (rxResponse.getStatus() == Status.SUCCESS
            ? rxResponse.getPacket()
            : null);
  }

  @Override
  public InfoResponse info() {
    InfoResponse response
      = this.stub.info(InfoRequest
                       .newBuilder()
                       .setSource(this.address)
                       .build());
    return (response.getStatus() == Status.SUCCESS ? response : null);
  }

  @Override
  public boolean reset() {
    return (this.stub.reset(ResetRequest
                            .newBuilder()
                            .setAddress(this.address)
                            .build())
                      .getStatus()
            == Status.SUCCESS);
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
