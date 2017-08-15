//
// snet_node.cpp
//
// SNET Client Node C++ implementation
//

#include "protobuf/snet.pb.h"

#include "snet_node.hpp"

namespace snet {

status node::init(void)
{
  return client::init(client::NODE);
}

};