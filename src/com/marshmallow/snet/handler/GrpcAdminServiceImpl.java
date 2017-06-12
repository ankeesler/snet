package com.marshmallow.snet.handler;

import com.marshmallow.snet.service.protobuf.AdminServiceGrpc;
import com.marshmallow.snet.service.protobuf.EchoRequest;
import com.marshmallow.snet.service.protobuf.EchoResponse;

import io.grpc.stub.StreamObserver;

public class GrpcAdminServiceImpl extends AdminServiceGrpc.AdminServiceImplBase {
  
  @Override
  public void echo(EchoRequest request, StreamObserver<EchoResponse> responseObserver) {
    responseObserver.onNext(EchoResponse.newBuilder().setMessage(request.getMessage()).build());
    responseObserver.onCompleted();
  }

}
