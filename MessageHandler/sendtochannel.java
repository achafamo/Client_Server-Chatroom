package MessageHandler;

import server.*;
import ocsf.server.*;
import java.io.*; 

/**
 *  This class handles a request from a client to login to the server.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class sendtochannel extends ServerMessageHandler
{
  private String myId;

  public sendtochannel(String str)
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
  public boolean validChannel()
  {
    //System.out.println("Validating account");
    int indexBlank = myId.indexOf(" ");
    if(indexBlank == -1)
    {
      return false;
    }
    String channelName = myId.substring(0,indexBlank);
    String message = myId.substring(indexBlank+1);
    //System.out.println(id + " " + password);
    //System.out.println(getEchoServer().getUserList());
    //System.out.println(getEchoServer().getUserList().containsKey(id));
    if (getEchoServer().channelNameExists(channelName))
    {         
      return true;
    }
    else
    {
      return false;      
    }
  }

  public String getChannel(String str)
  {
    int indexBlank = myId.indexOf(" ");
    if(indexBlank == -1)
    {
      return str;
    }
    String channelName = myId.substring(0,indexBlank);
    return channelName;
  }


  public String getMessage(String str)
  {    
    int indexBlank = myId.indexOf(" ");    
    if(indexBlank == -1)
    {
      return str;
    }
    String message = myId.substring(indexBlank+1);
    return message;
  }


  public void handleMessage()
  { 
    //System.out.println("in login handler");
    if(validChannel())
    {
      //getEchoServer().serverUI().display(getId(myId) + " has logged on");
      //getClient().setInfo("id", getId(myId));
      getEchoServer().channels().get(getChannel(myId)).sendMessageToChannel(getChannel(myId) + ": " + getClient().getInfo("id") + "> " + getMessage(myId));      
    }
    else
    {
      try
      {
        getClient().sendToClient("The channel you specified does not exist or you didn't use the correct format to send a message to channel");        
      }
      catch(IOException e){}
      
    }
  }
}
