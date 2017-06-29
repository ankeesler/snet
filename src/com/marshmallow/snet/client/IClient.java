package com.marshmallow.snet.client;

import com.marshmallow.snet.service.protobuf.Packet;

public interface IClient {
  public String getName();

  public boolean init();

  public boolean tx(final Packet packet);

  public Packet rx();
}
