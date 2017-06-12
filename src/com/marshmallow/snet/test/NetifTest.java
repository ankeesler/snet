package com.marshmallow.snet.test;

import com.marshmallow.snet.client.BaseClient;
import com.marshmallow.snet.client.IClient;
import com.marshmallow.snet.service.IService;
import com.marshmallow.snet.service.ServiceUtilities;
import com.marshmallow.snet.service.protobuf.Packet;
import com.marshmallow.snet.service.protobuf.Packet.Type;

import junit.framework.TestCase;

public class NetifTest extends TestCase {

  private static IService service;
  private static IClient[] clients = new IClient[3];

  private static IClient getClient(final int i) throws Exception {
    if (clients[i] == null) {
      clients[i] = new BaseClient("client" + i, getService());
    }
    return clients[i];
  }

  private static IService getService() throws Exception {
    if (service == null) {
      service = ServiceUtilities.createServiceFromFile("cfg/localsnet.properties");
    }
    return service;
  }

  public void testBroadcast() throws Exception {
    IClient client1 = getClient(0);
    IClient client2 = getClient(1);
    IClient client3 = getClient(2);

    // No data at the beginning.
    assertNull(client1.rx());
    assertNull(client2.rx());
    assertNull(client3.rx());

    // client1 transmits
    Packet p0 = Packet.newBuilder().setLength(0).build();
    assertTrue(client1.tx(p0));
    //waitForRx(client2);
    //waitForRx(client3);

    // No data after we read first transmission.
    assertNull(client1.rx());
    assertNull(client2.rx());
    assertNull(client3.rx());

    // client2 transmits
    Packet p1 = Packet.newBuilder().setLength(2).setType(Type.COMMAND).build();
    assertTrue(client2.tx(p1));
    //waitForRx(client1);
    //waitForRx(client3);

    // Double transmission.
    Packet p2 = Packet.newBuilder().setLength(3).setType(Type.DATA).setSource(1).build();
    Packet p3 = Packet.newBuilder().setLength(3).setType(Type.DATA).setSource(2).build();
    assertTrue(client1.tx(p2));
    assertTrue(client1.tx(p3));
    //waitForRx(client2);
    //waitForRx(client2);
    //waitForRx(client3);
    //waitForRx(client3);

    // Cross cross transmission.
    assertTrue(client1.tx(p0));
    assertTrue(client2.tx(p1));
    assertTrue(client3.tx(p2));
    //waitForRx(client1);
    //waitForRx(client1);
    //waitForRx(client2);
    //waitForRx(client2);
    //waitForRx(client3);
    //waitForRx(client3);
  }

  private static void waitForRx(IClient client) throws Exception {
    while (client.rx() == null) { /* wait for client to receive data */ }
  }
}
