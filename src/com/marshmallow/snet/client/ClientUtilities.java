package com.marshmallow.snet.client;

import com.marshmallow.snet.service.IService;

public class ClientUtilities {
  private ClientUtilities() { }

  private static int clientId = 1;
  public static IClient createClientForService(final IService service) throws Exception {
    String name = "client" + clientId++;
    return new BaseClient(name, service.getAddress(), service.getPort());
  }
}
