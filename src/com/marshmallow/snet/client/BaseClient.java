package com.marshmallow.snet.client;

import java.io.IOException;

import com.marshmallow.snet.service.IService;

public class BaseClient implements IClient {
  private final String name;

  public BaseClient(final String name, final IService service)
      throws IOException {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean send(final String data) {
    return false;
  }

  @Override
  public String receive() throws IOException {
    return "";
  }

  @Override
  public String toString() {
    return this.name;
  }
}
