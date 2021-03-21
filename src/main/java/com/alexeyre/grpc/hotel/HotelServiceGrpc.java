package com.alexeyre.grpc.hotel;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: hotel.proto")
public final class HotelServiceGrpc {

  private HotelServiceGrpc() {}

  public static final String SERVICE_NAME = "com.alexeyre.grpc.hotel.HotelService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.hotel.HotelListRequest,
      com.alexeyre.grpc.hotel.HotelListResponse> getHotelListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "hotelList",
      requestType = com.alexeyre.grpc.hotel.HotelListRequest.class,
      responseType = com.alexeyre.grpc.hotel.HotelListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.hotel.HotelListRequest,
      com.alexeyre.grpc.hotel.HotelListResponse> getHotelListMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.hotel.HotelListRequest, com.alexeyre.grpc.hotel.HotelListResponse> getHotelListMethod;
    if ((getHotelListMethod = HotelServiceGrpc.getHotelListMethod) == null) {
      synchronized (HotelServiceGrpc.class) {
        if ((getHotelListMethod = HotelServiceGrpc.getHotelListMethod) == null) {
          HotelServiceGrpc.getHotelListMethod = getHotelListMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.hotel.HotelListRequest, com.alexeyre.grpc.hotel.HotelListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.hotel.HotelService", "hotelList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.hotel.HotelListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.hotel.HotelListResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new HotelServiceMethodDescriptorSupplier("hotelList"))
                  .build();
          }
        }
     }
     return getHotelListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.hotel.HotelBookingRequest,
      com.alexeyre.grpc.hotel.HotelBookingResponse> getHotelBookingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "hotelBooking",
      requestType = com.alexeyre.grpc.hotel.HotelBookingRequest.class,
      responseType = com.alexeyre.grpc.hotel.HotelBookingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.hotel.HotelBookingRequest,
      com.alexeyre.grpc.hotel.HotelBookingResponse> getHotelBookingMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.hotel.HotelBookingRequest, com.alexeyre.grpc.hotel.HotelBookingResponse> getHotelBookingMethod;
    if ((getHotelBookingMethod = HotelServiceGrpc.getHotelBookingMethod) == null) {
      synchronized (HotelServiceGrpc.class) {
        if ((getHotelBookingMethod = HotelServiceGrpc.getHotelBookingMethod) == null) {
          HotelServiceGrpc.getHotelBookingMethod = getHotelBookingMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.hotel.HotelBookingRequest, com.alexeyre.grpc.hotel.HotelBookingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.hotel.HotelService", "hotelBooking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.hotel.HotelBookingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.hotel.HotelBookingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new HotelServiceMethodDescriptorSupplier("hotelBooking"))
                  .build();
          }
        }
     }
     return getHotelBookingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.hotel.HotelAmenitiesRequest,
      com.alexeyre.grpc.hotel.HotelAmenitiesResponse> getHotelAmenitiesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "hotelAmenities",
      requestType = com.alexeyre.grpc.hotel.HotelAmenitiesRequest.class,
      responseType = com.alexeyre.grpc.hotel.HotelAmenitiesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.hotel.HotelAmenitiesRequest,
      com.alexeyre.grpc.hotel.HotelAmenitiesResponse> getHotelAmenitiesMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.hotel.HotelAmenitiesRequest, com.alexeyre.grpc.hotel.HotelAmenitiesResponse> getHotelAmenitiesMethod;
    if ((getHotelAmenitiesMethod = HotelServiceGrpc.getHotelAmenitiesMethod) == null) {
      synchronized (HotelServiceGrpc.class) {
        if ((getHotelAmenitiesMethod = HotelServiceGrpc.getHotelAmenitiesMethod) == null) {
          HotelServiceGrpc.getHotelAmenitiesMethod = getHotelAmenitiesMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.hotel.HotelAmenitiesRequest, com.alexeyre.grpc.hotel.HotelAmenitiesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.hotel.HotelService", "hotelAmenities"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.hotel.HotelAmenitiesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.hotel.HotelAmenitiesResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new HotelServiceMethodDescriptorSupplier("hotelAmenities"))
                  .build();
          }
        }
     }
     return getHotelAmenitiesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HotelServiceStub newStub(io.grpc.Channel channel) {
    return new HotelServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HotelServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HotelServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HotelServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HotelServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class HotelServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void hotelList(com.alexeyre.grpc.hotel.HotelListRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHotelListMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelBookingRequest> hotelBooking(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelBookingResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getHotelBookingMethod(), responseObserver);
    }

    /**
     */
    public void hotelAmenities(com.alexeyre.grpc.hotel.HotelAmenitiesRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelAmenitiesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHotelAmenitiesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHotelListMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.hotel.HotelListRequest,
                com.alexeyre.grpc.hotel.HotelListResponse>(
                  this, METHODID_HOTEL_LIST)))
          .addMethod(
            getHotelBookingMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.hotel.HotelBookingRequest,
                com.alexeyre.grpc.hotel.HotelBookingResponse>(
                  this, METHODID_HOTEL_BOOKING)))
          .addMethod(
            getHotelAmenitiesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.alexeyre.grpc.hotel.HotelAmenitiesRequest,
                com.alexeyre.grpc.hotel.HotelAmenitiesResponse>(
                  this, METHODID_HOTEL_AMENITIES)))
          .build();
    }
  }

  /**
   */
  public static final class HotelServiceStub extends io.grpc.stub.AbstractStub<HotelServiceStub> {
    private HotelServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HotelServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HotelServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HotelServiceStub(channel, callOptions);
    }

    /**
     */
    public void hotelList(com.alexeyre.grpc.hotel.HotelListRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelListResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getHotelListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelBookingRequest> hotelBooking(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelBookingResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getHotelBookingMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void hotelAmenities(com.alexeyre.grpc.hotel.HotelAmenitiesRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelAmenitiesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHotelAmenitiesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class HotelServiceBlockingStub extends io.grpc.stub.AbstractStub<HotelServiceBlockingStub> {
    private HotelServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HotelServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HotelServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HotelServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.alexeyre.grpc.hotel.HotelListResponse> hotelList(
        com.alexeyre.grpc.hotel.HotelListRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getHotelListMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.alexeyre.grpc.hotel.HotelAmenitiesResponse hotelAmenities(com.alexeyre.grpc.hotel.HotelAmenitiesRequest request) {
      return blockingUnaryCall(
          getChannel(), getHotelAmenitiesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HotelServiceFutureStub extends io.grpc.stub.AbstractStub<HotelServiceFutureStub> {
    private HotelServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HotelServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HotelServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HotelServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.alexeyre.grpc.hotel.HotelAmenitiesResponse> hotelAmenities(
        com.alexeyre.grpc.hotel.HotelAmenitiesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHotelAmenitiesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HOTEL_LIST = 0;
  private static final int METHODID_HOTEL_AMENITIES = 1;
  private static final int METHODID_HOTEL_BOOKING = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HotelServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HotelServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HOTEL_LIST:
          serviceImpl.hotelList((com.alexeyre.grpc.hotel.HotelListRequest) request,
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelListResponse>) responseObserver);
          break;
        case METHODID_HOTEL_AMENITIES:
          serviceImpl.hotelAmenities((com.alexeyre.grpc.hotel.HotelAmenitiesRequest) request,
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelAmenitiesResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HOTEL_BOOKING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.hotelBooking(
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.hotel.HotelBookingResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HotelServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HotelServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.alexeyre.grpc.hotel.HotelServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HotelService");
    }
  }

  private static final class HotelServiceFileDescriptorSupplier
      extends HotelServiceBaseDescriptorSupplier {
    HotelServiceFileDescriptorSupplier() {}
  }

  private static final class HotelServiceMethodDescriptorSupplier
      extends HotelServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HotelServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HotelServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HotelServiceFileDescriptorSupplier())
              .addMethod(getHotelListMethod())
              .addMethod(getHotelBookingMethod())
              .addMethod(getHotelAmenitiesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
