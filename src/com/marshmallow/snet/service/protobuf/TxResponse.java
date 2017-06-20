// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protobuf/snet.proto

package com.marshmallow.snet.service.protobuf;

/**
 * Protobuf type {@code TxResponse}
 */
public  final class TxResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TxResponse)
    TxResponseOrBuilder {
  // Use TxResponse.newBuilder() to construct.
  private TxResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TxResponse() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private TxResponse(
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
    return com.marshmallow.snet.service.protobuf.Snet.internal_static_TxResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.marshmallow.snet.service.protobuf.Snet.internal_static_TxResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.marshmallow.snet.service.protobuf.TxResponse.class, com.marshmallow.snet.service.protobuf.TxResponse.Builder.class);
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
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (status_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getStatus());
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
    if (!(obj instanceof com.marshmallow.snet.service.protobuf.TxResponse)) {
      return super.equals(obj);
    }
    com.marshmallow.snet.service.protobuf.TxResponse other = (com.marshmallow.snet.service.protobuf.TxResponse) obj;

    boolean result = true;
    result = result && (hasStatus() == other.hasStatus());
    if (hasStatus()) {
      result = result && getStatus()
          .equals(other.getStatus());
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
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.marshmallow.snet.service.protobuf.TxResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.marshmallow.snet.service.protobuf.TxResponse parseFrom(
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
  public static Builder newBuilder(com.marshmallow.snet.service.protobuf.TxResponse prototype) {
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
   * Protobuf type {@code TxResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TxResponse)
      com.marshmallow.snet.service.protobuf.TxResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_TxResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_TxResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.marshmallow.snet.service.protobuf.TxResponse.class, com.marshmallow.snet.service.protobuf.TxResponse.Builder.class);
    }

    // Construct using com.marshmallow.snet.service.protobuf.TxResponse.newBuilder()
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
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.marshmallow.snet.service.protobuf.Snet.internal_static_TxResponse_descriptor;
    }

    public com.marshmallow.snet.service.protobuf.TxResponse getDefaultInstanceForType() {
      return com.marshmallow.snet.service.protobuf.TxResponse.getDefaultInstance();
    }

    public com.marshmallow.snet.service.protobuf.TxResponse build() {
      com.marshmallow.snet.service.protobuf.TxResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.marshmallow.snet.service.protobuf.TxResponse buildPartial() {
      com.marshmallow.snet.service.protobuf.TxResponse result = new com.marshmallow.snet.service.protobuf.TxResponse(this);
      if (statusBuilder_ == null) {
        result.status_ = status_;
      } else {
        result.status_ = statusBuilder_.build();
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
      if (other instanceof com.marshmallow.snet.service.protobuf.TxResponse) {
        return mergeFrom((com.marshmallow.snet.service.protobuf.TxResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.marshmallow.snet.service.protobuf.TxResponse other) {
      if (other == com.marshmallow.snet.service.protobuf.TxResponse.getDefaultInstance()) return this;
      if (other.hasStatus()) {
        mergeStatus(other.getStatus());
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
      com.marshmallow.snet.service.protobuf.TxResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.marshmallow.snet.service.protobuf.TxResponse) e.getUnfinishedMessage();
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
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:TxResponse)
  }

  // @@protoc_insertion_point(class_scope:TxResponse)
  private static final com.marshmallow.snet.service.protobuf.TxResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.marshmallow.snet.service.protobuf.TxResponse();
  }

  public static com.marshmallow.snet.service.protobuf.TxResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TxResponse>
      PARSER = new com.google.protobuf.AbstractParser<TxResponse>() {
    public TxResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TxResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TxResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TxResponse> getParserForType() {
    return PARSER;
  }

  public com.marshmallow.snet.service.protobuf.TxResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

