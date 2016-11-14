package com.marshmallow.snet.handler;

import java.util.ArrayList;
import java.util.List;

import com.marshmallow.snet.core.Log;
import com.marshmallow.snet.service.IMessageHandler;
import com.marshmallow.snet.service.Message;

public abstract class BaseNetifHandler implements IMessageHandler {

  public static final String SFD = "NETIF OUT ";

  @Override
  public Message messageReceived(final Message message) {
    if (!message.getData().startsWith(SFD)) {
      return null;
    }

    List<Integer> bytes = parseBytes(message.getData());
    if (bytes == null) {
      return null;
    }

    return handleBytes(message, bytes);
  }

  protected abstract Message handleBytes(final Message message, final List<Integer> bytes);

  private static List<Integer> parseBytes(final String message) {
    List<Integer> bytes = new ArrayList<Integer>(0);
    try {
      for (String b : message.substring(SFD.length()).split(",")) {
        Integer i = Integer.parseInt(b, 16);
        if (i > 255) {
          throw new IllegalArgumentException("Byte is greater than 255: " + i);
        } else {
          bytes.add(i);
        }
      }
    } catch (Exception e) {
      log("Could not parse bytes in message (" + message + "): " + e);
      return null;
    }
    return bytes;
  }

  private static void log(final String message) {
    Log.instance().note(BaseNetifHandler.class.toString(), message);
  }
}
