//
// multi_process_test.cpp
//
// SNET Client C++ test
//

#include <iostream>

#include <unistd.h>
#include <signal.h>

#include "src/snet_client.hpp"
#include "Unity/src/unity.h"

#define ECHO_APP "echo_app"

static pid_t app_pid = -1;

void setUp(void)
{
  app_pid = fork();
  if (app_pid == 0) {
    // Child. Start app.
    execv(ECHO_APP, NULL);
    // Should not return.
  } else {
    TEST_ASSERT_NOT_EQUAL(app_pid, -1);
    // Parent.
  }
}

void tearDown(void)
{
  if (app_pid != -1) {
    TEST_ASSERT_EQUAL(kill(app_pid, SIGTERM), 0);
  }
}

static void test(void)
{
  // Reset the network to start from a known state.
  snet::client client(1);
  TEST_ASSERT_EQUAL_INT(client.reset(), snet::OK);

  // Initialize successfully.
  TEST_ASSERT_EQUAL_INT(client.init(snet::client::NODE), snet::OK);

  // Wait for the child app to boot.
  sleep(2);

  // Send to echo app at address 123.
  int addr;
  std::string payload;
  const int echo_addr = 123;
  TEST_ASSERT_EQUAL_INT(client.tx(snet::DATA, echo_addr, "hey"),
                        snet::OK);
  sleep(4);
  TEST_ASSERT_EQUAL_INT(client.rx(&addr, &payload), snet::OK);
  TEST_ASSERT_EQUAL_INT(addr, echo_addr);
  TEST_ASSERT_EQUAL_STRING(payload.c_str(), "hey");
}

int main(int argc, char *argv[])
{
  UNITY_BEGIN();
  RUN_TEST(test);
  return UNITY_END();
}
