// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: flight.proto

package com.alexeyre.grpc;

/**
 * Protobuf type {@code com.alexeyre.grpc.flight.PeopleRequest}
 */
public  final class PeopleRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.alexeyre.grpc.flight.PeopleRequest)
    PeopleRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PeopleRequest.newBuilder() to construct.
  private PeopleRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PeopleRequest() {
    passengers_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PeopleRequest(
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
          case 8: {

            passengers_ = input.readInt32();
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
    return com.alexeyre.grpc.FlightServiceImpl.internal_static_com_alexeyre_grpc_flight_PeopleRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.alexeyre.grpc.FlightServiceImpl.internal_static_com_alexeyre_grpc_flight_PeopleRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.alexeyre.grpc.PeopleRequest.class, com.alexeyre.grpc.PeopleRequest.Builder.class);
  }

  public static final int PASSENGERS_FIELD_NUMBER = 1;
  private int passengers_;
  /**
   * <code>int32 passengers = 1;</code>
   */
  public int getPassengers() {
    return passengers_;
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
    if (passengers_ != 0) {
      output.writeInt32(1, passengers_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (passengers_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, passengers_);
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
    if (!(obj instanceof com.alexeyre.grpc.PeopleRequest)) {
      return super.equals(obj);
    }
    com.alexeyre.grpc.PeopleRequest other = (com.alexeyre.grpc.PeopleRequest) obj;

    boolean result = true;
    result = result && (getPassengers()
        == other.getPassengers());
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
    hash = (37 * hash) + PASSENGERS_FIELD_NUMBER;
    hash = (53 * hash) + getPassengers();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.alexeyre.grpc.PeopleRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.alexeyre.grpc.PeopleRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.alexeyre.grpc.PeopleRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.alexeyre.grpc.PeopleRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.alexeyre.grpc.PeopleRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.alexeyre.grpc.PeopleRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.alexeyre.grpc.PeopleRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.alexeyre.grpc.PeopleRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.alexeyre.grpc.PeopleRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.alexeyre.grpc.PeopleRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.alexeyre.grpc.PeopleRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.alexeyre.grpc.PeopleRequest parseFrom(
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
  public static Builder newBuilder(com.alexeyre.grpc.PeopleRequest prototype) {
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
   * Protobuf type {@code com.alexeyre.grpc.flight.PeopleRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.alexeyre.grpc.flight.PeopleRequest)
      com.alexeyre.grpc.PeopleRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.alexeyre.grpc.FlightServiceImpl.internal_static_com_alexeyre_grpc_flight_PeopleRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.alexeyre.grpc.FlightServiceImpl.internal_static_com_alexeyre_grpc_flight_PeopleRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.alexeyre.grpc.PeopleRequest.class, com.alexeyre.grpc.PeopleRequest.Builder.class);
    }

    // Construct using com.alexeyre.grpc.PeopleRequest.newBuilder()
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
      passengers_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.alexeyre.grpc.FlightServiceImpl.internal_static_com_alexeyre_grpc_flight_PeopleRequest_descriptor;
    }

    @java.lang.Override
    public com.alexeyre.grpc.PeopleRequest getDefaultInstanceForType() {
      return com.alexeyre.grpc.PeopleRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.alexeyre.grpc.PeopleRequest build() {
      com.alexeyre.grpc.PeopleRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.alexeyre.grpc.PeopleRequest buildPartial() {
      com.alexeyre.grpc.PeopleRequest result = new com.alexeyre.grpc.PeopleRequest(this);
      result.passengers_ = passengers_;
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
      if (other instanceof com.alexeyre.grpc.PeopleRequest) {
        return mergeFrom((com.alexeyre.grpc.PeopleRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.alexeyre.grpc.PeopleRequest other) {
      if (other == com.alexeyre.grpc.PeopleRequest.getDefaultInstance()) return this;
      if (other.getPassengers() != 0) {
        setPassengers(other.getPassengers());
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
      com.alexeyre.grpc.PeopleRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.alexeyre.grpc.PeopleRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int passengers_ ;
    /**
     * <code>int32 passengers = 1;</code>
     */
    public int getPassengers() {
      return passengers_;
    }
    /**
     * <code>int32 passengers = 1;</code>
     */
    public Builder setPassengers(int value) {
      
      passengers_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 passengers = 1;</code>
     */
    public Builder clearPassengers() {
      
      passengers_ = 0;
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


    // @@protoc_insertion_point(builder_scope:com.alexeyre.grpc.flight.PeopleRequest)
  }

  // @@protoc_insertion_point(class_scope:com.alexeyre.grpc.flight.PeopleRequest)
  private static final com.alexeyre.grpc.PeopleRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.alexeyre.grpc.PeopleRequest();
  }

  public static com.alexeyre.grpc.PeopleRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PeopleRequest>
      PARSER = new com.google.protobuf.AbstractParser<PeopleRequest>() {
    @java.lang.Override
    public PeopleRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new PeopleRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PeopleRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PeopleRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.alexeyre.grpc.PeopleRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
