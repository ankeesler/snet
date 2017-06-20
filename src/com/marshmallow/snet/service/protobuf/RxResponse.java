// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/snet.proto

package com.marshmallow.snet.service.protobuf;

/**
 * Protobuf type {@code RxResponse}
 */
public  final class RxResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:RxResponse)
    RxResponseOrBuilder {
  // Use RxResponse.newBuilder() to construct.
  private RxResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RxResponse() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private RxResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            com.marshmallow.snet.service.protobuf.Status.Builder subBuilder = null;
            if (status_ != null) {
              subBuilder = status_.toBuilder();
            }
            status_ = input.readMessage(com.marshmallow.snet.service.protobuf.Status.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(status_);
              status_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            com.marshmallow.snet.service.protobuf.Packet.Builder subBuilder = null;
            if (packet_ != null) {
              subBuilder = packet_.toBuilder();
            }
            packet_ = input.readMessage(com.marshmallow.snet.service.protobuf.Packet.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(packet_);
              packet_ = subBuilder.buildPartial();
            }

            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.marshmallow.snet.service.protobuf.Snet.internal_static_RxResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.marshmallow.snet.service.protobuf.Snet.internal_static_RxResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.marshmallow.snet.service.protobuf.RxResponse.class, com.marshmallow.snet.service.protobuf.RxResponse.Builder.class);
  }

  public static final int STATUS_FIELD_NUMBER = 1;
  private com.marshmallow.snet.service.protobuf.Status status_;
  /**
   * <code>.Status status = 1;</code>
   */
  public boolean hasStatus() {
    return status_ != null;
  }
  /**
   * <code>.Status status = 1;</code>
   */
  public com.marshmallow.snet.service.protobuf.Status getStatus() {
    return status_ == null ? com.marshmallow.snet.service.protobuf.Status.getDefaultInstance() : status_;
  }
  /**
   * <code>.Status status = 1;</code>
   */
  public com.marshmallow.snet.service.protobuf.StatusOrBuilder getStatusOrBuilder() {
    return getStatus();
  }

  public static final int PACKET_FIELD_NUMBER = 2;
  private com.marshmallow.snet.service.protobuf.Packet packet_;
  /**
   * <code>.Packet packet = 2;</code>
   */
  public boolean hasPacket() {
    return packet_ != null;
  }
  /**
   * <code>.Packet packet = 2;</code>
   */
  public com.marshmallow.snet.service.protobuf.Packet getPacket() {
    return packet_ == null ? com.marshmallow.snet.service.protobuf.Packet.getDefaultInstance() : packet_;
  }
  /**
   * <code>.Packet packet = 2;</code>
   */
  public com.marshmallow.snet.service.protobuf.PacketOrBuilder getPacketOrBuilder() {
    return getPacket();
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (status_ != null) {
      output.writeMessage(1, getStatus());
    }
    if (packet_ != null) {
      output.writeMessage(2, getPacket());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (status_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getStatus());
    }
    if (packet_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getPacket());
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.marshmallow.snet.service.protobuf.RxResponse)) {
      return super.equals(obj);
    }
    com.marshmallow.snet.service.protobuf.RxResponse other = (com.marshmallow.snet.service.protobuf.RxResponse) obj;

    boolean result = true;
    result = result && (hasStatus() == other.hasStatus());
    if (hasStatus()) {
      result = result && getStatus()
          .equals(other.getStatus());
    }
    result = result && (hasPacket() == other.hasPacket());
    if (hasPacket()) {
      result = result && getPacket()
          .equals(other.getPacket());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasStatus()) {
      hash = (37 * hash) + STATUS_FIELD_NUMBER;
      hash = (53 * hash) + getStatus().hashCode();
    }
    if (hasPacket()) {
      hash = (37 * hash) + PACKET_FIELD_NUMBER;
      hash = (53 * hash) + getPacket().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.marshmallow.snet.service.protobuf.RxResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.RxResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.marshmallow.snet.service.protobuf.RxResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code RxResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RxResponse)
      com.marshmallow.snet.service.protobuf.RxResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_RxResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_RxResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.marshmallow.snet.service.protobuf.RxResponse.class, com.marshmallow.snet.service.protobuf.RxResponse.Builder.class);
    }

    // Construct using com.marshmallow.snet.service.protobuf.RxResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      if (statusBuilder_ == null) {
        status_ = null;
      } else {
        status_ = null;
        statusBuilder_ = null;
      }
      if (packetBuilder_ == null) {
        packet_ = null;
      } else {
        packet_ = null;
        packetBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_RxResponse_descriptor;
    }

    public com.marshmallow.snet.service.protobuf.RxResponse getDefaultInstanceForType() {
      return com.marshmallow.snet.service.protobuf.RxResponse.getDefaultInstance();
    }

    public com.marshmallow.snet.service.protobuf.RxResponse build() {
      com.marshmallow.snet.service.protobuf.RxResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.marshmallow.snet.service.protobuf.RxResponse buildPartial() {
      com.marshmallow.snet.service.protobuf.RxResponse result = new com.marshmallow.snet.service.protobuf.RxResponse(this);
      if (statusBuilder_ == null) {
        result.status_ = status_;
      } else {
        result.status_ = statusBuilder_.build();
      }
      if (packetBuilder_ == null) {
        result.packet_ = packet_;
      } else {
        result.packet_ = packetBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.marshmallow.snet.service.protobuf.RxResponse) {
        return mergeFrom((com.marshmallow.snet.service.protobuf.RxResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.marshmallow.snet.service.protobuf.RxResponse other) {
      if (other == com.marshmallow.snet.service.protobuf.RxResponse.getDefaultInstance()) return this;
      if (other.hasStatus()) {
        mergeStatus(other.getStatus());
      }
      if (other.hasPacket()) {
        mergePacket(other.getPacket());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.marshmallow.snet.service.protobuf.RxResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.marshmallow.snet.service.protobuf.RxResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.marshmallow.snet.service.protobuf.Status status_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.marshmallow.snet.service.protobuf.Status, com.marshmallow.snet.service.protobuf.Status.Builder, com.marshmallow.snet.service.protobuf.StatusOrBuilder> statusBuilder_;
    /**
     * <code>.Status status = 1;</code>
     */
    public boolean hasStatus() {
      return statusBuilder_ != null || status_ != null;
    }
    /**
     * <code>.Status status = 1;</code>
     */
    public com.marshmallow.snet.service.protobuf.Status getStatus() {
      if (statusBuilder_ == null) {
        return status_ == null ? com.marshmallow.snet.service.protobuf.Status.getDefaultInstance() : status_;
      } else {
        return statusBuilder_.getMessage();
      }
    }
    /**
     * <code>.Status status = 1;</code>
     */
    public Builder setStatus(com.marshmallow.snet.service.protobuf.Status value) {
      if (statusBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        status_ = value;
        onChanged();
      } else {
        statusBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.Status status = 1;</code>
     */
    public Builder setStatus(
        com.marshmallow.snet.service.protobuf.Status.Builder builderForValue) {
      if (statusBuilder_ == null) {
        status_ = builderForValue.build();
        onChanged();
      } else {
        statusBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.Status status = 1;</code>
     */
    public Builder mergeStatus(com.marshmallow.snet.service.protobuf.Status value) {
      if (statusBuilder_ == null) {
        if (status_ != null) {
          status_ =
            com.marshmallow.snet.service.protobuf.Status.newBuilder(status_).mergeFrom(value).buildPartial();
        } else {
          status_ = value;
        }
        onChanged();
      } else {
        statusBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.Status status = 1;</code>
     */
    public Builder clearStatus() {
      if (statusBuilder_ == null) {
        status_ = null;
        onChanged();
      } else {
        status_ = null;
        statusBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.Status status = 1;</code>
     */
    public com.marshmallow.snet.service.protobuf.Status.Builder getStatusBuilder() {
      
      onChanged();
      return getStatusFieldBuilder().getBuilder();
    }
    /**
     * <code>.Status status = 1;</code>
     */
    public com.marshmallow.snet.service.protobuf.StatusOrBuilder getStatusOrBuilder() {
      if (statusBuilder_ != null) {
        return statusBuilder_.getMessageOrBuilder();
      } else {
        return status_ == null ?
            com.marshmallow.snet.service.protobuf.Status.getDefaultInstance() : status_;
      }
    }
    /**
     * <code>.Status status = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.marshmallow.snet.service.protobuf.Status, com.marshmallow.snet.service.protobuf.Status.Builder, com.marshmallow.snet.service.protobuf.StatusOrBuilder> 
        getStatusFieldBuilder() {
      if (statusBuilder_ == null) {
        statusBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.marshmallow.snet.service.protobuf.Status, com.marshmallow.snet.service.protobuf.Status.Builder, com.marshmallow.snet.service.protobuf.StatusOrBuilder>(
                getStatus(),
                getParentForChildren(),
                isClean());
        status_ = null;
      }
      return statusBuilder_;
    }

    private com.marshmallow.snet.service.protobuf.Packet packet_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.marshmallow.snet.service.protobuf.Packet, com.marshmallow.snet.service.protobuf.Packet.Builder, com.marshmallow.snet.service.protobuf.PacketOrBuilder> packetBuilder_;
    /**
     * <code>.Packet packet = 2;</code>
     */
    public boolean hasPacket() {
      return packetBuilder_ != null || packet_ != null;
    }
    /**
     * <code>.Packet packet = 2;</code>
     */
    public com.marshmallow.snet.service.protobuf.Packet getPacket() {
      if (packetBuilder_ == null) {
        return packet_ == null ? com.marshmallow.snet.service.protobuf.Packet.getDefaultInstance() : packet_;
      } else {
        return packetBuilder_.getMessage();
      }
    }
    /**
     * <code>.Packet packet = 2;</code>
     */
    public Builder setPacket(com.marshmallow.snet.service.protobuf.Packet value) {
      if (packetBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        packet_ = value;
        onChanged();
      } else {
        packetBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.Packet packet = 2;</code>
     */
    public Builder setPacket(
        com.marshmallow.snet.service.protobuf.Packet.Builder builderForValue) {
      if (packetBuilder_ == null) {
        packet_ = builderForValue.build();
        onChanged();
      } else {
        packetBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.Packet packet = 2;</code>
     */
    public Builder mergePacket(com.marshmallow.snet.service.protobuf.Packet value) {
      if (packetBuilder_ == null) {
        if (packet_ != null) {
          packet_ =
            com.marshmallow.snet.service.protobuf.Packet.newBuilder(packet_).mergeFrom(value).buildPartial();
        } else {
          packet_ = value;
        }
        onChanged();
      } else {
        packetBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.Packet packet = 2;</code>
     */
    public Builder clearPacket() {
      if (packetBuilder_ == null) {
        packet_ = null;
        onChanged();
      } else {
        packet_ = null;
        packetBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.Packet packet = 2;</code>
     */
    public com.marshmallow.snet.service.protobuf.Packet.Builder getPacketBuilder() {
      
      onChanged();
      return getPacketFieldBuilder().getBuilder();
    }
    /**
     * <code>.Packet packet = 2;</code>
     */
    public com.marshmallow.snet.service.protobuf.PacketOrBuilder getPacketOrBuilder() {
      if (packetBuilder_ != null) {
        return packetBuilder_.getMessageOrBuilder();
      } else {
        return packet_ == null ?
            com.marshmallow.snet.service.protobuf.Packet.getDefaultInstance() : packet_;
      }
    }
    /**
     * <code>.Packet packet = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.marshmallow.snet.service.protobuf.Packet, com.marshmallow.snet.service.protobuf.Packet.Builder, com.marshmallow.snet.service.protobuf.PacketOrBuilder> 
        getPacketFieldBuilder() {
      if (packetBuilder_ == null) {
        packetBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.marshmallow.snet.service.protobuf.Packet, com.marshmallow.snet.service.protobuf.Packet.Builder, com.marshmallow.snet.service.protobuf.PacketOrBuilder>(
                getPacket(),
                getParentForChildren(),
                isClean());
        packet_ = null;
      }
      return packetBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:RxResponse)
  }

  // @@protoc_insertion_point(class_scope:RxResponse)
  private static final com.marshmallow.snet.service.protobuf.RxResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.marshmallow.snet.service.protobuf.RxResponse();
  }

  public static com.marshmallow.snet.service.protobuf.RxResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RxResponse>
      PARSER = new com.google.protobuf.AbstractParser<RxResponse>() {
    public RxResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RxResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RxResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RxResponse> getParserForType() {
    return PARSER;
  }

  public com.marshmallow.snet.service.protobuf.RxResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

