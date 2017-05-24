package com.marshmallow.snet.handler;

import java.nio.ByteBuffer;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapDumper;

import com.marshmallow.snet.service.Message;

public class NetifPcapHandler extends BaseNetifHandler {

  private static final String FILENAME = "snet.pcap";

  private static byte[] parseBytes(Integer[] bytes) {
    byte[] reallyBytes = new byte[bytes.length];
    for (int i = 0; i < bytes.length; i ++) {
      // We know all of the values in bytes are <= 0xFF  as they have been
      // vetted in BaseNetifHandler.
      byte b = (byte)(0xFF & bytes[i]);
      reallyBytes[i] = b;
    }
    return reallyBytes;
  }

  @Override
  protected Message handleBytes(Message message, Integer[] bytes) {
    StringBuilder errorMessage = new StringBuilder();
    Pcap pcap = Pcap.openOffline(FILENAME, errorMessage);
    PcapDumper pcapDumper = pcap.dumpOpen(FILENAME);
    ByteBuffer byteBuffer = ByteBuffer.wrap(parseBytes(bytes));

    long nanoTime = System.nanoTime();
    long seconds = nanoTime / 100000000000L;
    int microseconds = (int)(nanoTime % 1000L);
    pcapDumper.dump(seconds, microseconds, bytes.length, bytes.length, byteBuffer);

    return null;
  }

}
