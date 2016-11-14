package com.marshmallow.snet.handler;

import java.util.List;

import com.marshmallow.snet.service.Message;

public class NetifTraceHandler extends BaseNetifHandler {

  @Override
  protected Message handleBytes(Message message, List<Integer> bytes) {
    return null;
  }

}
