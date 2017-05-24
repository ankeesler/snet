package com.marshmallow.snet.handler;

import java.util.Arrays;
import java.util.function.Function;

import com.marshmallow.snet.service.IMessageHandler;
import com.marshmallow.snet.service.Message;

public class AdminHandler implements IMessageHandler {

  public static final String SFD = "ADMIN ";

  private static class Command {
    public final String prefix;
    public final String description;
    public final Function<String, String> handler;
    public Command(final String prefix,
                   final String description,
                   final Function<String, String> handler) {
      this.prefix = prefix;
      this.description = description;
      this.handler = handler;
    }

    @Override
    public String toString() {
      return this.prefix + ": " + this.description;
    }
  }

  private static final Command[] commands = {
    new Command("info",
                "Print out general information about the service.",
                AdminHandler::infoCommandHandler), 
    new Command("help",
                "Display available admin options.",
                AdminHandler::helpCommandHandler),
    new Command("tuna",
                "Fish! Marlin!",
                AdminHandler::tunaCommandHandler), 
  };

  @Override
  public Message messageReceived(Message message) {
    if (message.getData().startsWith(SFD)) {
      String text = message.getData().substring(SFD.length());
      for (Command command : commands) {
        if (text.startsWith(command.prefix)) {
          return Message.makeUnicast(command.handler.apply(text), message.getAddress());
        }
      }
      return Message.makeUnicast("Unknown " + SFD + "command: " + text, message.getAddress());
    }
    return null;
  }

  private static String infoCommandHandler(final String text) {
    return "We can put admin stuff here.";
  }

  private static String helpCommandHandler(final String text) {
    return Arrays.toString(commands).replace(',', '\n'); 
  }

  private static String tunaCommandHandler(final String text) {
    return "Oh you knewit.";
  }
}
