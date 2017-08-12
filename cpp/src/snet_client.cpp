//
// snet_client.cpp
//
// SNET Client C++ implementation
//

#include <string>
#include <grpc++/create_channel.h>

#include "protobuf/snet.grpc.pb.h"
#include "protobuf/snet.pb.h"

#include "snet_client.hpp"

const int snet_client::SM_RPC_TIMEOUT_S;
int snet_client::sm_nxt_src_addr = 0;

snet_client::snet_client(void)
  : m_nxt_seq(0), m_addr(sm_nxt_src_addr++)
{
  init_stub();
}

snet_client::snet_client(int addr)
  : m_nxt_seq(0), m_addr(addr)
{
  init_stub();
}

int snet_client::get_addr(void) const
{
  return m_addr;
}

snet_client::status snet_client::reset(void)
{
  grpc::ClientContext ctx;
  make_ctx(&ctx);
  ResetRequest req;
  ResetResponse rsp;
  req.set_address(m_addr);
  grpc::Status status = m_stub->Reset(&ctx, req, &rsp);
  return (!status.ok()
          ? snet_client::RPCERR
          : (rsp.status() != Status::SUCCESS
             ? snet_client::BAD
             : snet_client::OK));
}

snet_client::status snet_client::init(void)
{
  grpc::ClientContext ctx;
  make_ctx(&ctx);
  InitRequest req;
  InitResponse rsp;
  req.set_address(m_addr);
  grpc::Status status = m_stub->Init(&ctx, req, &rsp);
  return (!status.ok()
          ? snet_client::RPCERR
          : (rsp.status() != Status::SUCCESS
             ? snet_client::BAD
             : snet_client::OK));
}

snet_client::status snet_client::tx(snet_client::packet_type type,
                                    int dst_addr,
                                    const std::string &payload)
{
  Packet *packet = new Packet();
  packet->set_length(0);
  packet->set_sequence(m_nxt_seq++);
  packet->set_type(type == snet_client::DATA ? Packet::DATA : Packet::COMMAND);
  packet->set_source(m_addr);
  packet->set_destination(dst_addr);
  packet->set_payload(payload);

  grpc::ClientContext ctx;
  make_ctx(&ctx);
  TxRequest req;
  req.set_allocated_packet(packet);
  TxResponse rsp;
  grpc::Status status = m_stub->Tx(&ctx, req, &rsp);
  return (!status.ok()
          ? snet_client::RPCERR
          : (rsp.status() != Status::SUCCESS
             ? snet_client::BAD
             : snet_client::OK));
}

snet_client::status snet_client::rx(int *src_addr, std::string *payload)
{
  grpc::ClientContext ctx;
  make_ctx(&ctx);
  RxRequest req;
  req.set_address(m_addr);
  RxResponse rsp;
  grpc::Status status = m_stub->Rx(&ctx, req, &rsp);
  if (!status.ok()) {
    return snet_client::RPCERR;
  }
  switch (rsp.status()) {
    case Status::SUCCESS:
      *src_addr = rsp.packet().source();
      *payload = rsp.packet().payload();
      return snet_client::OK;
    case Status::EMPTY:
      return snet_client::NONE;
    default:
      return snet_client::BAD;
  }
}

void snet_client::make_ctx(grpc::ClientContext *ctx)
{
  std::chrono::system_clock::time_point deadline =
    std::chrono::system_clock::now() + std::chrono::seconds(SM_RPC_TIMEOUT_S);
  ctx->set_deadline(deadline);
}

void snet_client::init_stub(void)
{
  std::shared_ptr<grpc::ChannelInterface> channel
    = grpc::CreateChannel("localhost:12345",
                          grpc::InsecureChannelCredentials());

  m_stub = SnetService::NewStub(channel);
}