package server;

import java.io.IOException;

/**
 *  Implements client command to quit, after first closing any connection to the current host.
 *

 */
public class quit extends ServerCommand
{
  public quit(String str, EchoServer1 server)
  {
    super(str, server);
  }

  public void doCommand() throws IOException
  {
    String msg= new String("SERVER MSG> server has quit");
    getServer().sendToAllClients(msg);
    this.getServer().close();
    this.getServer().serverUI().display("Server Terminated");
    System.exit(0);  
  }
}



