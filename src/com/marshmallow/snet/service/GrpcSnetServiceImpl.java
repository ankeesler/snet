package com.marshmallow.snet.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

import com.marshmallow.snet.service.protobuf.ClientType;
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

  private Map<Integer, ClientType> clientTypes
    = new HashMap<Integer, ClientType>();

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
    clientTypes.clear();
    packetQueues.clear();
    response.onNext(ResetResponse.newBuilder().setStatus(Status.SUCCESS).build());
    response.onCompleted();
  }

  @Override
  public void init(InitRequest request, StreamObserver<InitResponse> response) {
    log.info("init(" + request.getAddress() + ")");
    Status status;
    ClientType type = request.getType();
    Integer source = request.getAddress();
    if (clientTypes.containsKey(source)) {
      status = Status.DUPLICATE;
    } else {
      if (type == ClientType.NODE) {
        packetQueues.put(source, new LinkedList<Packet>());
      }
      clientTypes.put(source, type);
      status = Status.SUCCESS;
    }

    InitResponse initResponse = InitResponse.newBuilder().setStatus(status).build();
    response.onNext(initResponse);
    response.onCompleted();
  }

  @Override
  public void info(InfoRequest request, StreamObserver<InfoResponse> responseObserver) {
    Integer source = request.getSource();
    Status status = validateClient(source, false); // node?
    int nodeCount = 0;
    if (status == Status.SUCCESS) {
      nodeCount = packetQueues.size();
      status = Status.SUCCESS;
    }
    InfoResponse response = InfoResponse.newBuilder().setStatus(status).setNodeCount(nodeCount).build();
    log.info("info(" + source +") -> " + response);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void tx(TxRequest request, StreamObserver<TxResponse> response) {
    log.info("tx(" + request.toString().trim() + ")");

    // TODO: propagation stuff here.

    Integer source = request.getPacket().getSource();
    Status status = validateClient(source, true); // node?
    if (status == Status.SUCCESS) {
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

    Integer source = request.getAddress();
    Status status = validateClient(source, true); // node?
    Packet packet = null;
    if (status == Status.SUCCESS) {
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

  private Status validateClient(int source, boolean node) {
    return (!clientTypes.containsKey(source)
            ? Status.UNKNOWN
            : (((clientTypes.get(source) == ClientType.NODE) == node))
               ? Status.SUCCESS
               : Status.BADTYPE);
  }
}
