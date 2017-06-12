package com.marshmallow.snet.test;

import java.util.Arrays;

import org.junit.Test;

import com.marshmallow.snet.client.BaseClient;
import com.marshmallow.snet.client.IClient;
import com.marshmallow.snet.service.IService;
import com.marshmallow.snet.service.ServiceUtilities;
import com.marshmallow.snet.service.protobuf.InfoRequest;
import com.marshmallow.snet.service.protobuf.Packet;
import com.marshmallow.snet.service.protobuf.Packet.Type;

import io.grpc.netty.NettyChannelBuilder;

import com.marshmallow.snet.service.protobuf.SnetServiceGrpc;

import junit.framework.TestCase;

public class NetifTest extends TestCase {

  private IService service;
  private SnetServiceGrpc.SnetServiceBlockingStub stub;

  @Override
  public void setUp() throws Exception {
    service = ServiceUtilities.createServiceFromFile("cfg/localsnet.properties");
    stub = SnetServiceGrpc.newBlockingStub(NettyChannelBuilder.forAddress(service.getAddress().getHostAddress(),
                                                                          service.getPort())
                          .usePlaintext(true)
                          .build());
  }

  @Override
  public void tearDown() throws Exception {
    service.teardown();
  }

  private IClient makeClient(final int i) throws Exception {
    int rxCount = getRxCount();
    IClient client = new BaseClient(i, service);
    while (getRxCount() == rxCount) ;
    return client;
  }

  private int getRxCount() throws Exception {
    return stub.info(InfoRequest.newBuilder().build()).getRxCount();
  }

  @Test
  public void testOneNode() throws Exception {
    IClient client0 = makeClient(0);

    // No data at the beginning.
    expectRx(client0, false);

    // Transmission should succeed, but no one else should get it.
    Packet p0 = Packet.newBuilder().setLength(0).setSource(0).build();
    assertTrue(client0.tx(p0));
    expectRx(client0, false);
  }

  @Test
  public void testOneTxTwoRx() throws Exception {
    IClient client0 = makeClient(0);
    IClient client1 = makeClient(1);
    IClient client2 = makeClient(2);

    // No data to start off with.
    expectRx(client0, false);
    expectRx(client1, false);
    expectRx(client2, false);

    // client0 transmits, client1 and client2 hear it.
    Packet p0 = Packet.newBuilder().setLength(0).setSource(0).build();
    assertTrue(client0.tx(p0));
    expectRx(client1, true, p0);
    expectRx(client2, true, p0);

    // No data after we read first transmission.
    expectRx(client0, false);
    expectRx(client1, false);
    expectRx(client2, false);

    // client1 transmits, client0 and client2 hear it.
    Packet p1 = Packet.newBuilder().setLength(2).setType(Type.COMMAND).setSource(1).build();
    assertTrue(client1.tx(p1));
    expectRx(client0, true, p1);
    expectRx(client2, true, p1);
  }

  @Test
  public void testDoubleTransmission() throws Exception {
    IClient client0 = makeClient(0);
    IClient client1 = makeClient(1);
    IClient client2 = makeClient(2);

    // No data to start off with.
    expectRx(client0, false);
    expectRx(client1, false);
    expectRx(client2, false);

    // client0 transmits twice.
    Packet p0 = Packet.newBuilder().setLength(3).setType(Type.DATA).setSource(0).build();
    Packet p1 = Packet.newBuilder().setLength(3).setType(Type.DATA).setSource(0).build();
    assertTrue(client0.tx(p0));
    assertTrue(client0.tx(p1));
    expectRx(client0, false);
    expectRx(client1, true, p0, p1);
    expectRx(client1, true, p0, p1);
    expectRx(client2, true, p0, p1);
    expectRx(client2, true, p0, p1);
    expectRx(client0, false);
  }

  @Test
  public void testCrossTransmission() throws Exception {
    IClient client0 = makeClient(0);
    IClient client1 = makeClient(1);
    IClient client2 = makeClient(2);

    // No data to start off with.
    expectRx(client0, false);
    expectRx(client1, false);
    expectRx(client2, false);

    Packet p0 = Packet.newBuilder().setSource(0).build();
    Packet p1 = Packet.newBuilder().setSource(1).build();
    Packet p2 = Packet.newBuilder().setSource(2).build();
    assertTrue(client0.tx(p0));
    assertTrue(client1.tx(p1));
    assertTrue(client2.tx(p2));
    expectRx(client0, true, p1, p2);
    expectRx(client0, true, p1, p2);
    expectRx(client1, true, p0, p2);
    expectRx(client1, true, p0, p2);
    expectRx(client2, true, p0, p1);
    expectRx(client2, true, p0, p1);
  }

  private static void expectRx(IClient client, boolean expect, Packet...sentPackets) throws Exception {
    Packet p = null;
    long timeoutMS = 2000; // TODO: can we make this shorter, and deterministic?
    long timeoutStart = System.currentTimeMillis();
    do {
      p = client.rx();
      if (System.currentTimeMillis() - timeoutStart > timeoutMS) {
        if (expect) {
          throw new Exception("Never got an RX from client " + client);
        } else {
          // Success!
          return;
        }
      }
    } while (p == null);

    boolean matched = false;
    for (Packet sentPacket : sentPackets) {
      matched |= sentPacket.equals(p);
    }
    assertTrue("Could not match " + p + " with any of " + Arrays.toString(sentPackets), matched);
  }
}
