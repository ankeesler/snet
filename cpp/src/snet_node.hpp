//
// snet_node.hpp
//
// SNET Client Node C++ header
//

#include "snet_client.hpp"

namespace snet {

class node : public client {
public:
  status init(void);
};

}; // namespace snet