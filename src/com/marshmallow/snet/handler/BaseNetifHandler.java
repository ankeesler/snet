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

    Integer[] bytes = parseBytes(message.getData());
    if (bytes == null) {
      return null;
    }

    return handleBytes(message, bytes);
  }

  protected abstract Message handleBytes(final Message message, final Integer[] bytes);

  private static Integer[] parseBytes(final String message) {
    List<Integer> bytes = new ArrayList<Integer>(0);
    try {
      String[] bs = message.substring(SFD.length()).split(",");
      if (bs.length == 0 || (bs.length == 1 && bs[0].length() == 0)) {
        return null;
      }

      for (int i = 0 ; i < bs.length; i ++) {
        if (bs[i].length() == 0) {
          if (i != bs.length - 1) {
            throw new IllegalArgumentException("Invalid 'null' byte at index: " + i);
          } else {
            continue;
          }
        }

        Integer b = Integer.parseInt(bs[i], 16);
        if (b > 0xFF) {
          throw new IllegalArgumentException("Byte is greater than 255: " + b);
        } else {
          bytes.add(b);
        }
      }
    } catch (Exception e) {
      log(BaseNetifHandler.class, "Could not parse bytes in message (" + message + "): " + e);
      return null;
    }
    return bytes.toArray(new Integer[bytes.size()]);
  }

  protected static void log(final Class<?> clazz, final String message) {
    Log.instance().note(BaseNetifHandler.class, message);
  }
}
