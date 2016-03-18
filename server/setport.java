package server;

import java.io.IOException;

/**
 *  Implements client command to quit, after first closing any connection to the current host.
 *

 */
public class setport extends ServerCommand
{
  public setport(String str, EchoServer1 server)
  {
    super(str, server);
    
  }

  public void doCommand()
  {
	if (!this.getServer().isListening() && this.getServer().getNumberOfClients()==0)
		{this.getServer().setPort(Integer.parseInt(this.getStr()));
		}
	else
	{
		this.getServer().serverUI().display("Cannot set port while server is not closed");
	}	
  }
  
	  
  
  
}