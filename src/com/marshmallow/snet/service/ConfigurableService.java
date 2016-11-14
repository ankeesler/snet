package com.marshmallow.snet.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class ConfigurableService extends BaseService {

  public ConfigurableService(final Properties properties)
      throws IOException,
             ClassNotFoundException,
             IllegalAccessException,
             InstantiationException,
             IllegalArgumentException {
    super(getAddress(properties), getPort(properties));
    initHandlers(properties);
  }

  private void initHandlers(final Properties properties)
      throws ClassNotFoundException,
             IllegalAccessException,
             InstantiationException,
             IllegalArgumentException {
    String handlers = ConfigurationKey.HANDLERS.get(properties);
    for (String handler : handlers.split(",")) {
      Class<?> clazz = Class.forName(handler);
      Object handlerInstance = clazz.newInstance();
      if (handlerInstance instanceof IMessageHandler) {
        addMessageHandler((IMessageHandler)handlerInstance);        
      } else {
        String message = "Class is not instance of IMessageHandler: " + handlerInstance.getClass().toString();
        throw new IllegalArgumentException(message);
      }
    }
  }

  private static InetAddress getAddress(final Properties properties) {
    String addressString = ConfigurationKey.SERVICE_ADDRESS.get(properties);
    String dephault = ConfigurationKey.SERVICE_ADDRESS.dephault();
    try {
      return InetAddress.getByName(addressString); 
    } catch (UnknownHostException uhe) {
      log(ConfigurableService.class, "Cannot parse address string " + addressString + ". Using default service address.");
      try { return InetAddress.getByName(dephault); } catch (Exception e) { assert false; }
    }
    return null; // we should never get here, we should always be able to parse the default address
  }

  private static int getPort(final Properties properties) {
    return Integer.parseInt(ConfigurationKey.SERVICE_PORT.get(properties));
  }
}
