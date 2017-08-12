// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/snet.proto

package com.marshmallow.snet.service.protobuf;

/**
 * Protobuf type {@code InitRequest}
 */
public  final class InitRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:InitRequest)
    InitRequestOrBuilder {
  // Use InitRequest.newBuilder() to construct.
  private InitRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private InitRequest() {
    type_ = 0;
    address_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private InitRequest(
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
            int rawValue = input.readEnum();

            type_ = rawValue;
            break;
          }
          case 16: {

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
    return com.marshmallow.snet.service.protobuf.Snet.internal_static_InitRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.marshmallow.snet.service.protobuf.Snet.internal_static_InitRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.marshmallow.snet.service.protobuf.InitRequest.class, com.marshmallow.snet.service.protobuf.InitRequest.Builder.class);
  }

  public static final int TYPE_FIELD_NUMBER = 1;
  private int type_;
  /**
   * <code>.ClientType type = 1;</code>
   */
  public int getTypeValue() {
    return type_;
  }
  /**
   * <code>.ClientType type = 1;</code>
   */
  public com.marshmallow.snet.service.protobuf.ClientType getType() {
    com.marshmallow.snet.service.protobuf.ClientType result = com.marshmallow.snet.service.protobuf.ClientType.valueOf(type_);
    return result == null ? com.marshmallow.snet.service.protobuf.ClientType.UNRECOGNIZED : result;
  }

  public static final int ADDRESS_FIELD_NUMBER = 2;
  private int address_;
  /**
   * <code>int32 address = 2;</code>
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
    if (type_ != com.marshmallow.snet.service.protobuf.ClientType.ADMIN.getNumber()) {
      output.writeEnum(1, type_);
    }
    if (address_ != 0) {
      output.writeInt32(2, address_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (type_ != com.marshmallow.snet.service.protobuf.ClientType.ADMIN.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, type_);
    }
    if (address_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, address_);
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
    if (!(obj instanceof com.marshmallow.snet.service.protobuf.InitRequest)) {
      return super.equals(obj);
    }
    com.marshmallow.snet.service.protobuf.InitRequest other = (com.marshmallow.snet.service.protobuf.InitRequest) obj;

    boolean result = true;
    result = result && type_ == other.type_;
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
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + type_;
    hash = (37 * hash) + ADDRESS_FIELD_NUMBER;
    hash = (53 * hash) + getAddress();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.marshmallow.snet.service.protobuf.InitRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.InitRequest parseFrom(
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
  public static Builder newBuilder(com.marshmallow.snet.service.protobuf.InitRequest prototype) {
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
   * Protobuf type {@code InitRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:InitRequest)
      com.marshmallow.snet.service.protobuf.InitRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_InitRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_InitRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.marshmallow.snet.service.protobuf.InitRequest.class, com.marshmallow.snet.service.protobuf.InitRequest.Builder.class);
    }

    // Construct using com.marshmallow.snet.service.protobuf.InitRequest.newBuilder()
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
      type_ = 0;

      address_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_InitRequest_descriptor;
    }

    public com.marshmallow.snet.service.protobuf.InitRequest getDefaultInstanceForType() {
      return com.marshmallow.snet.service.protobuf.InitRequest.getDefaultInstance();
    }

    public com.marshmallow.snet.service.protobuf.InitRequest build() {
      com.marshmallow.snet.service.protobuf.InitRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.marshmallow.snet.service.protobuf.InitRequest buildPartial() {
      com.marshmallow.snet.service.protobuf.InitRequest result = new com.marshmallow.snet.service.protobuf.InitRequest(this);
      result.type_ = type_;
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
      if (other instanceof com.marshmallow.snet.service.protobuf.InitRequest) {
        return mergeFrom((com.marshmallow.snet.service.protobuf.InitRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.marshmallow.snet.service.protobuf.InitRequest other) {
      if (other == com.marshmallow.snet.service.protobuf.InitRequest.getDefaultInstance()) return this;
      if (other.type_ != 0) {
        setTypeValue(other.getTypeValue());
      }
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
      com.marshmallow.snet.service.protobuf.InitRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.marshmallow.snet.service.protobuf.InitRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int type_ = 0;
    /**
     * <code>.ClientType type = 1;</code>
     */
    public int getTypeValue() {
      return type_;
    }
    /**
     * <code>.ClientType type = 1;</code>
     */
    public Builder setTypeValue(int value) {
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.ClientType type = 1;</code>
     */
    public com.marshmallow.snet.service.protobuf.ClientType getType() {
      com.marshmallow.snet.service.protobuf.ClientType result = com.marshmallow.snet.service.protobuf.ClientType.valueOf(type_);
      return result == null ? com.marshmallow.snet.service.protobuf.ClientType.UNRECOGNIZED : result;
    }
    /**
     * <code>.ClientType type = 1;</code>
     */
    public Builder setType(com.marshmallow.snet.service.protobuf.ClientType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      type_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.ClientType type = 1;</code>
     */
    public Builder clearType() {
      
      type_ = 0;
      onChanged();
      return this;
    }

    private int address_ ;
    /**
     * <code>int32 address = 2;</code>
     */
    public int getAddress() {
      return address_;
    }
    /**
     * <code>int32 address = 2;</code>
     */
    public Builder setAddress(int value) {
      
      address_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 address = 2;</code>
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


    // @@protoc_insertion_point(builder_scope:InitRequest)
  }

  // @@protoc_insertion_point(class_scope:InitRequest)
  private static final com.marshmallow.snet.service.protobuf.InitRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.marshmallow.snet.service.protobuf.InitRequest();
  }

  public static com.marshmallow.snet.service.protobuf.InitRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<InitRequest>
      PARSER = new com.google.protobuf.AbstractParser<InitRequest>() {
    public InitRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new InitRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<InitRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<InitRequest> getParserForType() {
    return PARSER;
  }

  public com.marshmallow.snet.service.protobuf.InitRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

