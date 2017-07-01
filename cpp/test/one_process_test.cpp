//
// one_process_test.cpp
//
// SNET Client C++ test
//

#include <iostream>

#include "src/snet_client.hpp"

#include "../../../Unity/src/unity.h"

static void test(void)
{
  snet_client client0;
  TEST_ASSERT_EQUAL_INT(client0.get_addr(), 0);

  snet_client client1;
  TEST_ASSERT_EQUAL_INT(client1.get_addr(), 1);

  // Reset the network to start from a known state.
  TEST_ASSERT_EQUAL_INT(client0.reset(), snet_client::OK);

  // If we haven't init'd yet, we can't tx...
  std::string payload = "{\\\"foo\\\": \\\"bar\\\"}";
  TEST_ASSERT_EQUAL_INT(client0.tx(snet_client::DATA, 1, payload),
                        snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(client1.tx(snet_client::DATA, 0, payload),
                        snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(client0.tx(snet_client::DATA, 2, payload),
                        snet_client::BAD);

  // ...or rx.
  int src_addr;
  std::string rx_payload;
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(client1.rx(&src_addr, &rx_payload), snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet_client::BAD);

  // Now we should initialize successfully...
  TEST_ASSERT_EQUAL_INT(client0.init(), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(client1.init(), snet_client::OK);

  // ...and rx successfully (with no data)...
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet_client::NONE);
  TEST_ASSERT_EQUAL_INT(client1.rx(&src_addr, &rx_payload), snet_client::NONE);
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet_client::NONE);

  // ...and tx successfully...
  TEST_ASSERT_EQUAL_INT(client0.tx(snet_client::DATA, 1, payload),
         snet_client::OK);
  TEST_ASSERT_EQUAL_INT(client1.tx(snet_client::DATA, 0, payload),
         snet_client::OK);
  TEST_ASSERT_EQUAL_INT(client0.tx(snet_client::DATA, 2, payload),
         snet_client::OK);

  // ...rx successfully (with data).
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(src_addr, 1);
  TEST_ASSERT_EQUAL_STRING(payload.c_str(), rx_payload.c_str());
  TEST_ASSERT_EQUAL_INT(client1.rx(&src_addr, &rx_payload), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(src_addr, 0);
  TEST_ASSERT_EQUAL_STRING(payload.c_str(), rx_payload.c_str());
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet_client::NONE);
  TEST_ASSERT_EQUAL_INT(client1.rx(&src_addr, &rx_payload), snet_client::NONE);
}

int main(int argc, char *argv[])
{
  UNITY_BEGIN();
  RUN_TEST(test);
  return UNITY_END();
}
