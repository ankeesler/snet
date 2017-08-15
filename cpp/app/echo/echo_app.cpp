//
// echo_app.cpp
//
// An app that simply echoes everything that it receives.
//

#include "src/snet_client.hpp"

#include <iostream>
#include <unistd.h>

static void die(const std::string reason)
{
  std::cout << "ECHO APP: " << reason << " FAILED. EXITING." << std::endl;
  exit(1);
}

int main(int argc, char *argv[])
{
  snet::client client(123);
  if (client.init(snet::client::NODE) != snet::OK) {
    die("INIT");
  }

  while (1) {
    int src;
    std::string payload;
    snet::status stat = client.rx(&src, &payload);
    switch (stat) {
      case snet::status::OK:
        if (client.tx(snet::DATA, src, payload) != snet::OK) {
          die("TX");
        }
        break;
      case snet::NONE:
        sleep(1);
        break;
      default:
        die("RX");
    }
  }

  return 0;
}