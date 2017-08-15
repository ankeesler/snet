//
// snet_admin.hpp
//
// SNET Client Admin C++ header
//

#include "snet_client.hpp"

namespace snet {

class admin : public client {
public:
  status init(void);
};

}; // namespace snet