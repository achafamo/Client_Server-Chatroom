package SimpleChatServer;

// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

import java.io.*;

import common.*;
import client.*;
import ocsf.server.*;
import server.EchoServer1;


public class ServerConsole implements ChatIF
{
 
  final public static int DEFAULT_PORT = 5555;

  //Instance variables **********************************************


  EchoServer1 server;

  //Constructors ****************************************************

 
  public ServerConsole(int port)
  {
    try
    {
      server= new EchoServer1(port, this);
      server.listen();
    }
    catch(Exception exception)
    {
      System.out.println("Error: Can't setup server!"
                + " Terminating server.");
      System.exit(-1);
    }
  }


  //Instance methods ************************************************

  /**
   * This method waits for input from the console.  Once it is
   * received, it sends it to the server's message handler.
   */
  public void accept()
  {
    try
    {
      BufferedReader fromConsole =
        new BufferedReader(new InputStreamReader(System.in));
      String message;

      while (true)
      {
        message = fromConsole.readLine();
        server.handleMessageFromServerUI(message);
      }
    }
    catch (Exception ex)
    {
      System.out.println
        ("Unexpected error while reading from console!try again");
      	this.accept();
      	
    }
  }

  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */
  public void display(String message)
  {
    System.out.println("> " + message);
  }


  //Class methods ***************************************************

  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] The host to connect to.
   */
  public static void main(String[] args)
  {
    int port = 0;  //The port number
    try
    {
      port = Integer.parseInt(args[0]);
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      port = DEFAULT_PORT;
    }
    
    ServerConsole chat= new ServerConsole(port);
    chat.accept();  //Wait for console data
  }
}
//End of ConsoleChat class
