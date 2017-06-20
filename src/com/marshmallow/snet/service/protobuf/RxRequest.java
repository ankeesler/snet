// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/snet.proto

package com.marshmallow.snet.service.protobuf;

/**
 * Protobuf type {@code RxRequest}
 */
public  final class RxRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:RxRequest)
    RxRequestOrBuilder {
  // Use RxRequest.newBuilder() to construct.
  private RxRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RxRequest() {
    address_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private RxRequest(
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
          case 8: {

            address_ = input.readInt32();
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
    return com.marshmallow.snet.service.protobuf.Snet.internal_static_RxRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.marshmallow.snet.service.protobuf.Snet.internal_static_RxRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.marshmallow.snet.service.protobuf.RxRequest.class, com.marshmallow.snet.service.protobuf.RxRequest.Builder.class);
  }

  public static final int ADDRESS_FIELD_NUMBER = 1;
  private int address_;
  /**
   * <code>int32 address = 1;</code>
   */
  public int getAddress() {
    return address_;
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
    if (address_ != 0) {
      output.writeInt32(1, address_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (address_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, address_);
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
    if (!(obj instanceof com.marshmallow.snet.service.protobuf.RxRequest)) {
      return super.equals(obj);
    }
    com.marshmallow.snet.service.protobuf.RxRequest other = (com.marshmallow.snet.service.protobuf.RxRequest) obj;

    boolean result = true;
    result = result && (getAddress()
        == other.getAddress());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ADDRESS_FIELD_NUMBER;
    hash = (53 * hash) + getAddress();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.marshmallow.snet.service.protobuf.RxRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.RxRequest parseFrom(
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
  public static Builder newBuilder(com.marshmallow.snet.service.protobuf.RxRequest prototype) {
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
   * Protobuf type {@code RxRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RxRequest)
      com.marshmallow.snet.service.protobuf.RxRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_RxRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_RxRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.marshmallow.snet.service.protobuf.RxRequest.class, com.marshmallow.snet.service.protobuf.RxRequest.Builder.class);
    }

    // Construct using com.marshmallow.snet.service.protobuf.RxRequest.newBuilder()
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
      address_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_RxRequest_descriptor;
    }

    public com.marshmallow.snet.service.protobuf.RxRequest getDefaultInstanceForType() {
      return com.marshmallow.snet.service.protobuf.RxRequest.getDefaultInstance();
    }

    public com.marshmallow.snet.service.protobuf.RxRequest build() {
      com.marshmallow.snet.service.protobuf.RxRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.marshmallow.snet.service.protobuf.RxRequest buildPartial() {
      com.marshmallow.snet.service.protobuf.RxRequest result = new com.marshmallow.snet.service.protobuf.RxRequest(this);
      result.address_ = address_;
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
      if (other instanceof com.marshmallow.snet.service.protobuf.RxRequest) {
        return mergeFrom((com.marshmallow.snet.service.protobuf.RxRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.marshmallow.snet.service.protobuf.RxRequest other) {
      if (other == com.marshmallow.snet.service.protobuf.RxRequest.getDefaultInstance()) return this;
      if (other.getAddress() != 0) {
        setAddress(other.getAddress());
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
      com.marshmallow.snet.service.protobuf.RxRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.marshmallow.snet.service.protobuf.RxRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int address_ ;
    /**
     * <code>int32 address = 1;</code>
     */
    public int getAddress() {
      return address_;
    }
    /**
     * <code>int32 address = 1;</code>
     */
    public Builder setAddress(int value) {
      
      address_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 address = 1;</code>
     */
    public Builder clearAddress() {
      
      address_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:RxRequest)
  }

  // @@protoc_insertion_point(class_scope:RxRequest)
  private static final com.marshmallow.snet.service.protobuf.RxRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.marshmallow.snet.service.protobuf.RxRequest();
  }

  public static com.marshmallow.snet.service.protobuf.RxRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RxRequest>
      PARSER = new com.google.protobuf.AbstractParser<RxRequest>() {
    public RxRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RxRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RxRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RxRequest> getParserForType() {
    return PARSER;
  }

  public com.marshmallow.snet.service.protobuf.RxRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
