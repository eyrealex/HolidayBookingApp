package com.alexeyre.grpc.flight;

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
  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.ListRequest,
      com.alexeyre.grpc.flight.ListResponse> getFlightListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "flightList",
      requestType = com.alexeyre.grpc.flight.ListRequest.class,
      responseType = com.alexeyre.grpc.flight.ListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.ListRequest,
      com.alexeyre.grpc.flight.ListResponse> getFlightListMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.ListRequest, com.alexeyre.grpc.flight.ListResponse> getFlightListMethod;
    if ((getFlightListMethod = FlightServiceGrpc.getFlightListMethod) == null) {
      synchronized (FlightServiceGrpc.class) {
        if ((getFlightListMethod = FlightServiceGrpc.getFlightListMethod) == null) {
          FlightServiceGrpc.getFlightListMethod = getFlightListMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.flight.ListRequest, com.alexeyre.grpc.flight.ListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.flight.FlightService", "flightList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.ListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.ListResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FlightServiceMethodDescriptorSupplier("flightList"))
                  .build();
          }
        }
     }
     return getFlightListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.SearchRequest,
      com.alexeyre.grpc.flight.SearchResponse> getFlightSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "flightSearch",
      requestType = com.alexeyre.grpc.flight.SearchRequest.class,
      responseType = com.alexeyre.grpc.flight.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.SearchRequest,
      com.alexeyre.grpc.flight.SearchResponse> getFlightSearchMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.SearchRequest, com.alexeyre.grpc.flight.SearchResponse> getFlightSearchMethod;
    if ((getFlightSearchMethod = FlightServiceGrpc.getFlightSearchMethod) == null) {
      synchronized (FlightServiceGrpc.class) {
        if ((getFlightSearchMethod = FlightServiceGrpc.getFlightSearchMethod) == null) {
          FlightServiceGrpc.getFlightSearchMethod = getFlightSearchMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.flight.SearchRequest, com.alexeyre.grpc.flight.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.flight.FlightService", "flightSearch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.SearchResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FlightServiceMethodDescriptorSupplier("flightSearch"))
                  .build();
          }
        }
     }
     return getFlightSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.DetailsRequest,
      com.alexeyre.grpc.flight.DetailsResponse> getFlightDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "flightDetails",
      requestType = com.alexeyre.grpc.flight.DetailsRequest.class,
      responseType = com.alexeyre.grpc.flight.DetailsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.DetailsRequest,
      com.alexeyre.grpc.flight.DetailsResponse> getFlightDetailsMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.DetailsRequest, com.alexeyre.grpc.flight.DetailsResponse> getFlightDetailsMethod;
    if ((getFlightDetailsMethod = FlightServiceGrpc.getFlightDetailsMethod) == null) {
      synchronized (FlightServiceGrpc.class) {
        if ((getFlightDetailsMethod = FlightServiceGrpc.getFlightDetailsMethod) == null) {
          FlightServiceGrpc.getFlightDetailsMethod = getFlightDetailsMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.flight.DetailsRequest, com.alexeyre.grpc.flight.DetailsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.flight.FlightService", "flightDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.DetailsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.DetailsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FlightServiceMethodDescriptorSupplier("flightDetails"))
                  .build();
          }
        }
     }
     return getFlightDetailsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.NumberRequest,
      com.alexeyre.grpc.flight.NumberResponse> getFlightNumberMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "flightNumber",
      requestType = com.alexeyre.grpc.flight.NumberRequest.class,
      responseType = com.alexeyre.grpc.flight.NumberResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.NumberRequest,
      com.alexeyre.grpc.flight.NumberResponse> getFlightNumberMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.NumberRequest, com.alexeyre.grpc.flight.NumberResponse> getFlightNumberMethod;
    if ((getFlightNumberMethod = FlightServiceGrpc.getFlightNumberMethod) == null) {
      synchronized (FlightServiceGrpc.class) {
        if ((getFlightNumberMethod = FlightServiceGrpc.getFlightNumberMethod) == null) {
          FlightServiceGrpc.getFlightNumberMethod = getFlightNumberMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.flight.NumberRequest, com.alexeyre.grpc.flight.NumberResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.flight.FlightService", "flightNumber"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.NumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.NumberResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FlightServiceMethodDescriptorSupplier("flightNumber"))
                  .build();
          }
        }
     }
     return getFlightNumberMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.BookingRequest,
      com.alexeyre.grpc.flight.BookingResponse> getFlightBookingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "flightBooking",
      requestType = com.alexeyre.grpc.flight.BookingRequest.class,
      responseType = com.alexeyre.grpc.flight.BookingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.BookingRequest,
      com.alexeyre.grpc.flight.BookingResponse> getFlightBookingMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.BookingRequest, com.alexeyre.grpc.flight.BookingResponse> getFlightBookingMethod;
    if ((getFlightBookingMethod = FlightServiceGrpc.getFlightBookingMethod) == null) {
      synchronized (FlightServiceGrpc.class) {
        if ((getFlightBookingMethod = FlightServiceGrpc.getFlightBookingMethod) == null) {
          FlightServiceGrpc.getFlightBookingMethod = getFlightBookingMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.flight.BookingRequest, com.alexeyre.grpc.flight.BookingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.flight.FlightService", "flightBooking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.BookingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.BookingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FlightServiceMethodDescriptorSupplier("flightBooking"))
                  .build();
          }
        }
     }
     return getFlightBookingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.DisplayRequest,
      com.alexeyre.grpc.flight.DisplayResponse> getFlightDisplayMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "flightDisplay",
      requestType = com.alexeyre.grpc.flight.DisplayRequest.class,
      responseType = com.alexeyre.grpc.flight.DisplayResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.DisplayRequest,
      com.alexeyre.grpc.flight.DisplayResponse> getFlightDisplayMethod() {
    io.grpc.MethodDescriptor<com.alexeyre.grpc.flight.DisplayRequest, com.alexeyre.grpc.flight.DisplayResponse> getFlightDisplayMethod;
    if ((getFlightDisplayMethod = FlightServiceGrpc.getFlightDisplayMethod) == null) {
      synchronized (FlightServiceGrpc.class) {
        if ((getFlightDisplayMethod = FlightServiceGrpc.getFlightDisplayMethod) == null) {
          FlightServiceGrpc.getFlightDisplayMethod = getFlightDisplayMethod = 
              io.grpc.MethodDescriptor.<com.alexeyre.grpc.flight.DisplayRequest, com.alexeyre.grpc.flight.DisplayResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.alexeyre.grpc.flight.FlightService", "flightDisplay"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.DisplayRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.alexeyre.grpc.flight.DisplayResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FlightServiceMethodDescriptorSupplier("flightDisplay"))
                  .build();
          }
        }
     }
     return getFlightDisplayMethod;
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
    public void flightList(com.alexeyre.grpc.flight.ListRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.ListResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFlightListMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.SearchRequest> flightSearch(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.SearchResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getFlightSearchMethod(), responseObserver);
    }

    /**
     */
    public void flightDetails(com.alexeyre.grpc.flight.DetailsRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.DetailsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFlightDetailsMethod(), responseObserver);
    }

    /**
     */
    public void flightNumber(com.alexeyre.grpc.flight.NumberRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.NumberResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFlightNumberMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.BookingRequest> flightBooking(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.BookingResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getFlightBookingMethod(), responseObserver);
    }

    /**
     */
    public void flightDisplay(com.alexeyre.grpc.flight.DisplayRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.DisplayResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFlightDisplayMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFlightListMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.flight.ListRequest,
                com.alexeyre.grpc.flight.ListResponse>(
                  this, METHODID_FLIGHT_LIST)))
          .addMethod(
            getFlightSearchMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.flight.SearchRequest,
                com.alexeyre.grpc.flight.SearchResponse>(
                  this, METHODID_FLIGHT_SEARCH)))
          .addMethod(
            getFlightDetailsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.flight.DetailsRequest,
                com.alexeyre.grpc.flight.DetailsResponse>(
                  this, METHODID_FLIGHT_DETAILS)))
          .addMethod(
            getFlightNumberMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.alexeyre.grpc.flight.NumberRequest,
                com.alexeyre.grpc.flight.NumberResponse>(
                  this, METHODID_FLIGHT_NUMBER)))
          .addMethod(
            getFlightBookingMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.flight.BookingRequest,
                com.alexeyre.grpc.flight.BookingResponse>(
                  this, METHODID_FLIGHT_BOOKING)))
          .addMethod(
            getFlightDisplayMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.alexeyre.grpc.flight.DisplayRequest,
                com.alexeyre.grpc.flight.DisplayResponse>(
                  this, METHODID_FLIGHT_DISPLAY)))
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
    public void flightList(com.alexeyre.grpc.flight.ListRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.ListResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getFlightListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.SearchRequest> flightSearch(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.SearchResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getFlightSearchMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void flightDetails(com.alexeyre.grpc.flight.DetailsRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.DetailsResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getFlightDetailsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void flightNumber(com.alexeyre.grpc.flight.NumberRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.NumberResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFlightNumberMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.BookingRequest> flightBooking(
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.BookingResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getFlightBookingMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void flightDisplay(com.alexeyre.grpc.flight.DisplayRequest request,
        io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.DisplayResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getFlightDisplayMethod(), getCallOptions()), request, responseObserver);
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
    public java.util.Iterator<com.alexeyre.grpc.flight.ListResponse> flightList(
        com.alexeyre.grpc.flight.ListRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getFlightListMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.alexeyre.grpc.flight.DetailsResponse> flightDetails(
        com.alexeyre.grpc.flight.DetailsRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getFlightDetailsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.alexeyre.grpc.flight.NumberResponse flightNumber(com.alexeyre.grpc.flight.NumberRequest request) {
      return blockingUnaryCall(
          getChannel(), getFlightNumberMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.alexeyre.grpc.flight.DisplayResponse> flightDisplay(
        com.alexeyre.grpc.flight.DisplayRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getFlightDisplayMethod(), getCallOptions(), request);
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

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.alexeyre.grpc.flight.NumberResponse> flightNumber(
        com.alexeyre.grpc.flight.NumberRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFlightNumberMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FLIGHT_LIST = 0;
  private static final int METHODID_FLIGHT_DETAILS = 1;
  private static final int METHODID_FLIGHT_NUMBER = 2;
  private static final int METHODID_FLIGHT_DISPLAY = 3;
  private static final int METHODID_FLIGHT_SEARCH = 4;
  private static final int METHODID_FLIGHT_BOOKING = 5;

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
          serviceImpl.flightList((com.alexeyre.grpc.flight.ListRequest) request,
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.ListResponse>) responseObserver);
          break;
        case METHODID_FLIGHT_DETAILS:
          serviceImpl.flightDetails((com.alexeyre.grpc.flight.DetailsRequest) request,
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.DetailsResponse>) responseObserver);
          break;
        case METHODID_FLIGHT_NUMBER:
          serviceImpl.flightNumber((com.alexeyre.grpc.flight.NumberRequest) request,
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.NumberResponse>) responseObserver);
          break;
        case METHODID_FLIGHT_DISPLAY:
          serviceImpl.flightDisplay((com.alexeyre.grpc.flight.DisplayRequest) request,
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.DisplayResponse>) responseObserver);
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
        case METHODID_FLIGHT_SEARCH:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.flightSearch(
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.SearchResponse>) responseObserver);
        case METHODID_FLIGHT_BOOKING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.flightBooking(
              (io.grpc.stub.StreamObserver<com.alexeyre.grpc.flight.BookingResponse>) responseObserver);
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
      return com.alexeyre.grpc.flight.FlightServiceImpl.getDescriptor();
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
              .addMethod(getFlightSearchMethod())
              .addMethod(getFlightDetailsMethod())
              .addMethod(getFlightNumberMethod())
              .addMethod(getFlightBookingMethod())
              .addMethod(getFlightDisplayMethod())
              .build();
        }
      }
    }
    return result;
  }
}
