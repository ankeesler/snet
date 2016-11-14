package com.marshmallow.snet.test;

import com.marshmallow.snet.client.ClientUtilities;
import com.marshmallow.snet.client.IClient;
import com.marshmallow.snet.service.IService;
import com.marshmallow.snet.service.ServiceUtilities;

import junit.framework.TestCase;

public class NetifTest extends TestCase {

  private static IService service;
  private static IClient[] clients = new IClient[3];

  private static IClient getClient(final int i) throws Exception {
    if (clients[i] == null) {
      int previousClientCount = getService().getClientCount();
      clients[i] = ClientUtilities.createClientForService(getService());

      // Wait for the client to connect to the service
      while (getService().getClientCount() == previousClientCount) { }
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
    assertNull(client1.receive());
    assertNull(client2.receive());
    assertNull(client3.receive());

    // client1 transmits
    assertTrue(client1.send("NETIF OUT 12"));
    waitForRx(client2);
    waitForRx(client3);

    // No data after we read first transmission.
    assertNull(client1.receive());
    assertNull(client2.receive());
    assertNull(client3.receive());

    // client2 transmits
    assertTrue(client2.send("NETIF OUT 34,56"));
    waitForRx(client1);
    waitForRx(client3);

    // Double transmission.
    assertTrue(client1.send("NETIF OUT ab,cd,ef,00"));
    assertTrue(client1.send("NETIF OUT 00,11,22,33,44,55,66,77,88,99,aa,bb,cc,dd,ee,ff"));
    waitForRx(client2);
    waitForRx(client2);
    waitForRx(client3);
    waitForRx(client3);

    // Cross cross transmission.
    assertTrue(client1.send("NETIF OUT aa"));
    assertTrue(client2.send("NETIF OUT bb"));
    assertTrue(client3.send("NETIF OUT cc"));
    waitForRx(client1);
    waitForRx(client1);
    waitForRx(client2);
    waitForRx(client2);
    waitForRx(client3);
    waitForRx(client3);
  }

  public void testInvalidMessages() throws Exception {
    IClient client1 = getClient(0);
    IClient client2 = getClient(1);

    String[] invalidNetifMessages = {
      "a",
      "NETIFOUT 11,22",
      "NETIF OUT11,22",
      "NETIF Out 11,22",
      "NETIF OUT",
      "NETIF OUT ",
      "NETIF OUT 1g,22",
      "NETIF OUT abc,1",
      "NETIF OUT 121,1",
    };
    for (String msg : invalidNetifMessages) {
      assertTrue(client1.send(msg));
      assertNull("Invalid message " + msg + " was transmitted to client.", client2.receive());
    }
  }

  private static void waitForRx(IClient client) throws Exception {
    while (client.receive() == null) { /* wait for client to receive data */ }
  }
}
