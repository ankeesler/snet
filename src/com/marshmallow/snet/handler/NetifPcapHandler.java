package com.marshmallow.snet.handler;

import java.io.FilterOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.marshmallow.snet.service.Message;

public class NetifPcapHandler extends BaseNetifHandler {

  private static final String FILENAME = "snet.pcap";

  private FilterOutputStream output;

  private FilterOutputStream getFileOutputStream() throws IOException {
    if (output == null) {
      output = new FilterOutputStream(new FileOutputStream(FILENAME));
      writePcapFileHeader(output);
    }
    return output;
  }

  private static int byteN(long number, int byteNumber) {
    int shift = byteNumber << 3;
    int mask = 0xFF << shift;
    return (int)((number & mask) >> shift);
  }

  private static void writePcapFileHeader(FilterOutputStream output) throws IOException {
    // Let's make this big-endian for readability. I am getting this format from
    // https://wiki.wireshark.org/Development/LibpcapFileFormat.
    Integer[] data = {
      // Magic number (0xa1b2c3d4).
      0xa1, 0xb2, 0xc3, 0xd4,
      // Version (2.4).
      0x00, 0x02,
      0x00, 0x04,
      // Timezone (whatever (0)).
      0x00, 0x00, 0x00, 0x00,
      // Accuracy of timestamps (whatever (0)).
      0x00, 0x00, 0x00, 0x00,
      // Maximum length of captured packets (65535, arbitrary).
      0x00, 0x00, 0xFF, 0xFF,
      // Link layer header type (RAW (101)).
      0x00, 0x00, 0x00, 0x65,
    };
    for (Integer datum : data) {
      output.write(datum);
    }
  }

  private static void writePcacpPacket(FilterOutputStream output, Integer[] bytes) throws IOException {
    // Let's make this big-endian for readability. I am getting this format from
    // https://wiki.wireshark.org/Development/LibpcapFileFormat.
    long nowS = System.currentTimeMillis() / 1000000L;
    Integer[] packet = {
      // Packet #0.
      // Timestamp (seconds).
      byteN(nowS, 3),
      byteN(nowS, 2),
      byteN(nowS, 1),
      byteN(nowS, 0),
      // Timestamp (microseconds (whatever (0))).
      0x00,0x00, 0x00, 0x00,
      // Size of packet in file.
      byteN(bytes.length, 3),
      byteN(bytes.length, 2),
      byteN(bytes.length, 1),
      byteN(bytes.length, 0),
      // Size of packet on wire.
      byteN(bytes.length, 3),
      byteN(bytes.length, 2),
      byteN(bytes.length, 1),
      byteN(bytes.length, 0),
    };
    for (Integer bite : packet) {
      output.write(bite);
    }
    for (Integer bite : bytes) {
      output.write(bite);
    }
  }

  @Override
  protected Message handleBytes(Message message, Integer[] bytes) {
    try {
      writePcacpPacket(getFileOutputStream(), bytes);
    } catch (Exception e) {
      log(this.getClass(), "Cannot log bytes to pcap file: " + e);
    }

    return null;
  }

  @Override
  public void finalize() {
    try {
      output.close();
    } catch (Exception e) {
      // Nothing we can do here...
    }
  }
}
