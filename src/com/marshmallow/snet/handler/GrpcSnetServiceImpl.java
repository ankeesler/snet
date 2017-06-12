package com.marshmallow.snet.handler;

import com.marshmallow.snet.service.protobuf.SnetServiceGrpc;
import com.marshmallow.snet.service.protobuf.Status;
import com.marshmallow.snet.service.protobuf.RxConfig;
import com.marshmallow.snet.service.protobuf.TxConfig;
import com.marshmallow.snet.service.protobuf.EchoRequest;
import com.marshmallow.snet.service.protobuf.EchoResponse;
import com.marshmallow.snet.service.protobuf.Packet;

import io.grpc.stub.StreamObserver;

public class GrpcSnetServiceImpl extends SnetServiceGrpc.SnetServiceImplBase {
  
  @Override
  public void echo(EchoRequest request, StreamObserver<EchoResponse> responseObserver) {
    responseObserver.onNext(EchoResponse.newBuilder().setMessage(request.getMessage()).build());
    responseObserver.onCompleted();
  }

  @Override
  public void tx(TxConfig txConfig, StreamObserver<Status> statusObserver) {
    // TODO: implement me!
    statusObserver.onNext(Status.newBuilder().setId(Status.Id.SUCCESS).build());
    statusObserver.onCompleted();
  }

  @Override
  public void rx(RxConfig txConfig, StreamObserver<Packet> packetObserver) {
    // TODO: implement me!
  }
}
