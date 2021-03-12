package com.alexeyre.grpc.rental;

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
    comments = "Source: rental.proto")
public final class RentalServiceGrpc {

  private RentalServiceGrpc() {}

  public static final String SERVICE_NAME = "com.alexeyre.grpc.rental.RentalService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalListRequest,
      com.alexeyre.grpc.rental.RentalListResponse> getRentalListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rentalList",
      requestType = com.alexeyre.grpc.rental.RentalListRequest.class,
      responseType = com.alexeyre.grpc.rental.RentalListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalListRequest,
      com.alexeyre.grpc.rental.RentalListResponse> getRentalListMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalListRequest, com.alexeyre.grpc.rental.RentalListResponse> getRentalListMethod;
    if ((getRentalListMethod = RentalServiceGrpc.getRentalListMethod) == null) {
      synchronized (RentalServiceGrpc.class) {
        if ((getRentalListMethod = RentalServiceGrpc.getRentalListMethod) == null) {
          RentalServiceGrpc.getRentalListMethod = getRentalListMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.rental.RentalListRequest, com.alexeyre.grpc.rental.RentalListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.rental.RentalService", "rentalList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.rental.RentalListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.rental.RentalListResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RentalServiceMethodDescriptorSupplier("rentalList"))
                  .build();
          }
        }
     }
     return getRentalListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalAvailabilityRequest,
      com.alexeyre.grpc.rental.RentalAvailabilityResponse> getRentalAvailabilityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rentalAvailability",
      requestType = com.alexeyre.grpc.rental.RentalAvailabilityRequest.class,
      responseType = com.alexeyre.grpc.rental.RentalAvailabilityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalAvailabilityRequest,
      com.alexeyre.grpc.rental.RentalAvailabilityResponse> getRentalAvailabilityMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalAvailabilityRequest, com.alexeyre.grpc.rental.RentalAvailabilityResponse> getRentalAvailabilityMethod;
    if ((getRentalAvailabilityMethod = RentalServiceGrpc.getRentalAvailabilityMethod) == null) {
      synchronized (RentalServiceGrpc.class) {
        if ((getRentalAvailabilityMethod = RentalServiceGrpc.getRentalAvailabilityMethod) == null) {
          RentalServiceGrpc.getRentalAvailabilityMethod = getRentalAvailabilityMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.rental.RentalAvailabilityRequest, com.alexeyre.grpc.rental.RentalAvailabilityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.rental.RentalService", "rentalAvailability"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.rental.RentalAvailabilityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.rental.RentalAvailabilityResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RentalServiceMethodDescriptorSupplier("rentalAvailability"))
                  .build();
          }
        }
     }
     return getRentalAvailabilityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalBookingDateRequest,
      com.alexeyre.grpc.rental.RentalBookingDateResponse> getRentalBookingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rentalBooking",
      requestType = com.alexeyre.grpc.rental.RentalBookingDateRequest.class,
      responseType = com.alexeyre.grpc.rental.RentalBookingDateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalBookingDateRequest,
      com.alexeyre.grpc.rental.RentalBookingDateResponse> getRentalBookingMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalBookingDateRequest, com.alexeyre.grpc.rental.RentalBookingDateResponse> getRentalBookingMethod;
    if ((getRentalBookingMethod = RentalServiceGrpc.getRentalBookingMethod) == null) {
      synchronized (RentalServiceGrpc.class) {
        if ((getRentalBookingMethod = RentalServiceGrpc.getRentalBookingMethod) == null) {
          RentalServiceGrpc.getRentalBookingMethod = getRentalBookingMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.rental.RentalBookingDateRequest, com.alexeyre.grpc.rental.RentalBookingDateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.rental.RentalService", "rentalBooking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.rental.RentalBookingDateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.rental.RentalBookingDateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RentalServiceMethodDescriptorSupplier("rentalBooking"))
                  .build();
          }
        }
     }
     return getRentalBookingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalDetailsRequest,
      com.alexeyre.grpc.rental.RentalDetailsResponse> getRentalDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rentalDetails",
      requestType = com.alexeyre.grpc.rental.RentalDetailsRequest.class,
      responseType = com.alexeyre.grpc.rental.RentalDetailsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalDetailsRequest,
      com.alexeyre.grpc.rental.RentalDetailsResponse> getRentalDetailsMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.rental.RentalDetailsRequest, com.alexeyre.grpc.rental.RentalDetailsResponse> getRentalDetailsMethod;
    if ((getRentalDetailsMethod = RentalServiceGrpc.getRentalDetailsMethod) == null) {
      synchronized (RentalServiceGrpc.class) {
        if ((getRentalDetailsMethod = RentalServiceGrpc.getRentalDetailsMethod) == null) {
          RentalServiceGrpc.getRentalDetailsMethod = getRentalDetailsMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.rental.RentalDetailsRequest, com.alexeyre.grpc.rental.RentalDetailsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.rental.RentalService", "rentalDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.rental.RentalDetailsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.rental.RentalDetailsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RentalServiceMethodDescriptorSupplier("rentalDetails"))
                  .build();
          }
        }
     }
     return getRentalDetailsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RentalServiceStub newStub(io.grpc.Channel channel) {
    return new RentalServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RentalServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RentalServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RentalServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RentalServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RentalServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void rentalList(com.alexeyre.grpc.rental.RentalListRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRentalListMethod(), responseObserver);
    }

    /**
     */
    public void rentalAvailability(com.alexeyre.grpc.rental.RentalAvailabilityRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalAvailabilityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRentalAvailabilityMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalBookingDateRequest> rentalBooking(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalBookingDateResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getRentalBookingMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalDetailsRequest> rentalDetails(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalDetailsResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getRentalDetailsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRentalListMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.rental.RentalListRequest,
                com.alexeyre.grpc.rental.RentalListResponse>(
                  this, METHODID_RENTAL_LIST)))
          .addMethod(
            getRentalAvailabilityMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.rental.RentalAvailabilityRequest,
                com.alexeyre.grpc.rental.RentalAvailabilityResponse>(
                  this, METHODID_RENTAL_AVAILABILITY)))
          .addMethod(
            getRentalBookingMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.rental.RentalBookingDateRequest,
                com.alexeyre.grpc.rental.RentalBookingDateResponse>(
                  this, METHODID_RENTAL_BOOKING)))
          .addMethod(
            getRentalDetailsMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.rental.RentalDetailsRequest,
                com.alexeyre.grpc.rental.RentalDetailsResponse>(
                  this, METHODID_RENTAL_DETAILS)))
          .build();
    }
  }

  /**
   */
  public static final class RentalServiceStub extends io.grpc.stub.AbstractStub<RentalServiceStub> {
    private RentalServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RentalServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RentalServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RentalServiceStub(channel, callOptions);
    }

    /**
     */
    public void rentalList(com.alexeyre.grpc.rental.RentalListRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalListResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getRentalListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rentalAvailability(com.alexeyre.grpc.rental.RentalAvailabilityRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalAvailabilityResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getRentalAvailabilityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalBookingDateRequest> rentalBooking(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalBookingDateResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getRentalBookingMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalDetailsRequest> rentalDetails(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalDetailsResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getRentalDetailsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class RentalServiceBlockingStub extends io.grpc.stub.AbstractStub<RentalServiceBlockingStub> {
    private RentalServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RentalServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RentalServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RentalServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.alexeyre.grpc.rental.RentalListResponse> rentalList(
        com.alexeyre.grpc.rental.RentalListRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getRentalListMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.alexeyre.grpc.rental.RentalAvailabilityResponse> rentalAvailability(
        com.alexeyre.grpc.rental.RentalAvailabilityRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getRentalAvailabilityMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RentalServiceFutureStub extends io.grpc.stub.AbstractStub<RentalServiceFutureStub> {
    private RentalServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RentalServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RentalServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RentalServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_RENTAL_LIST = 0;
  private static final int METHODID_RENTAL_AVAILABILITY = 1;
  private static final int METHODID_RENTAL_BOOKING = 2;
  private static final int METHODID_RENTAL_DETAILS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RentalServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RentalServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RENTAL_LIST:
          serviceImpl.rentalList((com.alexeyre.grpc.rental.RentalListRequest) request,
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalListResponse>) responseObserver);
          break;
        case METHODID_RENTAL_AVAILABILITY:
          serviceImpl.rentalAvailability((com.alexeyre.grpc.rental.RentalAvailabilityRequest) request,
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalAvailabilityResponse>) responseObserver);
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
        case METHODID_RENTAL_BOOKING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.rentalBooking(
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalBookingDateResponse>) responseObserver);
        case METHODID_RENTAL_DETAILS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.rentalDetails(
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.rental.RentalDetailsResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RentalServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RentalServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.alexeyre.grpc.rental.RentalServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RentalService");
    }
  }

  private static final class RentalServiceFileDescriptorSupplier
      extends RentalServiceBaseDescriptorSupplier {
    RentalServiceFileDescriptorSupplier() {}
  }

  private static final class RentalServiceMethodDescriptorSupplier
      extends RentalServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RentalServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RentalServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RentalServiceFileDescriptorSupplier())
              .addMethod(getRentalListMethod())
              .addMethod(getRentalAvailabilityMethod())
              .addMethod(getRentalBookingMethod())
              .addMethod(getRentalDetailsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
