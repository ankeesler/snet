package com.marshmallow.snet.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.grpc.BindableService;

public class ConfigurableService extends GrpcBaseService {

  public ConfigurableService(final Properties properties)
      throws IOException,
             ClassNotFoundException,
             IllegalAccessException,
             InstantiationException,
             IllegalArgumentException {
    super(getPort(properties), getServices(properties));
  }

  private static BindableService[] getServices(final Properties properties)
      throws ClassNotFoundException,
             IllegalAccessException,
             InstantiationException,
             IllegalArgumentException {
    List<BindableService> services = new ArrayList<BindableService>();
    String serviceNames = ConfigurationKey.SERVICES.get(properties);
    for (String serviceName : serviceNames.split(",")) {
      Class<?> clazz = Class.forName(serviceName);
      Object serviceInstance = clazz.newInstance();
      if (serviceInstance instanceof BindableService) {
        services.add((BindableService)serviceInstance);
      } else {
        String message = "Class is not instance of IMessageHandler: "
                         + serviceInstance.getClass().toString();
        throw new IllegalArgumentException(message);
      }
    }
    return services.toArray(new BindableService[services.size()]);
  }

  private static int getPort(final Properties properties) {
    return Integer.parseInt(ConfigurationKey.SERVICE_PORT.get(properties));
  }
}
