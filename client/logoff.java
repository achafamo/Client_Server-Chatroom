package client;

import common.*;
import java.io.IOException;

//Implements client command to close connection with server without exiting

public class logoff extends ClientCommand
{
  public logoff(String str, ChatClient1 client)
  {
    super(str, client);
  }

  public void doCommand()
  {
    try
    {
      if(getClient().isConnected())
      {
        getClient().closeConnection();
      }
      else
      {
        getClient().clientUI().display("Connection already closed");
      }
    }
    catch(IOException ex)
    {
      getClient().clientUI().display("IOException " + ex + ", exiting.");
      System.exit(-1);
    }
  }
}