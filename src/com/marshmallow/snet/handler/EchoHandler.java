package com.marshmallow.snet.handler;

import com.marshmallow.snet.service.IMessageHandler;
import com.marshmallow.snet.service.Message;

public class EchoHandler implements IMessageHandler {
  @Override
  public Message messageReceived(final Message message) {
    String data = message.getData().replace(' ', '_');
    return Message.makeUnicast(data, message.getAddress());
  }
}
