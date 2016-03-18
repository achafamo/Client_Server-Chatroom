package client;
//import common.*;
import java.io.IOException;

/**
 *  Implements client command to quit, after first closing any connection to the current host.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class createchannel extends ClientCommand
{
  public createchannel(String str, ChatClient1 client)
  {
    super(str, client);
  }

  public void doCommand()
  {
    //System.out.println("in createchannel and not connected");
    try
    {
      if(getClient().isConnected())
      {
        //System.out.println("in createchannel and connected");
        getClient().sendToServer("#createchannel " + getStr());
      }
      else
      {
        getClient().clientUI().display("You can't create a channel before loging in");
      }      
    }
    catch(IOException ex)
    {
      getClient().clientUI().display("IOException " + ex + "Unable to create channel.");      
    }
  }
}



