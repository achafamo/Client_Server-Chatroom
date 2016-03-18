package client;
import java.io.IOException;

/**
 *  Implements client command to quit, after first closing any connection to the current host.
 *
 * @author Chris Nevison
 * @version February 2012
 */
public class whoblocksme extends ClientCommand
{
  public whoblocksme(String str, ChatClient1 client)
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
        getClient().sendToServer("#whoblocksme " + getStr());
      }
      else
      {
        getClient().clientUI().display("You can't see the list of people who have blocked you before loging in");
      }      
    }
    catch(IOException ex)
    {
      getClient().clientUI().display("IOException " + ex + "Unable to join channel.");      
    }
  }
}
