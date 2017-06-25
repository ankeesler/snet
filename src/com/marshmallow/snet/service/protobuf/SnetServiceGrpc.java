package com.marshmallow.snet.service.protobuf;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.5.0-SNAPSHOT)",
    comments = "Source: protobuf/snet.proto")
public final class SnetServiceGrpc {

  private SnetServiceGrpc() {}

  public static final String SERVICE_NAME = "SnetService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.marshmallow.snet.service.protobuf.EchoRequest,
      com.marshmallow.snet.service.protobuf.EchoResponse> METHOD_ECHO =
      io.grpc.MethodDescriptor.<com.marshmallow.snet.service.protobuf.EchoRequest, com.marshmallow.snet.service.protobuf.EchoResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SnetService", "Echo"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.EchoRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.EchoResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.marshmallow.snet.service.protobuf.ResetRequest,
      com.marshmallow.snet.service.protobuf.ResetResponse> METHOD_RESET =
      io.grpc.MethodDescriptor.<com.marshmallow.snet.service.protobuf.ResetRequest, com.marshmallow.snet.service.protobuf.ResetResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SnetService", "Reset"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.ResetRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.ResetResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.marshmallow.snet.service.protobuf.InfoRequest,
      com.marshmallow.snet.service.protobuf.InfoResponse> METHOD_INFO =
      io.grpc.MethodDescriptor.<com.marshmallow.snet.service.protobuf.InfoRequest, com.marshmallow.snet.service.protobuf.InfoResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SnetService", "Info"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.InfoRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.InfoResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.marshmallow.snet.service.protobuf.InitRequest,
      com.marshmallow.snet.service.protobuf.InitResponse> METHOD_INIT =
      io.grpc.MethodDescriptor.<com.marshmallow.snet.service.protobuf.InitRequest, com.marshmallow.snet.service.protobuf.InitResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SnetService", "Init"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.InitRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.InitResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.marshmallow.snet.service.protobuf.TxRequest,
      com.marshmallow.snet.service.protobuf.TxResponse> METHOD_TX =
      io.grpc.MethodDescriptor.<com.marshmallow.snet.service.protobuf.TxRequest, com.marshmallow.snet.service.protobuf.TxResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SnetService", "Tx"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.TxRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.TxResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.marshmallow.snet.service.protobuf.RxRequest,
      com.marshmallow.snet.service.protobuf.RxResponse> METHOD_RX =
      io.grpc.MethodDescriptor.<com.marshmallow.snet.service.protobuf.RxRequest, com.marshmallow.snet.service.protobuf.RxResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SnetService", "Rx"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.RxRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.RxResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SnetServiceStub newStub(io.grpc.Channel channel) {
    return new SnetServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SnetServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SnetServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SnetServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SnetServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SnetServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void echo(com.marshmallow.snet.service.protobuf.EchoRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.EchoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ECHO, responseObserver);
    }

    /**
     */
    public void reset(com.marshmallow.snet.service.protobuf.ResetRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.ResetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RESET, responseObserver);
    }

    /**
     */
    public void info(com.marshmallow.snet.service.protobuf.InfoRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.InfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_INFO, responseObserver);
    }

    /**
     */
    public void init(com.marshmallow.snet.service.protobuf.InitRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.InitResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_INIT, responseObserver);
    }

    /**
     */
    public void tx(com.marshmallow.snet.service.protobuf.TxRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.TxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_TX, responseObserver);
    }

    /**
     */
    public void rx(com.marshmallow.snet.service.protobuf.RxRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.RxResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RX, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ECHO,
            asyncUnaryCall(
              new MethodHandlers<
                com.marshmallow.snet.service.protobuf.EchoRequest,
                com.marshmallow.snet.service.protobuf.EchoResponse>(
                  this, METHODID_ECHO)))
          .addMethod(
            METHOD_RESET,
            asyncUnaryCall(
              new MethodHandlers<
                com.marshmallow.snet.service.protobuf.ResetRequest,
                com.marshmallow.snet.service.protobuf.ResetResponse>(
                  this, METHODID_RESET)))
          .addMethod(
            METHOD_INFO,
            asyncUnaryCall(
              new MethodHandlers<
                com.marshmallow.snet.service.protobuf.InfoRequest,
                com.marshmallow.snet.service.protobuf.InfoResponse>(
                  this, METHODID_INFO)))
          .addMethod(
            METHOD_INIT,
            asyncUnaryCall(
              new MethodHandlers<
                com.marshmallow.snet.service.protobuf.InitRequest,
                com.marshmallow.snet.service.protobuf.InitResponse>(
                  this, METHODID_INIT)))
          .addMethod(
            METHOD_TX,
            asyncUnaryCall(
              new MethodHandlers<
                com.marshmallow.snet.service.protobuf.TxRequest,
                com.marshmallow.snet.service.protobuf.TxResponse>(
                  this, METHODID_TX)))
          .addMethod(
            METHOD_RX,
            asyncUnaryCall(
              new MethodHandlers<
                com.marshmallow.snet.service.protobuf.RxRequest,
                com.marshmallow.snet.service.protobuf.RxResponse>(
                  this, METHODID_RX)))
          .build();
    }
  }

  /**
   */
  public static final class SnetServiceStub extends io.grpc.stub.AbstractStub<SnetServiceStub> {
    private SnetServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SnetServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SnetServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SnetServiceStub(channel, callOptions);
    }

    /**
     */
    public void echo(com.marshmallow.snet.service.protobuf.EchoRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.EchoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ECHO, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reset(com.marshmallow.snet.service.protobuf.ResetRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.ResetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RESET, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void info(com.marshmallow.snet.service.protobuf.InfoRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.InfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_INFO, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void init(com.marshmallow.snet.service.protobuf.InitRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.InitResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_INIT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void tx(com.marshmallow.snet.service.protobuf.TxRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.TxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_TX, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rx(com.marshmallow.snet.service.protobuf.RxRequest request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.RxResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RX, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SnetServiceBlockingStub extends io.grpc.stub.AbstractStub<SnetServiceBlockingStub> {
    private SnetServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SnetServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SnetServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SnetServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.marshmallow.snet.service.protobuf.EchoResponse echo(com.marshmallow.snet.service.protobuf.EchoRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ECHO, getCallOptions(), request);
    }

    /**
     */
    public com.marshmallow.snet.service.protobuf.ResetResponse reset(com.marshmallow.snet.service.protobuf.ResetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RESET, getCallOptions(), request);
    }

    /**
     */
    public com.marshmallow.snet.service.protobuf.InfoResponse info(com.marshmallow.snet.service.protobuf.InfoRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_INFO, getCallOptions(), request);
    }

    /**
     */
    public com.marshmallow.snet.service.protobuf.InitResponse init(com.marshmallow.snet.service.protobuf.InitRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_INIT, getCallOptions(), request);
    }

    /**
     */
    public com.marshmallow.snet.service.protobuf.TxResponse tx(com.marshmallow.snet.service.protobuf.TxRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_TX, getCallOptions(), request);
    }

    /**
     */
    public com.marshmallow.snet.service.protobuf.RxResponse rx(com.marshmallow.snet.service.protobuf.RxRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RX, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SnetServiceFutureStub extends io.grpc.stub.AbstractStub<SnetServiceFutureStub> {
    private SnetServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SnetServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SnetServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SnetServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.marshmallow.snet.service.protobuf.EchoResponse> echo(
        com.marshmallow.snet.service.protobuf.EchoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ECHO, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.marshmallow.snet.service.protobuf.ResetResponse> reset(
        com.marshmallow.snet.service.protobuf.ResetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RESET, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.marshmallow.snet.service.protobuf.InfoResponse> info(
        com.marshmallow.snet.service.protobuf.InfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_INFO, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.marshmallow.snet.service.protobuf.InitResponse> init(
        com.marshmallow.snet.service.protobuf.InitRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_INIT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.marshmallow.snet.service.protobuf.TxResponse> tx(
        com.marshmallow.snet.service.protobuf.TxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_TX, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.marshmallow.snet.service.protobuf.RxResponse> rx(
        com.marshmallow.snet.service.protobuf.RxRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RX, getCallOptions()), request);
    }
  }

  private static final int METHODID_ECHO = 0;
  private static final int METHODID_RESET = 1;
  private static final int METHODID_INFO = 2;
  private static final int METHODID_INIT = 3;
  private static final int METHODID_TX = 4;
  private static final int METHODID_RX = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SnetServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SnetServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ECHO:
          serviceImpl.echo((com.marshmallow.snet.service.protobuf.EchoRequest) request,
              (io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.EchoResponse>) responseObserver);
          break;
        case METHODID_RESET:
          serviceImpl.reset((com.marshmallow.snet.service.protobuf.ResetRequest) request,
              (io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.ResetResponse>) responseObserver);
          break;
        case METHODID_INFO:
          serviceImpl.info((com.marshmallow.snet.service.protobuf.InfoRequest) request,
              (io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.InfoResponse>) responseObserver);
          break;
        case METHODID_INIT:
          serviceImpl.init((com.marshmallow.snet.service.protobuf.InitRequest) request,
              (io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.InitResponse>) responseObserver);
          break;
        case METHODID_TX:
          serviceImpl.tx((com.marshmallow.snet.service.protobuf.TxRequest) request,
              (io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.TxResponse>) responseObserver);
          break;
        case METHODID_RX:
          serviceImpl.rx((com.marshmallow.snet.service.protobuf.RxRequest) request,
              (io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.RxResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class SnetServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.marshmallow.snet.service.protobuf.Snet.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SnetServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SnetServiceDescriptorSupplier())
              .addMethod(METHOD_ECHO)
              .addMethod(METHOD_RESET)
              .addMethod(METHOD_INFO)
              .addMethod(METHOD_INIT)
              .addMethod(METHOD_TX)
              .addMethod(METHOD_RX)
              .build();
        }
      }
    }
    return result;
  }
}
