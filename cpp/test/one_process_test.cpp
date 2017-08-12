//
// one_process_test.cpp
//
// SNET Client C++ test
//

#include <iostream>

#include "src/snet_client.hpp"
#include "src/snet_node.hpp"
#include "src/snet_admin.hpp"

#include "../../../Unity/src/unity.h"

static void test_client(void)
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
  TEST_ASSERT_EQUAL_INT(client0.init(snet_client::NODE), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(client1.init(snet_client::NODE), snet_client::OK);

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

  // Nodes cannot call the info RPC API.
  int node_cnt;
  TEST_ASSERT_EQUAL_INT(client0.info(&node_cnt), snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(client1.info(&node_cnt), snet_client::BAD);
}

static void test_node(void)
{
  // The node should function very much the same way as the client, but the node
  // interface is much simpler.

  snet_node node2;
  TEST_ASSERT_EQUAL_INT(node2.get_addr(), 2);

  snet_node node3;
  TEST_ASSERT_EQUAL_INT(node3.get_addr(), 3);

  // Reset the network to start from a known state.
  TEST_ASSERT_EQUAL_INT(node2.reset(), snet_client::OK);

  // If we haven't init'd yet, we can't tx...
  std::string payload = "{\\\"foo\\\": \\\"bar\\\"}";
  TEST_ASSERT_EQUAL_INT(node2.tx(snet_client::DATA, 3, payload),
                        snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(node3.tx(snet_client::DATA, 2, payload),
                        snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(node2.tx(snet_client::DATA, 4, payload),
                        snet_client::BAD);

  // ...or rx.
  int src_addr;
  std::string rx_payload;
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(node3.rx(&src_addr, &rx_payload), snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet_client::BAD);

  // Now we should initialize successfully...
  TEST_ASSERT_EQUAL_INT(node2.init(), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(node3.init(), snet_client::OK);

  // ...and rx successfully (with no data)...
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet_client::NONE);
  TEST_ASSERT_EQUAL_INT(node3.rx(&src_addr, &rx_payload), snet_client::NONE);
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet_client::NONE);

  // ...and tx successfully...
  TEST_ASSERT_EQUAL_INT(node2.tx(snet_client::DATA, 3, payload),
         snet_client::OK);
  TEST_ASSERT_EQUAL_INT(node3.tx(snet_client::DATA, 2, payload),
         snet_client::OK);
  TEST_ASSERT_EQUAL_INT(node2.tx(snet_client::DATA, 4, payload),
         snet_client::OK);

  // ...rx successfully (with data).
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(src_addr, 3);
  TEST_ASSERT_EQUAL_STRING(payload.c_str(), rx_payload.c_str());
  TEST_ASSERT_EQUAL_INT(node3.rx(&src_addr, &rx_payload), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(src_addr, 2);
  TEST_ASSERT_EQUAL_STRING(payload.c_str(), rx_payload.c_str());
  TEST_ASSERT_EQUAL_INT(node2.rx(&src_addr, &rx_payload), snet_client::NONE);
  TEST_ASSERT_EQUAL_INT(node3.rx(&src_addr, &rx_payload), snet_client::NONE);

  // Nodes cannot call the info RPC API.
  int node_cnt;
  TEST_ASSERT_EQUAL_INT(node2.info(&node_cnt), snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(node3.info(&node_cnt), snet_client::BAD);
}

static void test_admin(void)
{
  snet_node node4;
  TEST_ASSERT_EQUAL_INT(node4.get_addr(), 4);

  snet_node node5;
  TEST_ASSERT_EQUAL_INT(node5.get_addr(), 5);

  snet_admin admin6;
  TEST_ASSERT_EQUAL_INT(admin6.get_addr(), 6);

  // Reset the network to start from a known state.
  TEST_ASSERT_EQUAL_INT(node4.reset(), snet_client::OK);

  // The admin should not be able to tx, rx, or info since it has not init'd.
  std::string payload = "{\\\"foo\\\": \\\"bar\\\"}";
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet_client::DATA, 4, payload),
                        snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet_client::DATA, 5, payload),
                        snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet_client::DATA, 7, payload),
                        snet_client::BAD);
  int src_addr;
  std::string rx_payload;
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet_client::BAD);

  // The admin should init successfully.
  TEST_ASSERT_EQUAL_INT(admin6.init(), snet_client::OK);

  // The admin still cannot tx or rx, since it is an admin client.
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet_client::DATA, 4, payload),
                        snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet_client::DATA, 5, payload),
                        snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.tx(snet_client::DATA, 7, payload),
                        snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet_client::BAD);
  TEST_ASSERT_EQUAL_INT(admin6.rx(&src_addr, &rx_payload), snet_client::BAD);

  // The admin should successfully get info from the network.
  int node_cnt;
  TEST_ASSERT_EQUAL_INT(admin6.info(&node_cnt), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(node_cnt, 0);

  // After a node joins the network, the node count should increase to 1.
  TEST_ASSERT_EQUAL_INT(node4.init(), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(admin6.info(&node_cnt), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(node_cnt, 1);

  // After another node joins the network, the node count should increase to 1.
  TEST_ASSERT_EQUAL_INT(node5.init(), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(admin6.info(&node_cnt), snet_client::OK);
  TEST_ASSERT_EQUAL_INT(node_cnt, 2);
}

int main(int argc, char *argv[])
{
  UNITY_BEGIN();
  RUN_TEST(test_client);
  RUN_TEST(test_node);
  RUN_TEST(test_admin);
  return UNITY_END();
}
