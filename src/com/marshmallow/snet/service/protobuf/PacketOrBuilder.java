// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/snet.proto

package com.marshmallow.snet.service.protobuf;

public interface PacketOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Packet)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 length = 1;</code>
   */
  int getLength();

  /**
   * <code>.Packet.Type type = 2;</code>
   */
  int getTypeValue();
  /**
   * <code>.Packet.Type type = 2;</code>
   */
  com.marshmallow.snet.service.protobuf.Packet.Type getType();

  /**
   * <code>int32 source = 3;</code>
   */
  int getSource();

  /**
   * <code>int32 destination = 4;</code>
   */
  int getDestination();

  /**
   * <code>bytes payload = 5;</code>
   */
  com.google.protobuf.ByteString getPayload();
}
