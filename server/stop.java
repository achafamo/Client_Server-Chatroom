package server;

import java.io.IOException;

public class stop extends ServerCommand
{
	 public stop(String str, EchoServer1 server)
	  {
	    super(str, server);
	  }
	
	public void doCommand()
	{
		if (this.getServer().isListening()==true)
		{
			//this.getServer().serverStopped();
			String msg=new String("SERVER MSG> WARNING - Server has stopped listening");
			this.getServer().sendToAllClients(msg);
			this.getServer().stopListening();
		}
		else
		{
			this.getServer().serverUI().display("Server was not listening");
		}
		
	}
}