package MessageHandler;

import server.*;
import ocsf.server.*;
import java.io.*;
import java.util.*;


public class listchannels extends ServerMessageHandler
{
  private String channelName;

  public listchannels(String str)
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
		getClient().sendToClient("SERVER MSG> Channel List:\n" + getEchoServer().getChannelNames());	
	}
	catch(IOException e){}
  }
}