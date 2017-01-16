package com.marshmallow.snet.client;

import java.io.IOException;

import com.marshmallow.snet.service.IService;
import com.marshmallow.snet.service.IServiceDiplomat;
import com.marshmallow.snet.service.ServiceDiplomat;

public class BaseClient implements IClient {
  private final String name;
  private final IServiceDiplomat serviceDiplomat;

  public BaseClient(final String name, final IService service)
      throws IOException {
    this.name = name;
    this.serviceDiplomat = new ServiceDiplomat(service);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean send(final String data) {
    return serviceDiplomat.send(data);
  }

  @Override
  public String receive() throws IOException {
    return serviceDiplomat.receive();
  }

  @Override
  public String toString() {
    return this.name;
  }
}
