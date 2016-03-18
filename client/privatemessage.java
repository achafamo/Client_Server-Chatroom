package client;

import common.*;
import java.io.IOException;

/**
 *  Implements client command to log in to the current host.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class privatemessage extends ClientCommand
{
  public privatemessage(String str, ChatClient1 client)
  {
    super(str, client);
  }
  public boolean stringValid(String str)
  {
    return (str.indexOf(" ")!= -1);
  }
  public void doCommand()
  {
    if(getClient().isConnected())
    {
      try
      {
        if (stringValid(getStr()))
        {
          getClient().sendToServer("#privatemessage " + getStr());          
        }
        else
        {
          getClient().clientUI().display("That is not the correct format to send a message. Please enter #privatemessage user message");
        }
      
      }
      catch(IOException ex)
      {
        getClient().clientUI().display( "private message failed");
      }
    }
    else
    {
      getClient().clientUI().display("You are not connected");
    }
  }

}