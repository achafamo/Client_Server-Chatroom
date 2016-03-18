package client;

import java.io.IOException;
 
public class gethost extends ClientCommand
{
  public gethost(String str, ChatClient1 client)
  {
    super(str, client);
  }

  public void doCommand()
  {
    try
    {
      	String host = getClient().getHost();
      	System.out.println(host);
        getClient().clientUI().display("The host of the current connection is " + host + ".");  
    }
    catch(Exception ex)
    {
      getClient().clientUI().display("IOException " + ex + ", exiting.");
      System.exit(-1);
    }
  }
}