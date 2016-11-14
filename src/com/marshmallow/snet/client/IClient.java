package com.marshmallow.snet.client;

import java.io.IOException;

public interface IClient {
  public String getName();
  public boolean send(final String data);
  public String receive() throws IOException;
}
