package client;

import common.*;
import java.io.IOException;

/**
 *  Implements client command to log in to the current host.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class block extends ClientCommand
{
  public block(String str, ChatClient1 client)
  {
    super(str, client);
  }
  public boolean stringValid(String str)
  {
    return (str!= "");
  }
  public void doCommand()
  {
    if(getClient().isConnected())
    {
      try
      {
        if (stringValid(getStr()))
        {
          getClient().sendToServer("#block " + getStr());          
        }
        else
        {
          getClient().clientUI().display("That is not the correct format to block a user. Please enter #block username");
        }
      
      }
      catch(IOException ex)
      {
        getClient().clientUI().display( "block operation failed");
      }
    }
    else
    {
      getClient().clientUI().display("You are not connected");
    }
  }


}