package com.marshmallow.snet.test;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

import com.marshmallow.snet.service.ConfigurableService;
import com.marshmallow.snet.service.ConfigurationKey;

import junit.framework.TestCase;

public class EchoTest extends TestCase {
  public void testEcho() throws Exception {
    File file = new File("cfg/localecho.properties");
    assertTrue("Cannot find file " + file.getAbsolutePath(), file.exists());

    Properties properties = new Properties();
    properties.load(new FileReader(file));
    assertNotNull(properties);
    assertTrue(properties.containsKey(ConfigurationKey.HANDLERS.name()));

    new ConfigurableService(properties);

    Socket client1 = makeClient();
    clientTest(client1, "this is a sentence");

    Socket client2 = makeClient();
    clientTest(client2, "this is also a sentence");

    client1.close();
    clientTest(client2, "oh and this is also a sentence");
    client2.close();
  }

  private static Socket makeClient() throws Exception {
    Socket client = new Socket(InetAddress.getLoopbackAddress(), 12346);
    assertTrue(client.isConnected());
    assertTrue(client.isBound());
    return client;
  }

  private static void clientTest(final Socket client, final String message) throws Exception {
    PrintWriter toService = new PrintWriter(client.getOutputStream());
    toService.println(message);
    toService.flush();

    LineNumberReader fromService = new LineNumberReader(new InputStreamReader(client.getInputStream()));
    while (!fromService.ready()) { /* from service... */ }
    assertEquals(message.replace(' ', '_'), fromService.readLine());    
  }
}
