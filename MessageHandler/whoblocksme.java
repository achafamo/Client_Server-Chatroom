package MessageHandler;

import server.*;
import ocsf.server.*;
import java.io.*;
import java.util.*;


public class whoblocksme extends ServerMessageHandler
{
  private String channelName;

  public whoblocksme(String str)
  {
    channelName = str;
  }
  protected EchoServer1 getEchoServer()
  {
    return (EchoServer1)getServer();
  }

  
  public void handleMessage()
  {
	try
	{
    if(getClient().getInfo("whoBlocksMe")!=null)
    {
      getClient().sendToClient("SERVER MSG> People who block you:\n" + getClient().getInfo("whoBlocksMe"));  
    }
    else
    {
      getClient().sendToClient("SERVER MSG> No one has blocked you"); 
    }			
	}
	catch(IOException e){}
  }    
}