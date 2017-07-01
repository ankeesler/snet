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
  snet_client::snet_client client(123);
  if (client.init() != snet_client::OK) {
    die("INIT");
  }

  while (1) {
    int src;
    std::string payload;
    snet_client::status stat = client.rx(&src, &payload);
    switch (stat) {
      case snet_client::OK:
        if (client.tx(snet_client::DATA, src, payload) != snet_client::OK) {
          die("TX");
        }
        break;
      case snet_client::NONE:
        sleep(1);
        break;
      default:
        die("RX");
    }
  }

  return 0;
}