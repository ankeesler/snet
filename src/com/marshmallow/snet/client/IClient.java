package com.marshmallow.snet.client;

import com.marshmallow.snet.service.protobuf.ClientType;
import com.marshmallow.snet.service.protobuf.InfoResponse;
import com.marshmallow.snet.service.protobuf.Packet;

public interface IClient {
  public String getName();

  public int getAddress();

  public boolean init(ClientType clientType);

  public boolean tx(final Packet packet);

  public Packet rx();

  public InfoResponse info();

  public boolean reset();
}
