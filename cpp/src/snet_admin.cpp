//
// snet_admin.cpp
//
// SNET Client Admin C++ implementation
//

#include "protobuf/snet.pb.h"

#include "snet_admin.hpp"

snet_admin::status snet_admin::init(void)
{
  return snet_client::init(snet_client::ADMIN);
}