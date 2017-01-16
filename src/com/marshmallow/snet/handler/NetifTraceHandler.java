package com.marshmallow.snet.handler;

import java.util.stream.Stream;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import com.marshmallow.snet.service.Message;

public class NetifTraceHandler extends BaseNetifHandler {

  public static class Packet {
    public static enum Field {
      LENGTH(1),
      FRAME_CONTROL(1),
      SEQUENCE(1),
      SOURCE(1),
      DESTINATION(1),
      PAYLOAD(-1),
      ;

      public final int size;

      private Field(int size) {
        this.size = size;
      }
    }

    public final Map<Field, Integer> fields;
    public final Integer[] payload;

    public final String description;

    public Packet(final Integer[] bytes) {
      fields = new LinkedHashMap<Field, Integer>(Field.values().length);
      for (int i = 0; i < bytes.length && i < 5; i ++) {
        fields.put(Field.values()[i], bytes[i]);
      }

      StringBuilder descriptionBuilder = new StringBuilder();
      Field[] fieldValues = fields.keySet().toArray(new Field[0]);
      Stream.of(fieldValues).forEach(k -> descriptionBuilder.append(String.format("%s=0x%02X ", k.name(), fields.get(k))));

      if (bytes.length > 5) {
        payload = Arrays.copyOfRange(bytes, 5, bytes.length);
        descriptionBuilder.append(Field.PAYLOAD.name() + "=");
        Stream.of(payload).forEach(i -> descriptionBuilder.append(String.format("0x%02X ", i)));
      } else {
        payload = null;
      }

      description = descriptionBuilder.toString();
    }

    @Override
    public String toString() {
      return description;
    }
  }

  @Override
  protected Message handleBytes(Message message, Integer[] bytes) {
    Packet packet = null;
    Exception exception = null;
    try {
      packet = new Packet(bytes);
    } catch (Exception e) {
      exception = e;
    }

    if (exception == null) {
      log(this.getClass(), "Received packet: " + packet.toString());
    } else {
      log(this.getClass(), "Received bad packet: " + exception);
    }
    return null;
  }

}
