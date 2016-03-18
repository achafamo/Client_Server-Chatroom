package server;

import java.io.IOException;

public class start extends ServerCommand
{
  public start(String str, EchoServer1 server)
  {
    super(str, server);
  }

  public void doCommand()
  {
	  if (!this.getServer().isListening())
	  {
		  try {
			this.getServer().listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  else
		  this.getServer().serverUI().display("Cannot start listening while server is not closed");
  }
}
