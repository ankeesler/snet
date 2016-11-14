package com.marshmallow.snet.service;

public class Message {
  private final String data;
  private final int address;
  private final boolean unicast;

  private Message(final String data,
                  final int address,
                  final boolean unicast) {
    this.data = data;
    this.address = address;
    this.unicast = unicast;
  }

  public static Message makeUnicast(final String data, final int destination) {
    return new Message(data, destination, true); // unicast
  }

  public static Message makeBroadcast(final String data, final int source) {
    return new Message(data, source, false); // broadcast
  }

  public String getData() {
    return this.data;
  }

  public int getAddress() {
    return this.address;
  }

  boolean getUnicast() {
    return this.unicast;
  }
}
