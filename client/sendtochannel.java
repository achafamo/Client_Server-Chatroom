package client;

import common.*;
import java.io.IOException;

/**
 *  Implements client command to log in to the current host.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class sendtochannel extends ClientCommand
{
  public sendtochannel(String str, ChatClient1 client)
  {
    super(str, client);
  }

  public void doCommand()
  {
    try
    {
      if(getClient().isConnected())
      {
        //System.out.println("in createchannel and connected");
        getClient().sendToServer("#sendtochannel " + getStr());
      }
      else
      {
        getClient().clientUI().display("You can't send a message to channel before loging in");
      }      
    }
    catch(IOException ex)
    {
      getClient().clientUI().display("IOException " + ex + "Unable to send message to channel.");      
    }
  }
}

