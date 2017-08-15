//
// snet_client.hpp
//
// SNET Client C++ header
//

#ifndef __SNET_CLIENT_HPP__
#define __SNET_CLIENT_HPP__

#include <vector>

#include "protobuf/snet.grpc.pb.h"

namespace snet {

enum status {
  OK,
  BAD,
  NONE,
  RPCERR,
};
std::string status_to_str(status s);

enum packet_type {
  DATA,
  CMD,
};

class client {
public:
  enum type {
    ADMIN,
    NODE,
  };

  client(void);
  client(int);
  int get_addr(void) const;
  status reset(void);
  status init(type);
  status tx(packet_type, int, const std::string &);
  status rx(int *src_addr, std::string *payload);
  status info(std::vector<int> *addresses);

private:
  static const int SM_RPC_TIMEOUT_S = 1;
  static int sm_nxt_src_addr;

  static void make_ctx(grpc::ClientContext *ctx);

  int m_nxt_seq;
  int m_addr;
  std::unique_ptr<SnetService::Stub> m_stub;

  void init_stub(void);
};

class node : public client {
public:
  status init(void) { return client::init(NODE); }
};

class admin : public client {
public:
  status init(void) { return client::init(ADMIN); }
};

}; // namespace snet

#endif // __SNET_CLIENT_HPP__