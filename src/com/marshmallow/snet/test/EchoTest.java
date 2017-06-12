package com.marshmallow.snet.test;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import com.marshmallow.snet.service.ConfigurableService;
import com.marshmallow.snet.service.ConfigurationKey;
import com.marshmallow.snet.service.protobuf.AdminServiceGrpc;
import com.marshmallow.snet.service.protobuf.EchoRequest;
import com.marshmallow.snet.service.protobuf.EchoResponse;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import junit.framework.TestCase;

public class EchoTest extends TestCase {
  public void testEcho() throws Exception {
    File file = new File("cfg/localecho.properties");
    assertTrue("Cannot find file " + file.getAbsolutePath(), file.exists());

    Properties properties = new Properties();
    properties.load(new FileReader(file));
    assertNotNull(properties);
    assertTrue(properties.containsKey(ConfigurationKey.SERVICES.name()));
    assertTrue(properties.containsKey(ConfigurationKey.SERVICE_PORT.name()));

    new ConfigurableService(properties);

    int port = Integer.parseInt(ConfigurationKey.SERVICE_PORT.get(properties));
    assertEquals(port, 12346);

    AdminServiceGrpc.AdminServiceBlockingStub client1 = makeClient(port);
    clientTest(client1, "this is a sentence");

    AdminServiceGrpc.AdminServiceBlockingStub client2 = makeClient(port);
    clientTest(client2, "this is also a sentence");

    clientTest(client2, "oh and this is also a sentence");
  }

  private static AdminServiceGrpc.AdminServiceBlockingStub makeClient(int port) throws Exception {
    ManagedChannel channel = NettyChannelBuilder.forAddress("localhost", port)
        .usePlaintext(true)
        .build();
    AdminServiceGrpc.AdminServiceBlockingStub stub = AdminServiceGrpc.newBlockingStub(channel);
    return stub;
  }

  private static void clientTest(final AdminServiceGrpc.AdminServiceBlockingStub client,
                                 final String message) throws Exception {
    EchoRequest request = EchoRequest.newBuilder().setMessage(message).build();
    EchoResponse response = client.echo(request);
    assertEquals(message, response.getMessage());
  }
}
