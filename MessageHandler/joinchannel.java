package MessageHandler;

import server.*;
import ocsf.server.*;
import java.io.*;
import java.util.*;


public class joinchannel extends ServerMessageHandler
{
  private String channelName;

  public joinchannel(String str)
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
			getEchoServer().channels().get(channelName).addClient(getClient());
			//System.out.println("past adding client");
			getClient().sendToClient("You have been added to channel " + channelName);			
			getEchoServer().channels().get(channelName).sendMessageToChannel("SERVER MSG> " + getClient().getInfo("id") + " has joined channel " + channelName);	
		}
		catch(IOException e){}
	}
  }

}