package com.marshmallow.snet.test;

import com.marshmallow.snet.service.ServiceUtilities;

import junit.framework.TestCase;

public class BadConfigurationTest extends TestCase {

  public void testNotBindableServiceInstance() throws Exception {
    try {
      ServiceUtilities.createServiceFromFile("cfg/not-bindableservice-class.properties");
      fail("should have thrown IllegalArgumentException");
    } catch (IllegalArgumentException iae) {
      // pass
    } catch (Exception exception) {
      exception.printStackTrace();
      fail("threw wrong kind of exception; wanted IllegalArgumentException, got " + exception);
    }
  }

  public void testClassNotFound() {
    try {
      ServiceUtilities.createServiceFromFile("cfg/unknown-class.properties");
      fail("should have thrown ClassNotFoundException");
    } catch (ClassNotFoundException exception) {
      // pass
    } catch (Exception exception) {
      fail("threw wrong kind of exception; wanted ClassNotFoundException, got " + exception);
    }
  }
}
