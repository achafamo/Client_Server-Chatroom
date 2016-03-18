package client;

import common.*;
import java.io.IOException;

public class sethost extends NotConnectedClientCommand
{
  public sethost(String str, ChatClient1 client)
  {
    super(str, client);
  }

  public void doCmd()
  {
	try
	{
	    getClient().setHost(this.getStr());
	    getClient().clientUI().display("Connection host set to " + getStr());
	}
	catch(Exception ex)
	{
	  getClient().clientUI().display("IOException " + ex + ", exiting.");
	  System.exit(-1);
	}
}

}