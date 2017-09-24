package com.marshmallow.snet.service;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class ServiceUtilities {
  private ServiceUtilities() { }

  public static IService createServiceFromFile(final String filename) throws Exception {
    File file = new File(filename);
    Properties properties = new Properties();
    properties.load(new FileReader(file));
    return new ConfigurableService(properties);
  }
}
