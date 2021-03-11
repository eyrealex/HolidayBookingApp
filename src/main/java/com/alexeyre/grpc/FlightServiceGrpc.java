package com.alexeyre.grpc;

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
    comments = "Source: flight.proto")
public final class FlightServiceGrpc {

  private FlightServiceGrpc() {}

  public static final String SERVICE_NAME = "com.alexeyre.grpc.flight.FlightService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.ListRequest,
      com.alexeyre.grpc.ListResponse> getFlightListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "flightList",
      requestType = com.alexeyre.grpc.ListRequest.class,
      responseType = com.alexeyre.grpc.ListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.ListRequest,
      com.alexeyre.grpc.ListResponse> getFlightListMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.ListRequest, com.alexeyre.grpc.ListResponse> getFlightListMethod;
    if ((getFlightListMethod = FlightServiceGrpc.getFlightListMethod) == null) {
      synchronized (FlightServiceGrpc.class) {
        if ((getFlightListMethod = FlightServiceGrpc.getFlightListMethod) == null) {
          FlightServiceGrpc.getFlightListMethod = getFlightListMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.ListRequest, com.alexeyre.grpc.ListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.flight.FlightService", "flightList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.ListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.ListResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FlightServiceMethodDescriptorSupplier("flightList"))
                  .build();
          }
        }
     }
     return getFlightListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.BookingRequest,
      com.alexeyre.grpc.BookingResponse> getFlightBookingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "flightBooking",
      requestType = com.alexeyre.grpc.BookingRequest.class,
      responseType = com.alexeyre.grpc.BookingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.BookingRequest,
      com.alexeyre.grpc.BookingResponse> getFlightBookingMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.BookingRequest, com.alexeyre.grpc.BookingResponse> getFlightBookingMethod;
    if ((getFlightBookingMethod = FlightServiceGrpc.getFlightBookingMethod) == null) {
      synchronized (FlightServiceGrpc.class) {
        if ((getFlightBookingMethod = FlightServiceGrpc.getFlightBookingMethod) == null) {
          FlightServiceGrpc.getFlightBookingMethod = getFlightBookingMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.BookingRequest, com.alexeyre.grpc.BookingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.flight.FlightService", "flightBooking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.BookingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.BookingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FlightServiceMethodDescriptorSupplier("flightBooking"))
                  .build();
          }
        }
     }
     return getFlightBookingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FlightServiceStub newStub(io.grpc.Channel channel) {
    return new FlightServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FlightServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FlightServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FlightServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FlightServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class FlightServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void flightList(com.alexeyre.grpc.ListRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.ListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFlightListMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.BookingRequest> flightBooking(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.BookingResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getFlightBookingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFlightListMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.ListRequest,
                com.alexeyre.grpc.ListResponse>(
                  this, METHODID_FLIGHT_LIST)))
          .addMethod(
            getFlightBookingMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.BookingRequest,
                com.alexeyre.grpc.BookingResponse>(
                  this, METHODID_FLIGHT_BOOKING)))
          .build();
    }
  }

  /**
   */
  public static final class FlightServiceStub extends io.grpc.stub.AbstractStub<FlightServiceStub> {
    private FlightServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FlightServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlightServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FlightServiceStub(channel, callOptions);
    }

    /**
     */
    public void flightList(com.alexeyre.grpc.ListRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.ListResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getFlightListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.BookingRequest> flightBooking(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.BookingResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getFlightBookingMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class FlightServiceBlockingStub extends io.grpc.stub.AbstractStub<FlightServiceBlockingStub> {
    private FlightServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FlightServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlightServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FlightServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.alexeyre.grpc.ListResponse> flightList(
        com.alexeyre.grpc.ListRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getFlightListMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FlightServiceFutureStub extends io.grpc.stub.AbstractStub<FlightServiceFutureStub> {
    private FlightServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FlightServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlightServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FlightServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_FLIGHT_LIST = 0;
  private static final int METHODID_FLIGHT_BOOKING = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FlightServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FlightServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FLIGHT_LIST:
          serviceImpl.flightList((com.alexeyre.grpc.ListRequest) request,
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.ListResponse>) responseObserver);
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
        case METHODID_FLIGHT_BOOKING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.flightBooking(
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.BookingResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FlightServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FlightServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.alexeyre.grpc.FlightServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FlightService");
    }
  }

  private static final class FlightServiceFileDescriptorSupplier
      extends FlightServiceBaseDescriptorSupplier {
    FlightServiceFileDescriptorSupplier() {}
  }

  private static final class FlightServiceMethodDescriptorSupplier
      extends FlightServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FlightServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (FlightServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FlightServiceFileDescriptorSupplier())
              .addMethod(getFlightListMethod())
              .addMethod(getFlightBookingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
