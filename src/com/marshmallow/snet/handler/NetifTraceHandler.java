package com.marshmallow.snet.handler;

import java.util.Arrays;

import com.marshmallow.snet.service.Message;

public class NetifTraceHandler extends BaseNetifHandler {

  public static class Packet {
    public final int lengthOfWholePacket; // 1 byte
    public final int frameControl;        // 1 byte
    public final int sequence;            // 1 byte
    public final int source;              // 1 byte
    public final int destination;         // 1 byte
    public final Integer[] payload;

    public final String description;

    public Packet(final Integer[] bytes) {
      StringBuilder descriptionBuilder = new StringBuilder();

      lengthOfWholePacket = bytes[0];
      appendHexByte(descriptionBuilder, "lengthOfWholePacket", lengthOfWholePacket);

      frameControl = bytes[1];
      appendHexByte(descriptionBuilder, "frameControl", frameControl);

      sequence = bytes[2];
      appendHexByte(descriptionBuilder, "sequence", sequence);

      source = bytes[3];
      appendHexByte(descriptionBuilder, "source", source);

      destination = bytes[4];
      appendHexByte(descriptionBuilder, "destination", destination);

      if (bytes.length > 5) {
        payload = Arrays.copyOfRange(bytes, 5, bytes.length);
        descriptionBuilder.append("payload=");
        for (Integer i : payload) {
          descriptionBuilder.append(String.format("0x%02X ", i));
        }
      } else {
        payload = null;
      }

      description = descriptionBuilder.toString();
    }

    @Override
    public String toString() {
      return description;
    }

    private static void appendHexByte(final StringBuilder builder,
                                      final String label,
                                      final Integer integer) {
      builder.append(String.format("%s=0x%02X ", label, integer));
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
