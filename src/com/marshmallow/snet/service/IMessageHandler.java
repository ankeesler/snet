package com.marshmallow.snet.service;

public interface IMessageHandler {
  /** Handle the received message and return a response message.
   *  If the response message is null, no message will be sent in response.
   */
  public Message messageReceived(final Message message);
}
