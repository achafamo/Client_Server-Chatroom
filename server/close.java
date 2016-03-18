package server;

import java.io.IOException;

public class close extends ServerCommand
{
	 public close(String str, EchoServer1 server)
	  {
	    super(str, server);
	  }
	
	public void doCommand()
	{
		try {
			String msg2=new String("SERVER MSG> SERVER CLOSING! DISCONNECTING!");
			this.getServer().sendToAllClients(msg2);
			this.getServer().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}