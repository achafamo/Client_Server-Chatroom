package server;

import server.*;
import ocsf.server.*;
import java.io.*;

/**
 *  This class defines a message handler to simple request that a String be displayed.
 */
public class ServerChannelMessageHandler extends ServerNonLoginHandler
{ 
  private String myString;
  private String channelName;

  public ServerChannelMessageHandler(String str, String channelName)
  {
    myString = str;
    channelName  = channelName;
  }
  protected EchoServer1 getServer()
  {
    return (EchoServer1)getServer();
  }

  /**
   *  This method has the message String displayed on the server console and
   *  sent to all clients.
   */
  public void handleMess()
  {    
    Channel myChannel = getServer().channels().get(channelName);
    System.out.println(channelName + ": " + (String)getClient().getInfo("id") + ">" + myString);
    myChannel.sendMessageToChannel(channelName + ": " + (String)getClient().getInfo("id") + ">" + myString);    
    // System.out.println((String)getClient().getInfo("id") + "> " + myString);
    // getServer().sendToAllClients((String)getClient().getInfo("id") + "> " + myString);
  }

}