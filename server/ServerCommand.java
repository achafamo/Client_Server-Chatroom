package server;

import java.io.IOException;

/**
 *  This abstract class specifies a framework for any command from the server
 *  user interface to the client. Any such command must be implemented with
 *  a subclass of this class with a classname identical to the command
 *  (stripped of the '#'). An instance of the command class will be formed
 *  from the name using reflection, then its doCommand method will be
 *  executed.
 *
 */
public abstract class ServerCommand
{
  private String myString;
  private EchoServer1 myServer ;

  public ServerCommand(String str, EchoServer1 server)
  {
    myString = str;
    myServer = server;
  }

  /**
   * This method provides the command access to the server
   *
   * @return the client
   */
  protected EchoServer1 getServer()
  {
    return myServer;
  }

  /**
   * This method provides the command access to the command String (stripped of the command)
   *
   * @return command String
   */
  protected String getStr()
  {
    return myString;
  }

  /**
   * This method provides the slot that any command from the client UI to the client must fill by
   * implementing this method in the subclass that defines the command.
 * @throws IOException 
   */
  abstract public void doCommand() throws IOException;
}
