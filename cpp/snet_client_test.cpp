//
// snet_client_test.cpp
//
// SNET Client C++ test
//

#include <iostream>
#include <cassert>

#include "snet_client.hpp"

#define expect(a, b)                              \
 do {                                             \
   if ((a) != (b)) {                              \
     std::cout << a << " != " << b << std::endl;  \
     assert(0);                                   \
   }                                              \
   std::cout << "PASS:" << __LINE__ << std::endl; \
  } while (0);

int main(int argc, char *argv[])
{
  snet_client client0;
  expect(client0.get_addr(), 0)

  snet_client client1;
  expect(client1.get_addr(), 1);

  // Reset the network to start from a known state.
  expect(client0.reset(), snet_client::OK);

  // If we haven't init'd yet, we can't tx...
  std::string payload = "{\\\"foo\\\": \\\"bar\\\"}";
  expect(client0.tx(snet_client::DATA, 1, payload),
         snet_client::BAD);
  expect(client1.tx(snet_client::DATA, 0, payload),
         snet_client::BAD);
  expect(client0.tx(snet_client::DATA, 2, payload),
         snet_client::BAD);

  // ...or rx.
  int src_addr;
  std::string rx_payload;
  expect(client0.rx(&src_addr, &rx_payload), snet_client::BAD);
  expect(client1.rx(&src_addr, &rx_payload), snet_client::BAD);
  expect(client0.rx(&src_addr, &rx_payload), snet_client::BAD);

  // Now we should initialize successfully...
  expect(client0.init(), snet_client::OK);
  expect(client1.init(), snet_client::OK);

  // ...and rx successfully (with no data)...
  expect(client0.rx(&src_addr, &rx_payload), snet_client::NONE);
  expect(client1.rx(&src_addr, &rx_payload), snet_client::NONE);
  expect(client0.rx(&src_addr, &rx_payload), snet_client::NONE);

  // ...and tx successfully...
  expect(client0.tx(snet_client::DATA, 1, payload),
         snet_client::OK);
  expect(client1.tx(snet_client::DATA, 0, payload),
         snet_client::OK);
  expect(client0.tx(snet_client::DATA, 2, payload),
         snet_client::OK);

  // ...rx successfully (with data).
  expect(client0.rx(&src_addr, &rx_payload), snet_client::OK);
  expect(src_addr, 1);
  expect(payload, rx_payload);
  expect(client1.rx(&src_addr, &rx_payload), snet_client::OK);
  expect(src_addr, 0);
  expect(payload, rx_payload);
  expect(client0.rx(&src_addr, &rx_payload), snet_client::NONE);
  expect(client1.rx(&src_addr, &rx_payload), snet_client::NONE);

  return 0;
}
