package com.marshmallow.snet.service;

import java.net.InetAddress;

public interface IService extends IMessageHandlerContainer {
  public InetAddress getAddress();
  public int getPort();
  public int getClientCount();
}
