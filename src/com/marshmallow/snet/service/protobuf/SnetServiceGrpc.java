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
  public static final io.grpc.MethodDescriptor<com.marshmallow.snet.service.protobuf.TxConfig,
      com.marshmallow.snet.service.protobuf.Status> METHOD_TX =
      io.grpc.MethodDescriptor.<com.marshmallow.snet.service.protobuf.TxConfig, com.marshmallow.snet.service.protobuf.Status>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SnetService", "Tx"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.TxConfig.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.Status.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.marshmallow.snet.service.protobuf.RxConfig,
      com.marshmallow.snet.service.protobuf.Packet> METHOD_RX =
      io.grpc.MethodDescriptor.<com.marshmallow.snet.service.protobuf.RxConfig, com.marshmallow.snet.service.protobuf.Packet>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SnetService", "Rx"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.RxConfig.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.marshmallow.snet.service.protobuf.Packet.getDefaultInstance()))
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
    public void tx(com.marshmallow.snet.service.protobuf.TxConfig request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.Status> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_TX, responseObserver);
    }

    /**
     */
    public void rx(com.marshmallow.snet.service.protobuf.RxConfig request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.Packet> responseObserver) {
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
            METHOD_TX,
            asyncUnaryCall(
              new MethodHandlers<
                com.marshmallow.snet.service.protobuf.TxConfig,
                com.marshmallow.snet.service.protobuf.Status>(
                  this, METHODID_TX)))
          .addMethod(
            METHOD_RX,
            asyncUnaryCall(
              new MethodHandlers<
                com.marshmallow.snet.service.protobuf.RxConfig,
                com.marshmallow.snet.service.protobuf.Packet>(
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
    public void tx(com.marshmallow.snet.service.protobuf.TxConfig request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.Status> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_TX, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rx(com.marshmallow.snet.service.protobuf.RxConfig request,
        io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.Packet> responseObserver) {
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
    public com.marshmallow.snet.service.protobuf.Status tx(com.marshmallow.snet.service.protobuf.TxConfig request) {
      return blockingUnaryCall(
          getChannel(), METHOD_TX, getCallOptions(), request);
    }

    /**
     */
    public com.marshmallow.snet.service.protobuf.Packet rx(com.marshmallow.snet.service.protobuf.RxConfig request) {
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
    public com.google.common.util.concurrent.ListenableFuture<com.marshmallow.snet.service.protobuf.Status> tx(
        com.marshmallow.snet.service.protobuf.TxConfig request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_TX, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.marshmallow.snet.service.protobuf.Packet> rx(
        com.marshmallow.snet.service.protobuf.RxConfig request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RX, getCallOptions()), request);
    }
  }

  private static final int METHODID_ECHO = 0;
  private static final int METHODID_TX = 1;
  private static final int METHODID_RX = 2;

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
        case METHODID_TX:
          serviceImpl.tx((com.marshmallow.snet.service.protobuf.TxConfig) request,
              (io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.Status>) responseObserver);
          break;
        case METHODID_RX:
          serviceImpl.rx((com.marshmallow.snet.service.protobuf.RxConfig) request,
              (io.grpc.stub.StreamObserver<com.marshmallow.snet.service.protobuf.Packet>) responseObserver);
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
              .addMethod(METHOD_TX)
              .addMethod(METHOD_RX)
              .build();
        }
      }
    }
    return result;
  }
}
