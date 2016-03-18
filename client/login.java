package client;
//import server;
import common.*;
import java.io.IOException;

/**
 *  Implements client command to log in to the current host.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class login extends ClientCommand
{
  public login(String str, ChatClient1 client)
  {
    super(str, client);
  }

  public void doCommand()
  {
    //System.out.println("in docmd method of login client side");
    try
    {      
      getClient().openConnection();
      getClient().clientUI().display("Connection to " + getClient().getHost() + " opened.");
      getClient().sendToServer("#login " + getStr());  
      //System.out.println("#login id password sent to server");    
    }
    catch(Exception ex)
    {
      getClient().clientUI().display("Connection to " + getClient().getHost() + " failed.");
    }
  }

}

