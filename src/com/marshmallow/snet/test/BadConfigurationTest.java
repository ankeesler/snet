package com.marshmallow.snet.test;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import com.marshmallow.snet.service.ConfigurableService;
import com.marshmallow.snet.service.ConfigurationKey;

import junit.framework.TestCase;

public class BadConfigurationTest extends TestCase {
  public void testBadConfiguration() {
    // TODO: how do I get both of these guys to run at the same time???
//    try {
//      runTest("not-imessagehandler-class.properties");
//      fail("should have thrown IllegalArgumentException");
//    } catch (IllegalArgumentException iae) {
//      // pass
//    } catch (Exception exception) {
//      exception.printStackTrace();
//      fail("threw wrong kind of exception; wanted IllegalArgumentException, got "+ exception);
//    }

    try {
      runTest("unknown-class.properties");
      fail("should have thrown ClassNotFoundException");
    } catch (ClassNotFoundException exception) {
      // pass
    } catch (Exception exception) {
      fail("threw wrong kind of exception; wanted ClassNotFoundException, got " + exception);
    }
  }

  private static void runTest(final String filename) throws Exception {
    File file = new File("cfg", filename);
    assertTrue("Cannot find file " + file.getAbsolutePath(), file.exists());
    Properties properties = new Properties();
    properties.load(new FileReader(file));
    assertNotNull(properties);
    assertTrue(properties.containsKey(ConfigurationKey.HANDLERS.name()));
    new ConfigurableService(properties);
  }
}
