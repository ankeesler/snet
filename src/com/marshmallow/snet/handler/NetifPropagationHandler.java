package com.marshmallow.snet.handler;

import java.util.List;

import com.marshmallow.snet.service.Message;

public class NetifPropagationHandler extends BaseNetifHandler {

  @Override
  protected Message handleBytes(final Message message, final List<Integer> bytes) {
    return Message.makeBroadcast(message.getData(), message.getAddress());
  }

}
