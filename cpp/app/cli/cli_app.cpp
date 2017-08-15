//
// cli_app.cpp
//
// SNET Client CLI application.
//

#include <vector>
#include <sstream>
#include <map>

#include "src/snet_node.hpp"
#include "src/snet_admin.hpp"

// ----------------------------------------------------------------------------
// CLI declaration

class cli {
public:
  class handler {
  public:
    virtual bool handle(const std::vector<std::string>&) = 0;
    virtual const std::string usage(void) const = 0;
  };

  cli(const std::string&);
  void add_handler(handler *);
  void run(void);
  void print_usage(void) const;

private:
  const std::string m_prompt;
  std::vector<handler *> m_handlers;
};

// ----------------------------------------------------------------------------
// Main

// -------------------------------------
// CLI handlers

class node_handler : public cli::handler {
public:
  static node_handler *get_instance(void)
  {
    static node_handler instance;
    return &instance;
  }

  const std::string usage(void) const
  {
    std::ostringstream oss;
    oss << "node add" << std::endl;
    oss << "node <address> [init|rx|reset]" << std::endl;
    oss << "node <address> tx <type> <dst> <payload>";
    return oss.str();
  }

  void print_usage(void) const
  {
    std::cout << usage() << std::endl;
  }

  bool handle(const std::vector<std::string>& args)
  {
    int arg_cnt = args.size();
    if (arg_cnt == 0 || args[0].compare("node") != 0) {
      return false;
    }

    if (arg_cnt == 1) {
      print_usage();
    } else if (args[1].compare("add") == 0) {
      snet_node *node = new snet_node();
      int addr = node->get_addr();
      m_nodes[addr] = node;
      std::cout << "add " << addr << std::endl;
    } else {
      int addr;
      bool correct_addr = get_num(args[1], &addr);
      std::map<int, snet_node*>::iterator it = m_nodes.find(addr);
      if (!correct_addr || it == m_nodes.end()) {
        std::cout << "unknown address: " << args[1] << std::endl;
        print_usage();
      } else if (arg_cnt >= 3) {
        snet_node *node = it->second;
        snet_client::status status;
        if (args[2].compare("init") == 0) {
          status = node->init();
          std::cout << "init: ";
          std::cout << snet_client::status_to_str(status);
          std::cout << std::endl;
        } else if (args[2].compare("tx") == 0) {
          int type, dst;
          if (arg_cnt != 6) {
            std::cout << "wrong command count" << std::endl;
            print_usage();
          } else if (!get_num(args[3], &type)) {
            std::cout << "unknown type: " << args[3] << std::endl;
          } else if (!get_num(args[4], &dst)) {
            std::cout << "unknown destination: " << args[4] << std::endl;
          } else {
            std::string payload = args[5];
            status = node->tx((snet_client::packet_type)type, dst, payload);
            std::cout << "tx: ";
            std::cout << snet_client::status_to_str(status);
            std::cout << std::endl;
          }
        } else if (args[2].compare("rx") == 0) {
          int src_addr;
          std::string payload;
          status = node->rx(&src_addr, &payload);
          std::cout << "rx: ";
          std::cout << snet_client::status_to_str(status);
          if (status == snet_client::OK) {
            std::cout << ", src: " << src_addr;
            std::cout << ", payload: " << payload;
          }
          std::cout << std::endl;
        } else if (args[2].compare("reset") == 0) {
          status = node->reset();
          std::cout << "reset: ";
          std::cout << snet_client::status_to_str(status);
          std::cout << std::endl;
        } else {
          std::cout << "unknown command: " << args[2] << std::endl;
          print_usage();
        }
      } else {
        std::cout << "wrong command count" << std::endl;
        print_usage();
      }
    }
    return true;
  }

private:
  static bool get_num(const std::string& s, int *addr)
  {
    try {
      *addr = std::stoi(s);
    } catch (...) {
      return false;
    }
    return true;
  }

  // Hide the default constructor. Callers should use get_instance().
  node_handler(void) { }

  std::map<int, snet_node*> m_nodes;
};

class admin_handler : public cli::handler {
public:
  static admin_handler *get_instance(void)
  {
    static admin_handler instance;
    return &instance;
  }

  const std::string usage(void) const
  {
    return "admin [init|info|reset]";
  }

  void print_usage(void) const
  {
    std::cout << usage() << std::endl;
  }

  bool handle(const std::vector<std::string>& args)
  {
    int arg_cnt = args.size();
    if (arg_cnt == 0 || args[0].compare("admin") != 0) {
      return false;
    }

    snet_client::status status;
    if (arg_cnt == 1) {
      print_usage();
    } else if (args[1].compare("init") == 0) {
      status = m_admin.init();
      std::cout << "init: ";
      std::cout << snet_client::status_to_str(status);
      std::cout << std::endl;
    } else if (args[1].compare("info") == 0) {
      int node_cnt;
      status = m_admin.info(&node_cnt);
      std::cout << "info: ";
      std::cout << snet_client::status_to_str(status);
      if (status == snet_client::OK) {
        std::cout << ", node_cnt: " << node_cnt;
      }
      std::cout << std::endl;
    } else if (args[1].compare("reset") == 0) {
      status = m_admin.reset();
      std::cout << "reset: ";
      std::cout << snet_client::status_to_str(status);
      std::cout << std::endl;
    } else {
      print_usage();
    }
    return true;
  }

private:
  // Hide the default constructor. Callers should use get_instance().
  admin_handler(void) { }

  snet_admin m_admin;
};

// -------------------------------------
// Really main

int main(int argc, char *argv[])
{
  cli c("cli> ");
  c.add_handler(node_handler::get_instance());
  c.add_handler(admin_handler::get_instance());
  c.run();
  return 0;
}

// ----------------------------------------------------------------------------
// CLI implementation

cli::cli(const std::string& prompt) : m_prompt(prompt)
{
}

void cli::add_handler(cli::handler *h)
{
  m_handlers.push_back(h);
}

void cli::run(void)
{
  while (true) {
    std::string line;
    std::cout << m_prompt;
    std::getline(std::cin, line);
    if (line.empty()) {
      continue;
    }

    bool handled = false;
    std::istringstream iss(line);
    std::vector<std::string> args((std::istream_iterator<std::string>(iss)),
                                  std::istream_iterator<std::string>());
    for (std::vector<cli::handler *>::iterator it = m_handlers.begin();
         it != m_handlers.end();
         it++) {
      handled |= (*it)->handle(args);
    }

    if (!handled) {
      print_usage();
    }
  }
}

void cli::print_usage(void) const
{
  std::cout << m_handlers.size() << " handlers; usage:" << std::endl;
  for (std::vector<cli::handler *>::const_iterator it = m_handlers.cbegin();
       it != m_handlers.cend();
       it++) {
    std::cout << (*it)->usage() << std::endl;
  }
}