// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hotel.proto

package com.alexeyre.grpc.hotel;

/**
 * Protobuf type {@code com.alexeyre.grpc.hotel.HotelListResponse}
 */
public  final class HotelListResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.alexeyre.grpc.hotel.HotelListResponse)
    HotelListResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use HotelListResponse.newBuilder() to construct.
  private HotelListResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HotelListResponse() {
    hotels_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HotelListResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            hotels_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.alexeyre.grpc.hotel.HotelServiceImpl.internal_static_com_alexeyre_grpc_hotel_HotelListResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.alexeyre.grpc.hotel.HotelServiceImpl.internal_static_com_alexeyre_grpc_hotel_HotelListResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.alexeyre.grpc.hotel.HotelListResponse.class, com.alexeyre.grpc.hotel.HotelListResponse.Builder.class);
  }

  public static final int HOTELS_FIELD_NUMBER = 1;
  private volatile java.lang.Object hotels_;
  /**
   * <code>string hotels = 1;</code>
   */
  public java.lang.String getHotels() {
    java.lang.Object ref = hotels_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      hotels_ = s;
      return s;
    }
  }
  /**
   * <code>string hotels = 1;</code>
   */
  public com.google.protobuf.ByteString
      getHotelsBytes() {
    java.lang.Object ref = hotels_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      hotels_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getHotelsBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, hotels_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getHotelsBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, hotels_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.alexeyre.grpc.hotel.HotelListResponse)) {
      return super.equals(obj);
    }
    com.alexeyre.grpc.hotel.HotelListResponse other = (com.alexeyre.grpc.hotel.HotelListResponse) obj;

    boolean result = true;
    result = result && getHotels()
        .equals(other.getHotels());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + HOTELS_FIELD_NUMBER;
    hash = (53 * hash) + getHotels().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.alexeyre.grpc.hotel.HotelListResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.alexeyre.grpc.hotel.HotelListResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.alexeyre.grpc.hotel.HotelListResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   * Protobuf type {@code com.alexeyre.grpc.hotel.HotelListResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.alexeyre.grpc.hotel.HotelListResponse)
      com.alexeyre.grpc.hotel.HotelListResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.alexeyre.grpc.hotel.HotelServiceImpl.internal_static_com_alexeyre_grpc_hotel_HotelListResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.alexeyre.grpc.hotel.HotelServiceImpl.internal_static_com_alexeyre_grpc_hotel_HotelListResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.alexeyre.grpc.hotel.HotelListResponse.class, com.alexeyre.grpc.hotel.HotelListResponse.Builder.class);
    }

    // Construct using com.alexeyre.grpc.hotel.HotelListResponse.newBuilder()
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
    @java.lang.Override
    public Builder clear() {
      super.clear();
      hotels_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.alexeyre.grpc.hotel.HotelServiceImpl.internal_static_com_alexeyre_grpc_hotel_HotelListResponse_descriptor;
    }

    @java.lang.Override
    public com.alexeyre.grpc.hotel.HotelListResponse getDefaultInstanceForType() {
      return com.alexeyre.grpc.hotel.HotelListResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.alexeyre.grpc.hotel.HotelListResponse build() {
      com.alexeyre.grpc.hotel.HotelListResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.alexeyre.grpc.hotel.HotelListResponse buildPartial() {
      com.alexeyre.grpc.hotel.HotelListResponse result = new com.alexeyre.grpc.hotel.HotelListResponse(this);
      result.hotels_ = hotels_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.alexeyre.grpc.hotel.HotelListResponse) {
        return mergeFrom((com.alexeyre.grpc.hotel.HotelListResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.alexeyre.grpc.hotel.HotelListResponse other) {
      if (other == com.alexeyre.grpc.hotel.HotelListResponse.getDefaultInstance()) return this;
      if (!other.getHotels().isEmpty()) {
        hotels_ = other.hotels_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.alexeyre.grpc.hotel.HotelListResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.alexeyre.grpc.hotel.HotelListResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object hotels_ = "";
    /**
     * <code>string hotels = 1;</code>
     */
    public java.lang.String getHotels() {
      java.lang.Object ref = hotels_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        hotels_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string hotels = 1;</code>
     */
    public com.google.protobuf.ByteString
        getHotelsBytes() {
      java.lang.Object ref = hotels_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        hotels_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string hotels = 1;</code>
     */
    public Builder setHotels(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      hotels_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string hotels = 1;</code>
     */
    public Builder clearHotels() {
      
      hotels_ = getDefaultInstance().getHotels();
      onChanged();
      return this;
    }
    /**
     * <code>string hotels = 1;</code>
     */
    public Builder setHotelsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      hotels_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.alexeyre.grpc.hotel.HotelListResponse)
  }

  // @@protoc_insertion_point(class_scope:com.alexeyre.grpc.hotel.HotelListResponse)
  private static final com.alexeyre.grpc.hotel.HotelListResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.alexeyre.grpc.hotel.HotelListResponse();
  }

  public static com.alexeyre.grpc.hotel.HotelListResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<HotelListResponse>
      PARSER = new com.google.protobuf.AbstractParser<HotelListResponse>() {
    @java.lang.Override
    public HotelListResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new HotelListResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HotelListResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HotelListResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.alexeyre.grpc.hotel.HotelListResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
