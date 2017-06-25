//
// snet_client.hpp
//
// SNET Client C++ header
//

#include "protobuf/snet.grpc.pb.h"

class snet_client {
public:
  enum status {
    OK,
    BAD,
    NONE,
    RPCERR,
  };

  enum packet_type {
    DATA,
    CMD,
  };

  snet_client(void);
  int get_addr(void) const;
  status reset(void);
  status init(void);
  status tx(packet_type, int, const std::string &);
  status rx(int *src_addr, std::string *payload);

private:
  static const int SM_RPC_TIMEOUT_S = 1;
  static int sm_nxt_src_addr;

  static void make_ctx(grpc::ClientContext *ctx);

  int m_nxt_seq;
  int m_addr;
  std::unique_ptr<SnetService::Stub> m_stub;
};
