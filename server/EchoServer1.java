package server;
 
import MessageHandler.*;
import java.io.*;

import client.*;
//import server.*;
import ocsf.server.*;
import common.*;
import java.util.*;
/**
 *  This class modifies EchoServer to complete to begin Chat phase 2.
 *  It uses messages from Client to Server that are instances
 *  of command classes that extend the class ServerMessageHandler.
 *  This class delegates responsibility for handling a message to the
 *  message itself.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @author Chris Nevison
 * @version February 2012
 */
public class EchoServer1 extends AbstractServer
{
  //Class variables *************************************************

  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  private ChatIF myServerUI;
  private HashMap<String, Channel> channels;
  private HashMap<String, String> userList;
  //Constructors ****************************************************

  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer1(int port, ChatIF serverUI)
  {
    super(port);
    myServerUI = serverUI;
    channels = new HashMap<String, Channel>();
    userList = new HashMap<String, String>(); 

    
  }
  
  //Instance methods ************************************************

  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received, an instance of a subclass of ServerMessageHandler
   * @param client The connection from which the message originated.
   */
  
  public void handleMessageFromServerUI(String message)
  {
    if(message.charAt(0) != '#')
    {
    	 sendToAllClients("SERVER MSG> " + message);
    	 myServerUI.display("SERVER MSG> "+ message);
    }
    else
    {
      message = message.substring(1);
      createAndDoCommand(message);
    }

  }

  
  private void createAndDoCommandNew(String message, ConnectionToClient client)
  {
    //System.out.println("creating login handler");    
    String commandStr;
    int indexBlank = message.indexOf(' ');
    if(indexBlank == -1)
    {
      commandStr = "MessageHandler." + message;
      message = "";
    }
    else
    {
      commandStr = "MessageHandler." + message.substring(0, indexBlank);
      message = message.substring(indexBlank+1);
    }

    try
    {
      Class[] param = {String.class};
      ServerMessageHandler cmd = (ServerMessageHandler)Class.forName(commandStr).getConstructor(param).newInstance(message);
      cmd.setServer(this);
      cmd.setConnectionToClient(client);
      //sSystem.out.println("Handling login handler");
      cmd.handleMessage();
    }
    catch(Exception ex)
    {
      System.out.println(ex);
      serverUI().display("\nNo such command " + commandStr + "\nNo action taken.");
    }
  } 
  private void createAndDoCommand(String message)
  {
    String commandStr;
    int indexBlank = message.indexOf(' ');
    if(indexBlank == -1)
    {
      commandStr = "server." + message;
      message = "";
    }
    else
    {
      commandStr = "server." + message.substring(0, indexBlank);
      message = message.substring(indexBlank+1);
    }

    try
    {
      Class[] param = {String.class, EchoServer1.class};
      ServerCommand cmd = (ServerCommand)Class.forName(commandStr).getConstructor(param).newInstance(message, this);
      cmd.doCommand();
    }
    catch(Exception ex)
    {
      serverUI().display("\nNo such command " + commandStr + "\nNo action taken.");
    }
  } 


  public ChatIF serverUI()
  {
    return myServerUI;
  }

  public HashMap<String, Channel> channels()
  {
    return channels;
  }
  public HashMap<String, String> getUserList()
  {
    return userList;
  }
  public Set<String> getChannelNames()
  {
    return channels.keySet();
  }
  public boolean channelNameExists(String name)
  {
    return (getChannelNames().contains(name));
  }
  public ConnectionToClient getClient(String id) throws IOException
  {
    Thread[] clientConnections = getClientConnections();
    for (Thread client:clientConnections)
    {
      ConnectionToClient c = (ConnectionToClient)client;
      String clientId = (String)c.getInfo("id");
      if(id.equals(clientId))
        return c;
    }
    throw new IOException(); 
  }
  public void createChannel(String name, ConnectionToClient client)
  {
    //System.out.println("in Echo Server creating channel");
    Channel newChannel = new Channel(name);
    newChannel.addClient(client);    
    channels.put(name, newChannel);
    //clientThreadGroup.remove(client);
  }
  public void handleMessageFromClient(Object message, ConnectionToClient client)
  {
    //System.out.println("recieved #login server");    
    String msg = (String)message;    
    if(msg.charAt(0) != '#')
    {
      handleStringMessageFromClient(msg, client);
    }
    else
    {
      //System.out.println("recieved #login server");
      msg = msg.substring(1);
      createAndDoCommandNew(msg, client);

    }
  }

  
  public void handleStringMessageFromClient(String msg, ConnectionToClient client)
  {
    serverUI().display((String)client.getInfo("id") + "> " + msg);
    sendToAllClients((String)client.getInfo("id") + "> " + msg);
  }

  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
	  this.serverUI().display("Server listening for connections on port " + getPort());
  }

  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    this.serverUI().display("Server has stopped listening for connections.");
  }

  synchronized protected void clientException(
		    ConnectionToClient client, Throwable exception) {
	  this.sendToAllClients("SERVER MSG> " + client.getInfo("id") + "has disconnected");
	  this.serverUI().display(client.getInfo("id") + " disconnected");
    System.out.println(exception);
  }

}