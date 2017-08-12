//
// snet_node.cpp
//
// SNET Client Node C++ implementation
//

#include "protobuf/snet.pb.h"

#include "snet_node.hpp"

snet_node::status snet_node::init(void)
{
  return snet_client::init(snet_client::NODE);
}