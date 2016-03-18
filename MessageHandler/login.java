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
public class login extends ServerMessageHandler
{
  private String myId;

  public login(String str)
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
  public boolean validAccount()
  {
    //System.out.println("Validating account");
    int indexBlank = myId.indexOf(" ");
    if(indexBlank == -1)
    {
      return false;
    }
    String id = myId.substring(0,indexBlank);
    String password = myId.substring(indexBlank+1);
    //System.out.println(id + " " + password);
    //System.out.println(getEchoServer().getUserList());
    //System.out.println(getEchoServer().getUserList().containsKey(id));
    if (getEchoServer().getUserList().containsKey(id))
    {         
      return (password.equals(getEchoServer().getUserList().get(id)));
    }
    else
    {
      setupAccount(id, password);      
    }
    return true;
  }

  public void setupAccount(String id, String password)
  {
    getClient().setInfo("id", id);
    getEchoServer().getUserList().put(id, password);

  }

  public String getId(String str)
  {
    int indexBlank = myId.indexOf(" ");
    if(indexBlank == -1)
    {
      return str;
    }
    String id = myId.substring(0,indexBlank);
    return id;
  }


  public void handleMessage()
  { 
    //System.out.println("in login handler");
    if(validAccount())
    {
      getEchoServer().serverUI().display(getId(myId) + " has logged on");
      getClient().setInfo("id", getId(myId));
      getServer().sendToAllClients("SERVER MSG> " + getId(myId) + " has joined");      
    }
    else
    {
      try
      {
        getClient().sendToClient("SERVER MSG> Please enter your correct credentials");        
      }
      catch(IOException e){}
      
    }
  }

}