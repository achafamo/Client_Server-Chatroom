package MessageHandler;

import server.*;
import ocsf.server.*;
import java.io.*;
import java.util.*;
/**
 *  This class handles a request from a client to login to the server.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class privatemessage extends ServerMessageHandler
{
  private String myId;

  public privatemessage(String str)
  {
    myId = str;
  }

  /**
   * This method logs the client in by saving its id String and
   * sends a message to all clients that the new client has logged in
   * If already logged in (id String has been set) a message is sent to the
   * client and no other action is taken.
   */
  public EchoServer1 getEchoServer()
  {
    return (EchoServer1)getServer();
  }

  public String getId(String str) 
  {
    int indexBlank = str.indexOf(" ");
    String id = myId.substring(0,indexBlank);
    return id;
  }
  public String getMessage(String str)
  {
    int indexBlank = str.indexOf(" ");
    String message = myId.substring(indexBlank+1);
    return message;
  }

  public void handleMessage()
  { 
    System.out.println("in privatemessage handler");
    if(getEchoServer().getUserList().containsKey(getId(myId)))
    {
      try
      {
        ConnectionToClient user = getEchoServer().getClient(getId(myId));
        HashSet<String> blockList = (HashSet<String>)user.getInfo("block list");
        if(blockList== null || !blockList.contains(getClient().getInfo("id")))
        {
          user.sendToClient(getClient().getInfo("id") + "> " + getMessage(myId));          
        }
        else
        {
          getClient().sendToClient("SERVER MSG> You can't send this private message because the user has blocked you.");
        }

      }
      catch(IOException e)
      {
        try 
        {
          getClient().sendToClient("SERVER MSG> The user you specified is currently not logged on");
        }
        catch(IOException ex){ }
      }
    }
    else
    {
      try
      {
        getClient().sendToClient("The user that you specified does not exist");        
      }
      catch(IOException e){}      
    }
  }

}