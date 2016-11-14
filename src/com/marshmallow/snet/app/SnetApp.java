package com.marshmallow.snet.app;

import com.marshmallow.snet.service.ServiceUtilities;

public class SnetApp {
  public static void main(String[] args) {
    try {
      ServiceUtilities.createServiceFromFile("cfg/localsnet.properties");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
