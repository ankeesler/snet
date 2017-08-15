//
// snet_admin.cpp
//
// SNET Client Admin C++ implementation
//

#include "protobuf/snet.pb.h"

#include "snet_admin.hpp"

namespace snet {

status admin::init(void)
{
  return client::init(client::ADMIN);
}

}; // namespace snet