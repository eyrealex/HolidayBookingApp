// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: rental.proto

package com.alexeyre.grpc.rental;

public final class RentalServiceImpl {
  private RentalServiceImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_alexeyre_grpc_rental_RentalListRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_alexeyre_grpc_rental_RentalListRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_alexeyre_grpc_rental_RentalListResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_alexeyre_grpc_rental_RentalListResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_alexeyre_grpc_rental_RentalAvailabilityRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_alexeyre_grpc_rental_RentalAvailabilityRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_alexeyre_grpc_rental_RentalAvailabilityResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_alexeyre_grpc_rental_RentalAvailabilityResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_alexeyre_grpc_rental_RentalBookingDateRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_alexeyre_grpc_rental_RentalBookingDateRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_alexeyre_grpc_rental_RentalBookingDateResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_alexeyre_grpc_rental_RentalBookingDateResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_alexeyre_grpc_rental_RentalDetailsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_alexeyre_grpc_rental_RentalDetailsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_alexeyre_grpc_rental_RentalDetailsResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_alexeyre_grpc_rental_RentalDetailsResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014rental.proto\022\030com.alexeyre.grpc.rental" +
      "\"\"\n\021RentalListRequest\022\r\n\005empty\030\001 \001(\t\"%\n\022" +
      "RentalListResponse\022\017\n\007rentals\030\001 \001(\t\"*\n\031R" +
      "entalAvailabilityRequest\022\r\n\005empty\030\001 \001(\t\"" +
      "P\n\032RentalAvailabilityResponse\022\017\n\007carName" +
      "\030\001 \001(\t\022\017\n\007carType\030\002 \001(\t\022\020\n\010carModel\030\003 \001(" +
      "\t\"(\n\030RentalBookingDateRequest\022\014\n\004date\030\001 " +
      "\001(\t\")\n\031RentalBookingDateResponse\022\014\n\004date" +
      "\030\001 \001(\t\"J\n\024RentalDetailsRequest\022\017\n\007carNam" +
      "e\030\001 \001(\t\022\017\n\007carType\030\002 \001(\t\022\020\n\010carModel\030\003 \001" +
      "(\t\"K\n\025RentalDetailsResponse\022\017\n\007carName\030\001" +
      " \001(\t\022\017\n\007carType\030\002 \001(\t\022\020\n\010carModel\030\003 \001(\t2" +
      "\370\003\n\rRentalService\022k\n\nrentalList\022+.com.al" +
      "exeyre.grpc.rental.RentalListRequest\032,.c" +
      "om.alexeyre.grpc.rental.RentalListRespon" +
      "se\"\0000\001\022\203\001\n\022rentalAvailability\0223.com.alex" +
      "eyre.grpc.rental.RentalAvailabilityReque" +
      "st\0324.com.alexeyre.grpc.rental.RentalAvai" +
      "labilityResponse\"\0000\001\022|\n\rrentalBooking\0222." +
      "com.alexeyre.grpc.rental.RentalBookingDa" +
      "teRequest\0323.com.alexeyre.grpc.rental.Ren" +
      "talBookingDateResponse\"\000(\001\022v\n\rrentalDeta" +
      "ils\022..com.alexeyre.grpc.rental.RentalDet" +
      "ailsRequest\032/.com.alexeyre.grpc.rental.R" +
      "entalDetailsResponse\"\000(\0010\001B/\n\030com.alexey" +
      "re.grpc.rentalB\021RentalServiceImplP\001b\006pro" +
      "to3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_alexeyre_grpc_rental_RentalListRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_alexeyre_grpc_rental_RentalListRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_alexeyre_grpc_rental_RentalListRequest_descriptor,
        new java.lang.String[] { "Empty", });
    internal_static_com_alexeyre_grpc_rental_RentalListResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_alexeyre_grpc_rental_RentalListResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_alexeyre_grpc_rental_RentalListResponse_descriptor,
        new java.lang.String[] { "Rentals", });
    internal_static_com_alexeyre_grpc_rental_RentalAvailabilityRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_alexeyre_grpc_rental_RentalAvailabilityRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_alexeyre_grpc_rental_RentalAvailabilityRequest_descriptor,
        new java.lang.String[] { "Empty", });
    internal_static_com_alexeyre_grpc_rental_RentalAvailabilityResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_alexeyre_grpc_rental_RentalAvailabilityResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_alexeyre_grpc_rental_RentalAvailabilityResponse_descriptor,
        new java.lang.String[] { "CarName", "CarType", "CarModel", });
    internal_static_com_alexeyre_grpc_rental_RentalBookingDateRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_com_alexeyre_grpc_rental_RentalBookingDateRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_alexeyre_grpc_rental_RentalBookingDateRequest_descriptor,
        new java.lang.String[] { "Date", });
    internal_static_com_alexeyre_grpc_rental_RentalBookingDateResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_com_alexeyre_grpc_rental_RentalBookingDateResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_alexeyre_grpc_rental_RentalBookingDateResponse_descriptor,
        new java.lang.String[] { "Date", });
    internal_static_com_alexeyre_grpc_rental_RentalDetailsRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_com_alexeyre_grpc_rental_RentalDetailsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_alexeyre_grpc_rental_RentalDetailsRequest_descriptor,
        new java.lang.String[] { "CarName", "CarType", "CarModel", });
    internal_static_com_alexeyre_grpc_rental_RentalDetailsResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_com_alexeyre_grpc_rental_RentalDetailsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_alexeyre_grpc_rental_RentalDetailsResponse_descriptor,
        new java.lang.String[] { "CarName", "CarType", "CarModel", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}