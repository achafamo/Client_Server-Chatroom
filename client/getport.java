package client;

import java.io.IOException;
 
public class getport extends ClientCommand
{
  public getport(String str, ChatClient1 client)
  {
    super(str, client);
  }

  public void doCommand()
  {
    try
    {     
      	int port = getClient().getPort();      	
        getClient().clientUI().display("The port of the current connection is " + port + ".");
    }
    catch(Exception ex)
    {
      getClient().clientUI().display("IOException " + ex + ", exiting.");
      System.exit(-1);
    }
  }
}