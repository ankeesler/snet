// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: snet.proto

package com.marshmallow.snet.service.protobuf;

/**
 * Protobuf enum {@code Status}
 */
public enum Status
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>SUCCESS = 0;</code>
   */
  SUCCESS(0),
  /**
   * <code>FAILURE = 1;</code>
   */
  FAILURE(1),
  /**
   * <code>UNKNOWN = 2;</code>
   */
  UNKNOWN(2),
  /**
   * <code>EMPTY = 3;</code>
   */
  EMPTY(3),
  /**
   * <code>DUPLICATE = 4;</code>
   */
  DUPLICATE(4),
  /**
   * <code>BADTYPE = 5;</code>
   */
  BADTYPE(5),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>SUCCESS = 0;</code>
   */
  public static final int SUCCESS_VALUE = 0;
  /**
   * <code>FAILURE = 1;</code>
   */
  public static final int FAILURE_VALUE = 1;
  /**
   * <code>UNKNOWN = 2;</code>
   */
  public static final int UNKNOWN_VALUE = 2;
  /**
   * <code>EMPTY = 3;</code>
   */
  public static final int EMPTY_VALUE = 3;
  /**
   * <code>DUPLICATE = 4;</code>
   */
  public static final int DUPLICATE_VALUE = 4;
  /**
   * <code>BADTYPE = 5;</code>
   */
  public static final int BADTYPE_VALUE = 5;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static Status valueOf(int value) {
    return forNumber(value);
  }

  public static Status forNumber(int value) {
    switch (value) {
      case 0: return SUCCESS;
      case 1: return FAILURE;
      case 2: return UNKNOWN;
      case 3: return EMPTY;
      case 4: return DUPLICATE;
      case 5: return BADTYPE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Status>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Status> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Status>() {
          public Status findValueByNumber(int number) {
            return Status.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.marshmallow.snet.service.protobuf.Snet.getDescriptor()
        .getEnumTypes().get(0);
  }

  private static final Status[] VALUES = values();

  public static Status valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private Status(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:Status)
}

