package com.marshmallow.snet.client;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.marshmallow.snet.core.Log;
import com.marshmallow.snet.service.IService;
import com.marshmallow.snet.service.protobuf.Packet;
import com.marshmallow.snet.service.protobuf.RxConfig;
import com.marshmallow.snet.service.protobuf.SnetServiceGrpc;
import com.marshmallow.snet.service.protobuf.Status;
import com.marshmallow.snet.service.protobuf.TxConfig;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;

public class BaseClient implements IClient {
  private final int address;
  private final SnetServiceGrpc.SnetServiceBlockingStub txStub;

  private volatile Queue<Packet> rxQueue = new ConcurrentLinkedQueue<Packet>();

  public BaseClient(int address, final IService service)
      throws IOException {
    this.address = address;
    ManagedChannel channel
      = NettyChannelBuilder.forAddress(service.getAddress().getHostAddress(),
                                       service.getPort())
                           .usePlaintext(true)
                           .build();
    this.txStub = SnetServiceGrpc.newBlockingStub(channel);

    SnetServiceGrpc.SnetServiceStub rxStub = SnetServiceGrpc.newStub(channel);
    RxConfig rxConfig = RxConfig.newBuilder().setAddress(address).build();
    rxStub.rx(rxConfig, new StreamObserver<Packet>() {
      @Override
      public void onNext(Packet value) {
        rxQueue.add(value);
      }

      @Override
      public void onError(Throwable t) {
        Log.instance().note(BaseClient.class, "Client " + BaseClient.this.address + " rx error: " + t.getMessage());
        rxQueue.clear();
      }

      @Override
      public void onCompleted() {
        rxQueue.clear();
      }
    });
  }

  @Override
  public String getName() {
    return BaseClient.class.getCanonicalName() + "-" + this.address;
  }

  @Override
  public boolean tx(final Packet packet) {
    TxConfig txConfig = TxConfig.newBuilder().setPacket(packet).build();
    Status status = this.txStub.tx(txConfig);
    return (status.getId() == Status.Id.SUCCESS);
  }

  @Override
  public Packet rx() {
    return this.rxQueue.poll();
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
