package com.marshmallow.snet.handler;

import com.marshmallow.snet.service.protobuf.SnetServiceGrpc;
import com.marshmallow.snet.service.protobuf.Status;
import com.marshmallow.snet.service.protobuf.RxConfig;
import com.marshmallow.snet.service.protobuf.TxConfig;

import java.util.HashMap;
import java.util.Map;

import com.marshmallow.snet.core.Log;
import com.marshmallow.snet.service.protobuf.EchoRequest;
import com.marshmallow.snet.service.protobuf.EchoResponse;
import com.marshmallow.snet.service.protobuf.InfoRequest;
import com.marshmallow.snet.service.protobuf.InfoResponse;
import com.marshmallow.snet.service.protobuf.Packet;

import io.grpc.stub.StreamObserver;

public class GrpcSnetServiceImpl extends SnetServiceGrpc.SnetServiceImplBase {

  private Map<Integer, StreamObserver<Packet>> rxStreams
    = new HashMap<Integer, StreamObserver<Packet>>();
  
  @Override
  public void echo(EchoRequest request, StreamObserver<EchoResponse> responseObserver) {
    responseObserver.onNext(EchoResponse.newBuilder().setMessage(request.getMessage()).build());
    responseObserver.onCompleted();
  }

  @Override
  public void info(InfoRequest request, StreamObserver<InfoResponse> responseObserver) {
    InfoResponse response = InfoResponse.newBuilder().setRxCount(rxStreams.size()).build();
    Log.instance().note(this.getClass(), "info(...) -> " + response);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void tx(TxConfig txConfig, StreamObserver<Status> statusObserver) {
    Log.instance().note(this.getClass(), "tx(" + txConfig.toString().trim() + ")");

    // TODO: propagation stuff here.

    // For each of the nodes listening (that aren't the node who sent the
    // message), send them a copy of the packet.
    for (Integer address : this.rxStreams.keySet()) {
      if (address != txConfig.getPacket().getSource()) {
        StreamObserver<Packet> rxStream = this.rxStreams.get(address);
        Log.instance().note(this.getClass(), "Sending message to rxStream " + rxStream + " for client " + address);
        rxStream.onNext(txConfig.getPacket());
      }
    }

    // Tell the sender that the packet was a success, and that we are done.
    statusObserver.onNext(Status.newBuilder().setId(Status.Id.SUCCESS).build());
    statusObserver.onCompleted();
  }

  @Override
  public void rx(RxConfig rxConfig, StreamObserver<Packet> packetObserver) {
    Log.instance().note(this.getClass(), "rx(" + rxConfig.toString().trim() + ")");

    // There is a node that wants to listen on an address. Remember their
    // address and the stream to use to send stuff to the node.
    this.rxStreams.put(rxConfig.getAddress(), packetObserver);
  }
}
