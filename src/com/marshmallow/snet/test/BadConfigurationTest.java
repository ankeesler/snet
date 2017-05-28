package com.marshmallow.snet.test;

import com.marshmallow.snet.service.ServiceUtilities;

import junit.framework.TestCase;

public class BadConfigurationTest extends TestCase {

  private static final boolean RUN_FIRST_TEST = true;

  public void testBadConfiguration() {
    // TODO: how do I get both of these guys to run at the same time???
    if (RUN_FIRST_TEST) {
      try {
        ServiceUtilities.createServiceFromFile("cfg/not-imessagehandler-class.properties");
        fail("should have thrown IllegalArgumentException");
      } catch (IllegalArgumentException iae) {
        // pass
      } catch (Exception exception) {
        exception.printStackTrace();
        fail("threw wrong kind of exception; wanted IllegalArgumentException, got "+ exception);
      }
    }

    if (!RUN_FIRST_TEST) {
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
}
