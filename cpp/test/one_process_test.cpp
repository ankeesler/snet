//
// one_process_test.cpp
//
// SNET Client C++ test
//

#include <iostream>
#include <vector>

#include "src/snet_client.hpp"

#include "../../../Unity/src/unity.h"

static void test_client(void)
{
  snet::client client0;
  TEST_ASSERT_EQUAL_INT(client0.get_addr(), 0);

  snet::client client1;
  TEST_ASSERT_EQUAL_INT(client1.get_addr(), 1);

  // Reset the network to start from a known state.
  TEST_ASSERT_EQUAL_INT(client0.reset(), snet::OK);

  // If we haven't init'd yet, we can't tx...
  std::string payload = "{\\\"foo\\\": \\\"bar\\\"}";
  TEST_ASSERT_EQUAL_INT(client0.tx(snet::DATA, 1, payload),
                        snet::BAD);
  TEST_ASSERT_EQUAL_INT(client1.tx(snet::DATA, 0, payload),
                        snet::BAD);
  TEST_ASSERT_EQUAL_INT(client0.tx(snet::DATA, 2, payload),
                        snet::BAD);

  // ...or rx.
  int src_addr;
  std::string rx_payload;
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet::BAD);
  TEST_ASSERT_EQUAL_INT(client1.rx(&src_addr, &rx_payload), snet::BAD);
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet::BAD);

  // Now we should initialize successfully...
  TEST_ASSERT_EQUAL_INT(client0.init(snet::client::NODE), snet::OK);
  TEST_ASSERT_EQUAL_INT(client1.init(snet::client::NODE), snet::OK);

  // ...and rx successfully (with no data)...
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet::NONE);
  TEST_ASSERT_EQUAL_INT(client1.rx(&src_addr, &rx_payload), snet::NONE);
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet::NONE);

  // ...and tx successfully...
  TEST_ASSERT_EQUAL_INT(client0.tx(snet::DATA, 1, payload),
         snet::OK);
  TEST_ASSERT_EQUAL_INT(client1.tx(snet::DATA, 0, payload),
         snet::OK);
  TEST_ASSERT_EQUAL_INT(client0.tx(snet::DATA, 2, payload),
         snet::OK);

  // ...rx successfully (with data).
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet::OK);
  TEST_ASSERT_EQUAL_INT(src_addr, 1);
  TEST_ASSERT_EQUAL_STRING(payload.c_str(), rx_payload.c_str());
  TEST_ASSERT_EQUAL_INT(client1.rx(&src_addr, &rx_payload), snet::OK);
  TEST_ASSERT_EQUAL_INT(src_addr, 0);
  TEST_ASSERT_EQUAL_STRING(payload.c_str(), rx_payload.c_str());
  TEST_ASSERT_EQUAL_INT(client0.rx(&src_addr, &rx_payload), snet::NONE);
  TEST_ASSERT_EQUAL_INT(client1.rx(&src_addr, &rx_payload), snet::NONE);

  // Nodes cannot call the info RPC API.
  std::vector<int> addresses;
  TEST_ASSERT_EQUAL_INT(client0.info(&addresses), snet::BAD);
  TEST_ASSERT_EQUAL_INT(client1.info(&addresses), snet::BAD);
}

static void test_node(void)
{
  // The node should function very much the same way as the client, but the node
  // interface is much simpler.

  snet::node node2;
  TEST_ASSERT_EQUAL_INT(node2.get_addr(), 2);

  snet::node node3;
  TEST_ASSERT_EQUAL_INT(node3.get_addr(), 3);

  // Reset the network to start from a known state.
  TEST_ASSERT_EQUAL_INT(node2.reset(), snet::OK);

  // If we haven't init'd yet, we can't tx...
  std::string payload = "{\\\"foo\\\": \\\"bar\\\"}";
  TEST_ASSERT_EQUAL_INT(node2.tx(snet::DATA, 3, payload),
                        snet::BAD);
  TEST_ASSERT_EQUAL_INT(node3.tx(snet::DATA, 2, payload),
                        snet::BAD);
  TEST_ASSERT_EQUAL_INT(node2.tx(snet::DATA, 4, payload),
                        snet::BAD);

  // ...or rx.
  int src_addr;
  std::string rx_payload;
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet::BAD);
  TEST_ASSERT_EQUAL_INT(node3.rx(&src_addr, &rx_payload), snet::BAD);
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet::BAD);

  // Now we should initialize successfully...
  TEST_ASSERT_EQUAL_INT(node2.init(), snet::OK);
  TEST_ASSERT_EQUAL_INT(node3.init(), snet::OK);

  // ...and rx successfully (with no data)...
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet::NONE);
  TEST_ASSERT_EQUAL_INT(node3.rx(&src_addr, &rx_payload), snet::NONE);
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet::NONE);

  // ...and tx successfully...
  TEST_ASSERT_EQUAL_INT(node2.tx(snet::DATA, 3, payload),
         snet::OK);
  TEST_ASSERT_EQUAL_INT(node3.tx(snet::DATA, 2, payload),
         snet::OK);
  TEST_ASSERT_EQUAL_INT(node2.tx(snet::DATA, 4, payload),
         snet::OK);

  // ...rx successfully (with data).
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet::OK);
  TEST_ASSERT_EQUAL_INT(src_addr, 3);
  TEST_ASSERT_EQUAL_STRING(payload.c_str(), rx_payload.c_str());
  TEST_ASSERT_EQUAL_INT(node3.rx(&src_addr, &rx_payload), snet::OK);
  TEST_ASSERT_EQUAL_INT(src_addr, 2);
  TEST_ASSERT_EQUAL_STRING(payload.c_str(), rx_payload.c_str());
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet::NONE);
  TEST_ASSERT_EQUAL_INT(node3.rx(&src_addr, &rx_payload), snet::NONE);

  // Nodes cannot call the info RPC API.
  std::vector<int> addresses;
  TEST_ASSERT_EQUAL_INT(node2.info(&addresses), snet::BAD);
  TEST_ASSERT_EQUAL_INT(node3.info(&addresses), snet::BAD);
}

static void test_admin(void)
{
  snet::node node4;
  TEST_ASSERT_EQUAL_INT(node4.get_addr(), 4);

  snet::node node5;
  TEST_ASSERT_EQUAL_INT(node5.get_addr(), 5);

  snet::admin admin6;
  TEST_ASSERT_EQUAL_INT(admin6.get_addr(), 6);

  // Reset the network to start from a known state.
  TEST_ASSERT_EQUAL_INT(node4.reset(), snet::OK);

  // The admin should not be able to tx, rx, or info since it has not init'd.
  std::string payload = "{\\\"foo\\\": \\\"bar\\\"}";
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet::DATA, 4, payload),
                        snet::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet::DATA, 5, payload),
                        snet::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet::DATA, 7, payload),
                        snet::BAD);
  int src_addr;
  std::string rx_payload;
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet::BAD);

  // The admin should init successfully.
  TEST_ASSERT_EQUAL_INT(admin6.init(), snet::OK);

  // The admin still cannot tx or rx, since it is an admin client.
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet::DATA, 4, payload),
                        snet::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet::DATA, 5, payload),
                        snet::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet::DATA, 7, payload),
                        snet::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet::BAD);

  // The admin should successfully get info from the network.
  std::vector<int> addresses;
  addresses.clear();
  TEST_ASSERT_EQUAL_INT(admin6.info(&addresses), snet::OK);
  TEST_ASSERT_EQUAL_INT(addresses.size(), 0);

  // After a node joins the network, the node count should increase to 1.
  addresses.clear();
  TEST_ASSERT_EQUAL_INT(node4.init(), snet::OK);
  TEST_ASSERT_EQUAL_INT(admin6.info(&addresses), snet::OK);
  TEST_ASSERT_EQUAL_INT(addresses.size(), 1);
  TEST_ASSERT_EQUAL_INT(addresses[0], 4);

  // After another node joins the network, the node count should increase to 1.
  addresses.clear();
  TEST_ASSERT_EQUAL_INT(node5.init(), snet::OK);
  TEST_ASSERT_EQUAL_INT(admin6.info(&addresses), snet::OK);
  TEST_ASSERT_EQUAL_INT(addresses.size(), 2);
  TEST_ASSERT_EQUAL_INT(addresses[0], 4);
  TEST_ASSERT_EQUAL_INT(addresses[1], 5);
}

int main(int argc, char *argv[])
{
  UNITY_BEGIN();
  RUN_TEST(test_client);
  RUN_TEST(test_node);
  RUN_TEST(test_admin);
  return UNITY_END();
}
