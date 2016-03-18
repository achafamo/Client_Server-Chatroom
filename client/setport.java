package client;

import common.*;
import java.io.IOException;

public class setport extends NotConnectedClientCommand
{
  public setport(String str, ChatClient1 client)
  {
    super(str, client);
  }

  public void doCmd()
  {
	try
	{
	    getClient().setPort(Integer.parseInt(this.getStr()));
	    getClient().clientUI().display("Connection port set to " + getStr()); 
	}
	catch(Exception ex)
	{
	  getClient().clientUI().display("IOException " + ex + ", exiting.");
	  System.exit(-1);
	}
}

}