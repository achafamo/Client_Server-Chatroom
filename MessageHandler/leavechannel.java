package MessageHandler;

import server.*;
import ocsf.server.*;
import java.io.*;
import java.util.*;


public class leavechannel extends ServerMessageHandler
{
  private String channelName;

  public leavechannel(String str)
  {
    channelName = str;
  }
  protected EchoServer1 getEchoServer()
  {
    return (EchoServer1)getServer();
  }

  
  public void handleMessage()
  {  	
  	//System.out.println("handling joinchannel message");
	if(!getEchoServer().channelNameExists(channelName))
	{
		try
		{
			getClient().sendToClient("SERVER MSG> The Channel " + channelName + " does not exist");	
		}
		catch(IOException e){}
	
		//getClient().changeChannel(channelName);
	}
	else
	{
		//System.out.println("channel name exists");
		try
		{
			if(getEchoServer().channels().get(channelName).removeClient(getClient()))
			{
				getClient().sendToClient("You have been removed from channel " + channelName);		
			}
			//System.out.println("past adding client");
						
		}
		catch(IOException e){}
	}
  }

}