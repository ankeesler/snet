package com.marshmallow.snet.test;

import java.util.List;

import org.junit.Test;

import com.marshmallow.snet.client.BaseClient;
import com.marshmallow.snet.client.IClient;
import com.marshmallow.snet.service.IService;
import com.marshmallow.snet.service.ServiceUtilities;
import com.marshmallow.snet.service.protobuf.ClientType;
import com.marshmallow.snet.service.protobuf.InfoResponse;
import com.marshmallow.snet.service.protobuf.Packet;

import junit.framework.TestCase;

public class NetifTest extends TestCase {

  private IService service;

  @Override
  public void setUp() throws Exception {
    service = ServiceUtilities.createServiceFromFile("cfg/localsnet.properties");
  }

  @Override
  public void tearDown() throws Exception {
    service.teardown();
  }

  @Test
  public void testAdmin() throws Exception {
    IClient admin = new BaseClient(0, service);

    // All commands should fail before we init the admin.
    Packet p01 = Packet.newBuilder().setSequence(0).setSource(0).setDestination(1).build();
    assertFalse(admin.tx(p01));
    assertNull(admin.rx());
    assertNull(admin.info());

    // Once we init the admin, we shouldn't be able to init again.
    assertTrue(admin.init(ClientType.ADMIN));
    assertFalse(admin.init(ClientType.NODE));
    assertFalse(admin.init(ClientType.ADMIN));

    // Since we are an admin, we shouldn't be able to TX or RX.
    assertFalse(admin.tx(p01));
    assertNull(admin.rx());

    // We should be able to call the INFO API and get a response back.
    InfoResponse infoResponse = admin.info();
    assertNotNull(infoResponse);
    List<Integer> addresses = infoResponse.getAddressesList();
    assertEquals(0, addresses.size());

    // After we add another client, we get an increased node count.
    IClient client0 = new BaseClient(1, service);
    assertTrue(client0.init(ClientType.NODE));
    infoResponse = admin.info();
    assertNotNull(infoResponse);
    addresses = infoResponse.getAddressesList();
    assertEquals(1, addresses.size());
    assertEquals(addresses.get(0), new Integer(client0.getAddress()));

    // After we add another client, we get an increased node count.
    IClient client1 = new BaseClient(2, service);
    assertTrue(client1.init(ClientType.NODE));
    infoResponse = admin.info();
    assertNotNull(infoResponse);
    addresses = infoResponse.getAddressesList();
    assertEquals(2, addresses.size());
    assertEquals(addresses.get(0), new Integer(client0.getAddress()));
    assertEquals(addresses.get(1), new Integer(client1.getAddress()));

    // After we reset the network, the node count goes back to 0.
    assertTrue(client0.reset());
    assertTrue(admin.init(ClientType.ADMIN));
    infoResponse = admin.info();
    assertNotNull(infoResponse);
    addresses = infoResponse.getAddressesList();
    assertEquals(0, addresses.size());    
  }

  @Test
  public void testOneNode() throws Exception {
    IClient client0 = new BaseClient(0, service);

    // Tx should fail, since we have not initialized.
    Packet p01 = Packet.newBuilder().setSequence(0).setSource(0).setDestination(1).build();
    assertFalse(client0.tx(p01));

    // No data at the beginning.
    assertNull(client0.rx());

    // Init should pass.
    assertTrue(client0.init(ClientType.NODE));

    // Still no data.
    assertNull(client0.rx());

    // Transmission should succeed, but no one else should get it.
    assertTrue(client0.tx(p01));
    assertNull(client0.rx());

    // Clients of type NODE cannot send the INFO command.
    assertNull(client0.info());

    // Calling the RESET API should make the network forget this node.
    assertTrue(client0.reset());
    assertFalse(client0.tx(p01));
    assertTrue(client0.init(ClientType.NODE));
  }

  @Test
  public void testOneTxTwoRx() throws Exception {
    IClient client0 = new BaseClient(0, service);
    IClient client1 = new BaseClient(1, service);
    IClient client2 = new BaseClient(2, service);

    // Client0, uninitialized, sends a packet to client1, also uninitialized.
    // The transmission should fail, and the packet shouldn't pop up later.
    Packet p01 = Packet.newBuilder().setSequence(0).setSource(0).setDestination(1).build();
    assertFalse(client0.tx(p01));

    // Client1 shouldn't receive the failed transmission.
    assertNull(client1.rx());

    // Only client0 and client1 init.
    assertTrue(client0.init(ClientType.NODE));
    assertTrue(client1.init(ClientType.NODE));

    // Client1 still doesn't get client0's original packet.
    assertNull(client1.rx());

    // Client0 sends to client2, but it has not initialized, so it does not
    // receive anything.
    Packet p02 = Packet.newBuilder().setSequence(1).setSource(0).setDestination(2).build();
    assertTrue(client0.tx(p02));
    assertNull(client1.rx());
    assertNull(client2.rx());

    // Client2 successfully initializes.
    assertTrue(client2.init(ClientType.NODE));

    // client0 transmits to client2 again - it works this time.
    assertTrue(client0.tx(p02));
    assertNull(client1.rx());
    assertEquals(client2.rx(), p02);

    // No data after we receive the only transmission.
    assertNull(client0.rx());
    assertNull(client1.rx());
    assertNull(client2.rx());

    // client1 transmits, client0 receives it, client2 does not receive it.
    Packet p10 = Packet.newBuilder().setSequence(2).setSource(1).setDestination(0).build();
    assertTrue(client1.tx(p10));
    assertEquals(client0.rx(), p10);
    assertNull(client2.rx());

    // Calling the RESET API will make the network forget about all nodes.
    Packet p21 = Packet.newBuilder().setSequence(3).setSource(2).setDestination(1).build();
    assertTrue(client0.reset());
    assertFalse(client0.tx(p01));
    assertFalse(client1.tx(p10));
    assertFalse(client2.tx(p21));
    assertTrue(client0.init(ClientType.NODE));
    assertTrue(client1.init(ClientType.NODE));
    assertTrue(client2.init(ClientType.NODE));
  }

  @Test
  public void testDoubleTransmission() throws Exception {
    // What happens if we send two packets at once? Depending on propagation
    // settings, they should both be received by the recipient.
    IClient client0 = new BaseClient(0, service);
    IClient client1 = new BaseClient(1, service);
    Packet p01 = Packet.newBuilder().setSequence(0).setSource(0).setDestination(1).build();
    assertTrue(client0.init(ClientType.NODE));
    assertTrue(client1.init(ClientType.NODE));
    assertTrue(client0.tx(p01));
    assertTrue(client0.tx(p01));
    assertEquals(client1.rx(), p01);
    assertEquals(client1.rx(), p01);
    assertNull(client1.rx());
  }

  @Test
  public void testCrossTransmission() throws Exception {
    // What happens if we send packets across a network in a crossbar? Depending
    // on propagation settings, the packets should be received by the intended
    // parties.
    IClient client0 = new BaseClient(0, service);
    IClient client1 = new BaseClient(1, service);
    IClient client2 = new BaseClient(2, service);
    IClient client3 = new BaseClient(3, service);
    assertTrue(client0.init(ClientType.NODE));
    assertTrue(client1.init(ClientType.NODE));
    assertTrue(client2.init(ClientType.NODE));
    assertTrue(client3.init(ClientType.NODE));

    Packet p02 = Packet.newBuilder().setSequence(0).setSource(0).setDestination(2).build();
    Packet p13 = Packet.newBuilder().setSequence(1).setSource(1).setDestination(3).build();
    assertTrue(client0.tx(p02));
    assertTrue(client1.tx(p13));
    assertNull(client0.rx());
    assertNull(client1.rx());
    assertEquals(client2.rx(), p02);
    assertEquals(client3.rx(), p13);

    Packet p01 = Packet.newBuilder().setSequence(2).setSource(0).setDestination(1).build();
    Packet p23 = Packet.newBuilder().setSequence(3).setSource(2).setDestination(3).build();
    assertTrue(client0.tx(p01));
    assertTrue(client1.tx(p23));
    assertNull(client0.rx());
    assertEquals(client1.rx(), p01);
    assertNull(client2.rx());
    assertEquals(client3.rx(), p23);
  }

  @Test
  public void testDuplicateAddress() throws Exception {
    // What happens if we call Init() twice with the same source address? We get
    // a DUPLICATE status in return.
    IClient client0 = new BaseClient(0, service);
    IClient client0Again = new BaseClient(0, service);
    assertTrue(client0.init(ClientType.NODE));
    assertFalse(client0Again.init(ClientType.NODE));

    // Same goes for an admin node.
    IClient admin0 = new BaseClient(0, service);
    assertFalse(admin0.init(ClientType.ADMIN));
  }
}