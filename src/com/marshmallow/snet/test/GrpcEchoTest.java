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

  public void testIt() throws Exception {
    IService service = new GrpcBaseService(12349);
    //ManagedChannel channel = NettyChannelBuilder.forTarget(AdminServiceGrpc.SERVICE_NAME).build();
    //AdminServiceGrpc.AdminServiceBlockingStub stub = AdminServiceGrpc.newBlockingStub(channel);
    //EchoResponse response = stub.echo(EchoRequest.newBuilder().setMessage("Tuna, fish, marlin.").build());
    //System.out.println("Response: " + response.getMessage());
  }

}
