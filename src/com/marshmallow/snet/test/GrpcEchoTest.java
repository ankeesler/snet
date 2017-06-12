package com.marshmallow.snet.test;


import com.marshmallow.snet.service.GrpcBaseService;
import com.marshmallow.snet.service.IService;
import com.marshmallow.snet.service.protobuf.AdminServiceGrpc;
import com.marshmallow.snet.service.protobuf.EchoRequest;
import com.marshmallow.snet.service.protobuf.EchoResponse;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

import junit.framework.TestCase;

public class GrpcEchoTest extends TestCase {

  private static final String MESSAGE = "Tuna, fish, marlin.";

  public void testIt() throws Exception {
    IService service = new GrpcBaseService(12349);
    ManagedChannel channel = NettyChannelBuilder.forAddress("localhost", 12349)
                                                .usePlaintext(true)
                                                .build();
    AdminServiceGrpc.AdminServiceBlockingStub stub = AdminServiceGrpc.newBlockingStub(channel);
    EchoRequest request = EchoRequest.newBuilder()
                                     .setMessage(MESSAGE)
                                     .build();
    EchoResponse response = stub.echo(request);
    assertTrue(response.getMessage().equals(MESSAGE));
  }

}
