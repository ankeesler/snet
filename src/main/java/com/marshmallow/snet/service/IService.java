package com.marshmallow.snet.service;

import java.net.InetAddress;

public interface IService {
  public InetAddress getAddress();

  public int getPort();

  public int getClientCount();

  public void teardown();
}
