package com.marshmallow.snet.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

import com.marshmallow.snet.service.protobuf.EchoRequest;
import com.marshmallow.snet.service.protobuf.EchoResponse;
import com.marshmallow.snet.service.protobuf.InfoRequest;
import com.marshmallow.snet.service.protobuf.InfoResponse;
import com.marshmallow.snet.service.protobuf.InitRequest;
import com.marshmallow.snet.service.protobuf.InitResponse;
import com.marshmallow.snet.service.protobuf.Packet;
import com.marshmallow.snet.service.protobuf.ResetRequest;
import com.marshmallow.snet.service.protobuf.ResetResponse;
import com.marshmallow.snet.service.protobuf.RxRequest;
import com.marshmallow.snet.service.protobuf.RxResponse;
import com.marshmallow.snet.service.protobuf.SnetServiceGrpc;
import com.marshmallow.snet.service.protobuf.Status;
import com.marshmallow.snet.service.protobuf.TxRequest;
import com.marshmallow.snet.service.protobuf.TxResponse;

import io.grpc.stub.StreamObserver;

public class GrpcSnetServiceImpl extends SnetServiceGrpc.SnetServiceImplBase {

  private static final Logger log = Logger.getGlobal();

  private Map<Integer, Queue<Packet>> packetQueues
    = new HashMap<Integer, Queue<Packet>>();

  @Override
  public void echo(EchoRequest request, StreamObserver<EchoResponse> response) {
    response.onNext(EchoResponse.newBuilder().setMessage(request.getMessage()).build());
    response.onCompleted();
  }

  @Override
  public void reset(ResetRequest request, StreamObserver<ResetResponse> response) {
    log.info("reset(" + request.getAddress() + ")");
    packetQueues.clear();
    response.onNext(ResetResponse.newBuilder().setStatus(Status.SUCCESS).build());
    response.onCompleted();
  }

  @Override
  public void init(InitRequest request, StreamObserver<InitResponse> response) {
    log.info("init(" + request.getAddress() + ")");
    Status status;
    Integer source = request.getAddress();
    if (packetQueues.containsKey(source)) {
      status = Status.DUPLICATE;
    } else {
      packetQueues.put(source, new LinkedList<Packet>());
      status = Status.SUCCESS;
    }

    InitResponse initResponse = InitResponse.newBuilder().setStatus(status).build();
    response.onNext(initResponse);
    response.onCompleted();
  }

  @Override
  public void info(InfoRequest request, StreamObserver<InfoResponse> responseObserver) {
    InfoResponse response = InfoResponse.newBuilder().build();
    log.info("info(...) -> " + response);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void tx(TxRequest request, StreamObserver<TxResponse> response) {
    log.info("tx(" + request.toString().trim() + ")");

    // TODO: propagation stuff here.

    Status status;
    Integer source = request.getPacket().getSource();
    if (!packetQueues.containsKey(source)) {
      status = Status.UNKNOWN;
    } else {
      Integer destination = request.getPacket().getDestination();
      if (packetQueues.containsKey(destination)) {
        packetQueues.get(destination).add(request.getPacket());
      }
      status = Status.SUCCESS;
    }

    TxResponse txResponse = TxResponse.newBuilder().setStatus(status).build();
    response.onNext(txResponse);
    response.onCompleted();
  }

  @Override
  public void rx(RxRequest request, StreamObserver<RxResponse> response) {
    log.info("rx(" + request.toString().trim() + ")");

    Status status;
    Packet packet = null;
    Integer source = request.getAddress();
    if (!packetQueues.containsKey(source)) {
      status = Status.UNKNOWN;
    } else {
      Queue<Packet> queue = packetQueues.get(source);
      if (queue.isEmpty()) {
        status = Status.EMPTY;
      } else {
        packet = queue.remove();
        status = Status.SUCCESS;
      }
    }

    RxResponse.Builder rxResponseBuilder = RxResponse.newBuilder().setStatus(status);
    if (packet != null) {
      rxResponseBuilder.setPacket(packet);
    }
    RxResponse rxResponse = rxResponseBuilder.build();
    response.onNext(rxResponse);
    response.onCompleted();
  }
}
