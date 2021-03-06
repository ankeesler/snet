ARCH.txt
SNET Architecture
==================

SNET contains client- and service-side functionality to run a communication
protocol stack over a simulated medium. Here is an overview of the file
structure of this repo.

| Directory | Description |
| --------- | ----------- |
| src/      | SNET gRPC-based service source code |
| cfg/      | Configuration files for running SNET service |
| protobuf/ | Google Protocol Buffer specification files; these files define the RPC API in SNET |
| cpp/      | C++ SNET Client utilities |


Service
-------
The SNET service is written in Java. The code can be found in the src/
directory. Here is a description of each of the Java packages used in the SNET
service code.
 
| Package | Description |
| ------- | ----------- |
| com.marshmallow.snet.app              | SNET applications built on top of the rest of the SNET code |
| com.marshmallow.snet.client           | SNET client code |
| com.marshmallow.snet.service          | SNET service code |
| com.marshmallow.snet.service.protobuf | Generated Google Protocol Buffers/gRPC code | 
| com.marshmallow.snet.test             | SNET unit tests |

Client
------
The only currently supported client-side SNET language is C++ (other than Java).
There is future support planned for the following languages.
- Python

The C++ source files can be found in the cpp directory. See
[cpp/README](cpp/README) for more information.

There are 2 types of clients: an admin and a node. An admin can obtain
information from the service with the Info() RPC API. A node communicates
with the service like a communication interface would interact with a physical
medium. See below for the node API.

Node API
---
The SNET RPC API can be found in protobuf/snet.proto. Essentially, a node on the
network communicates in 4 ways with the service.
1. Init  - the node initializes itself on the simulated medium.
2. Tx    - the node transmits some data to the simulated medium.
3. Rx    - the node receives for data from the simulated medium.
4. Reset - completely reset the service so that it forgets all current nodes.

Here is a communication diagram for how clients communicate with the SNET
service.
```
         client0       service          client1
                          |
    (boot) |              |
           |              |
[A] Init() |    ->        |
           |    <- Status |
           |              |
[B] Tx()   |    ->        |
           |              | (nothing)
           |    <- Status |
           |              |
           |              |               | (boot)
           |              |               |
[C] Tx()   |    ->        |               |
           |              | (nothing)     |
           |    <- Status |               |
           |              |               |
           |              |         <-    | Init()
           |              | Status  ->    |
           |              |               |
[D] Tx()   |    ->        |               |
           |              | (packet)      |
           |    <- Status |               |
           |              |               |
[E]        |              |         <-    | Rx()
           |              | Packet  ->    |
           |              |               |
[F]        |              |         <-    | Rx()
           |              | Nothing ->    |
           |              |               |
```
There are a couple of specific notes that need to be made here. Note that all of
the SNET RPC calls are meant to be made SYNCHRONOUSLY.
- [A] A client needs to call the Init() RPC API in order to establish itself on
      the simulated medium. If it does not do this, then it will not receive any
      information over the simulated medium, and the SNET service will not report
      any information about the device. The client must pass the NODE ClientType
      to this API in order to establish itself as a node (not an admin).
- [B] Once a client initializes itself (with the Init() RPC API), it can start
      sending packets (with the Tx() RPC API). Packets sent over the simulated
      medium are sent to all nodes, or only the nodes with a matching address
      (see Propagation section below). Even if there are no other clients
      listening on the simulated medium, the SNET service will report that the
      packet has been sent successfully.
- [C] If no other clients have initialized themselves on the network, then a
      packet sent from the lone initialized node will go unnoticed.
- [D] Once another node joins the network (see [A]), packets will be queued by
      the SNET service upon transmission from other nodes (see [B]).
- [E] Nodes may synchronously call the RPC API Rx() in order to receive any
      messages queued in the the network. See propagation section for more
      configurable propagation options.
- [F] If there is nothing in the network to be received, an RPC API Rx() call
      will result in the SNET service reporting that there are no packets to be
      received.

Propagation
-----------
There is a propagation feature planned for SNET, which will offer users the
ability to define their own propagation mechanism for packets traveling through
the simulated medium. This feature is not currently ready. Here are some
potential uses for this feature.
- Location-based propagation
- Propagation delay
- Packet error simulation
- Simulated hardware packet filtering
- Packet queueing (maximum number, timeout)