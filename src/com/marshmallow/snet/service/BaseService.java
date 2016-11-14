package com.marshmallow.snet.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marshmallow.snet.core.Log;

public class BaseService implements IService, ConnectionListener.IHandler, Runnable {

  private static class Client {
    private static int ID = 1;

    public final int id;
    public final Socket socket;
    public final PrintWriter toClient;
    public final LineNumberReader fromClient;

    public Client(final Socket socket) throws IOException {
      this.id = ID ++;
      this.socket = socket;
      this.toClient = new PrintWriter(this.socket.getOutputStream());
      this.fromClient = new LineNumberReader(new InputStreamReader(this.socket.getInputStream()));
    }

    public void destory() {
      try { this.socket.close();     } catch (Exception e) { }
      try { this.toClient.close();   } catch (Exception e) { }
      try { this.fromClient.close(); } catch (Exception e) { }
    }

    @Override
    public String toString() {
      return String.format("Client(%d, %s)", this.id, this.socket);
    }
  }

  private static final int BACKLOG_SIZE = 10;

  private final ServerSocket socket;
  private final List<IMessageHandler> handlers;

  private final Map<Integer, Client> connections;

  private final Thread thread;
  private volatile boolean spinning;

  public BaseService(final InetAddress address, final int port) throws IOException {
    this.socket = new ServerSocket(port, BACKLOG_SIZE, address);
    this.handlers = new ArrayList<IMessageHandler>();

    this.connections = new HashMap<Integer, Client>();
    new ConnectionListener(socket, this);

    this.thread = new Thread(this, this.getClass().toString() + ":Thread");
    this.spinning = false;
    this.thread.start();
    while (!this.spinning) { /* wait for boot */ }
    log("Started");
  }

  @Override
  public InetAddress getAddress() {
    return this.socket.getInetAddress();
  }

  @Override
  public int getPort() {
    return this.socket.getLocalPort();
  }

  @Override
  public int getClientCount() {
    return this.connections.size();
  }

  @Override
  public void addMessageHandler(final IMessageHandler handler) {
    this.handlers.add(handler);
  }

  private void send(final Message message) {
    if (message.getUnicast()) {
      sendUnicast(message.getAddress(), message.getData());
    } else {
      sendBroadcast(message.getAddress(), message.getData());
    }
  }

  private boolean sendUnicast(final int destination, final String message) {
    Client client = null;
    synchronized (this.connections) {
      client = this.connections.get(destination);
    }
    if (client == null) {
      log("Cannot send to unknown connection: " + destination);
      return false;
    } else {
      boolean error;
      client.toClient.println(message);
      error = client.toClient.checkError();
      if (error) {
        log("Failed to send message (" + message + ") to client: " + client);
        try {client.socket.close();} catch (IOException ioe) { } // mark for removal
      } else {
        log("Sent message (" + message + ") to client: " + client);
      }
      return !error;
    }
  }

  private void sendBroadcast(final int source, final String message) {
    // Create a copy of our connections. This is so we don't have to
    // make the synchronized block as big when we work on the connections.
    Map<Integer, Client> copyOfConnections;
    synchronized (this.connections) {
      copyOfConnections = new HashMap<Integer, Client>(this.connections);
    }

    for (final Integer id : copyOfConnections.keySet()) {
      if (id != source && !sendUnicast(id, message)) {
        log("Could not broadcast to client: " + copyOfConnections.get(id));
      }
    }
  }

  @Override
  public void connectionEstablished(final Socket connection) {
    try {
      Client client = new Client(connection); 
      synchronized(this.connections) {
        this.connections.put(client.id, client);
      }
      log("Added client: " + client);
    } catch (Exception exception) {
      log("Cannot create client for connection (" + connection + "): " + exception);
    }
  }

  @Override
  public void connectionException(final Exception exception) {
    log(ConnectionListener.class.toString() + " exception: " + exception);
  }

  @Override
  public void run() {
    this.spinning = true;
    while (this.spinning) {
      // Create a copy of our connections. This is so we don't have to
      // make the synchronized block as big when we work on the connections.
      Map<Integer, Client> copyOfConnections;
      List<Integer> toRemove = new ArrayList<Integer>();
      synchronized (this.connections) {
        copyOfConnections = new HashMap<Integer, Client>(this.connections);
      }

      // For each connection, if there is an error, then set it to be removed.
      // If there is anything waiting for us, pass it up.
      for (Integer id : copyOfConnections.keySet()) {
        Client client = copyOfConnections.get(id);
        Socket connection = client.socket;
        if (connection.isClosed() || !connection.isConnected()) {
          toRemove.add(id);
        } else {
          process(client);
        }
      }

      // Remove the erred connections from above.
      synchronized (this.connections) {
        for (Integer id : toRemove) {
          Client anyLastWords = this.connections.remove(id);
          log("Removed client: " + anyLastWords);
          anyLastWords.destory();
        }
      }
    }
  }

  private void process(final Client client) {
    String message = null;
    try {
      if (client.fromClient.ready()) {
        message = client.fromClient.readLine();
      }
    } catch (IOException ioe) {
      log("IOException reading from client " + this + ": " + ioe);
    }

    if (message != null) {
      log("Received message (" + message + ") from client: " + client);
      for (final IMessageHandler handler : this.handlers) {
        Message response = handler.messageReceived(Message.makeUnicast(message, client.id));
        if (response != null) {
          this.send(response);
        }
      }
    }    
  }

  protected void log(final String message) {
    log(this.getClass(), message);
  }

  protected static void log(final Class<?> clazz, final String message) {
    Log.instance().note(clazz.toString(), message);
  }
}
