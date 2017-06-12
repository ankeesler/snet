package com.marshmallow.snet.handler;

import com.marshmallow.snet.service.protobuf.SnetServiceGrpc;
import com.marshmallow.snet.service.protobuf.Status;
import com.marshmallow.snet.service.protobuf.RxConfig;
import com.marshmallow.snet.service.protobuf.TxConfig;

import java.util.HashMap;
import java.util.Map;

import com.marshmallow.snet.service.protobuf.EchoRequest;
import com.marshmallow.snet.service.protobuf.EchoResponse;
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
  public void tx(TxConfig txConfig, StreamObserver<Status> statusObserver) {
    // For each of the nodes listening (that aren't the node who sent the
    // message), send them a copy of the packet.
    for (Integer address : rxStreams.keySet()) {
      if (address != txConfig.getPacket().getSource()) {
        rxStreams.get(address).onNext(txConfig.getPacket());
      }
    }

    // Tell the sender that the packet was a success, and that we are done.
    statusObserver.onNext(Status.newBuilder().setId(Status.Id.SUCCESS).build());
    statusObserver.onCompleted();
  }

  @Override
  public void rx(RxConfig rxConfig, StreamObserver<Packet> packetObserver) {
    // There is a node that wants to listen on an address. Remember their
    // address and the stream to use to send stuff to the node.
    rxStreams.put(rxConfig.getAddress(), packetObserver);
  }
}
