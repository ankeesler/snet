package com.marshmallow.snet.service;

import java.util.Properties;

public enum ConfigurationKey {
  SERVICE_PORT("12345"), // The port at which the service will run.
  SERVICES(""),          // Comma-separated names of services.
  ;

  private final String dephault;

  private ConfigurationKey(final String dephault) {
    this.dephault = dephault;
  }

  public String dephault() { return this.dephault; }

  public String get(final Properties properties) {
    return properties.getProperty(this.name(), this.dephault());
  }
}
