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

namespace snet {

std::string status_to_str(status s)
{
  switch (s) {
  case OK: return "OK";
  case BAD: return "BAD";
  case NONE: return "NONE";
  case RPCERR: return "RPCERR";
  default: return "???";
  }
}

const int client::SM_RPC_TIMEOUT_S;
int client::sm_nxt_src_addr = 0;

client::client(void)
  : m_nxt_seq(0), m_addr(sm_nxt_src_addr++)
{
  init_stub();
}

client::client(int addr)
  : m_nxt_seq(0), m_addr(addr)
{
  init_stub();
}

int client::get_addr(void) const
{
  return m_addr;
}

status client::reset(void)
{
  grpc::ClientContext ctx;
  make_ctx(&ctx);
  ResetRequest req;
  ResetResponse rsp;
  req.set_address(m_addr);
  grpc::Status status = m_stub->Reset(&ctx, req, &rsp);
  return (!status.ok()
          ? status::RPCERR
          : (rsp.status() != Status::SUCCESS
             ? status::BAD
             : status::OK));
}

status client::init(type type)
{
  grpc::ClientContext ctx;
  make_ctx(&ctx);
  InitRequest req;
  InitResponse rsp;
  req.set_type(type == client::ADMIN ? ClientType::ADMIN : ClientType::NODE);
  req.set_address(m_addr);
  grpc::Status status = m_stub->Init(&ctx, req, &rsp);
  return (!status.ok()
          ? status::RPCERR
          : (rsp.status() != Status::SUCCESS
             ? status::BAD
             : status::OK));
}

status client::tx(packet_type type,
                  int dst_addr,
                  const std::string &payload)
{
  Packet *packet = new Packet();
  packet->set_length(0);
  packet->set_sequence(m_nxt_seq++);
  packet->set_type(type == packet_type::DATA ? Packet::DATA : Packet::COMMAND);
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
          ? status::RPCERR
          : (rsp.status() != Status::SUCCESS
             ? status::BAD
             : status::OK));
}

status client::rx(int *src_addr, std::string *payload)
{
  grpc::ClientContext ctx;
  make_ctx(&ctx);
  RxRequest req;
  req.set_address(m_addr);
  RxResponse rsp;
  grpc::Status status = m_stub->Rx(&ctx, req, &rsp);
  if (!status.ok()) {
    return status::RPCERR;
  }
  switch (rsp.status()) {
    case Status::SUCCESS:
      *src_addr = rsp.packet().source();
      *payload = rsp.packet().payload();
      return status::OK;
    case Status::EMPTY:
      return status::NONE;
    default:
      return status::BAD;
  }
}

status client::info(int *node_cnt)
{
  grpc::ClientContext ctx;
  make_ctx(&ctx);
  InfoRequest req;
  req.set_source(m_addr);
  InfoResponse rsp;
  grpc::Status status = m_stub->Info(&ctx, req, &rsp);
  if (!status.ok()) {
    return status::RPCERR;
  }
  switch (rsp.status()) {
    case Status::SUCCESS:
      *node_cnt = rsp.nodecount();
      return status::OK;
    default:
      return status::BAD;
  }
}

void client::make_ctx(grpc::ClientContext *ctx)
{
  std::chrono::system_clock::time_point deadline =
    std::chrono::system_clock::now() + std::chrono::seconds(SM_RPC_TIMEOUT_S);
  ctx->set_deadline(deadline);
}

void client::init_stub(void)
{
  std::shared_ptr<grpc::ChannelInterface> channel
    = grpc::CreateChannel("localhost:12345",
                          grpc::InsecureChannelCredentials());

  m_stub = SnetService::NewStub(channel);
}

}; // namespace snet