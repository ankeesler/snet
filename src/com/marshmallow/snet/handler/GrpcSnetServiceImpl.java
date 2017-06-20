package com.marshmallow.snet.handler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.marshmallow.snet.core.Log;
import com.marshmallow.snet.service.protobuf.EchoRequest;
import com.marshmallow.snet.service.protobuf.EchoResponse;
import com.marshmallow.snet.service.protobuf.InfoRequest;
import com.marshmallow.snet.service.protobuf.InfoResponse;
import com.marshmallow.snet.service.protobuf.InitRequest;
import com.marshmallow.snet.service.protobuf.InitResponse;
import com.marshmallow.snet.service.protobuf.Packet;
import com.marshmallow.snet.service.protobuf.RxRequest;
import com.marshmallow.snet.service.protobuf.RxResponse;
import com.marshmallow.snet.service.protobuf.SnetServiceGrpc;
import com.marshmallow.snet.service.protobuf.Status;
import com.marshmallow.snet.service.protobuf.TxRequest;
import com.marshmallow.snet.service.protobuf.TxResponse;

import io.grpc.stub.StreamObserver;

public class GrpcSnetServiceImpl extends SnetServiceGrpc.SnetServiceImplBase {

  private Map<Integer, Queue<Packet>> packetQueues
    = new HashMap<Integer, Queue<Packet>>();

  @Override
  public void echo(EchoRequest request, StreamObserver<EchoResponse> response) {
    response.onNext(EchoResponse.newBuilder().setMessage(request.getMessage()).build());
    response.onCompleted();
  }

  @Override
  public void init(InitRequest request, StreamObserver<InitResponse> response) {
    Log.instance().note(this.getClass(), "init(" + request.getAddress() + ")");
    Status.Id statusId;
    Integer source = request.getAddress();
    if (packetQueues.containsKey(source)) {
      statusId = Status.Id.DUPLICATE;
    } else {
      packetQueues.put(source, new LinkedList<Packet>());
      statusId = Status.Id.SUCCESS;
    }

    Status status = Status.newBuilder().setId(statusId).build();
    InitResponse initResponse = InitResponse.newBuilder().setStatus(status).build();
    response.onNext(initResponse);
    response.onCompleted();
  }

  @Override
  public void info(InfoRequest request, StreamObserver<InfoResponse> responseObserver) {
    InfoResponse response = InfoResponse.newBuilder().build();
    Log.instance().note(this.getClass(), "info(...) -> " + response);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void tx(TxRequest request, StreamObserver<TxResponse> response) {
    Log.instance().note(this.getClass(), "tx(" + request.toString().trim() + ")");

    // TODO: propagation stuff here.

    Status.Id statusId;
    Integer source = request.getPacket().getSource();
    if (!packetQueues.containsKey(source)) {
      statusId = Status.Id.UNKNOWN;
    } else {
      Integer destination = request.getPacket().getDestination();
      if (packetQueues.containsKey(destination)) {
        packetQueues.get(destination).add(request.getPacket());
      }
      statusId = Status.Id.SUCCESS;
    }

    Status status = Status.newBuilder().setId(statusId).build();
    TxResponse txResponse = TxResponse.newBuilder().setStatus(status).build();
    response.onNext(txResponse);
    response.onCompleted();
  }

  @Override
  public void rx(RxRequest request, StreamObserver<RxResponse> response) {
    Log.instance().note(this.getClass(), "rx(" + request.toString().trim() + ")");

    Status.Id statusId;
    Packet packet = null;
    Integer source = request.getAddress();
    if (!packetQueues.containsKey(source)) {
      statusId = Status.Id.UNKNOWN;
    } else {
      Queue<Packet> queue = packetQueues.get(source);
      if (queue.isEmpty()) {
        statusId = Status.Id.EMPTY;
      } else {
        packet = queue.remove();
        statusId = Status.Id.SUCCESS;
      }
    }

    Status status = Status.newBuilder().setId(statusId).build();
    RxResponse.Builder rxResponseBuilder = RxResponse.newBuilder().setStatus(status);
    if (packet != null) {
      rxResponseBuilder.setPacket(packet);
    }
    RxResponse rxResponse = rxResponseBuilder.build();
    response.onNext(rxResponse);
    response.onCompleted();
  }
}
