package server;

import java.io.IOException;

/**
 *  Implements client command to quit, after first closing any connection to the current host.
 *

 */
public class getport extends ServerCommand
{
  public getport(String str, EchoServer1 server)
  {
    super(str, server);
  }

  public void doCommand()
  {
	int port;
    port=this.getServer().getPort();
    this.getServer().serverUI().display("Current port is " + port);
  }
}



