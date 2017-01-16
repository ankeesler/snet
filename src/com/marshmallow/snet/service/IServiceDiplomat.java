package com.marshmallow.snet.service;

import java.io.IOException;

public interface IServiceDiplomat {
  public boolean send(final String data);
  public String receive() throws IOException;
}
