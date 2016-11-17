package com.marshmallow.snet.core;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Log {
  public static final boolean USE_STDOUT = true;
  public static final String FILENAME = "snet.log";

  private static Log instance;

  private PrintStream out;

  private Log() {
    if (USE_STDOUT) {
      out = System.out;
    } else {
      try {
        out = new PrintStream(FILENAME);
      } catch (FileNotFoundException fnfe) {
        System.err.println("Could not create log (" + fnfe + "). Exiting.");
        System.exit(-1);
      }
    }
  }

  public static Log instance() {
    if (instance == null) {
      instance = new Log();
    }
    return instance;
  }

  public void note(final Class<?> clazz, final String message) {
    out.printf("%.3f: NOTE: %s: %s\n", System.currentTimeMillis() / 1000.0, clazz.getName(), message);
    out.flush();
  }
}
