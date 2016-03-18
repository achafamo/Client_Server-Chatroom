package MessageHandler;

import server.*;
import ocsf.server.*;
import java.io.*;
import java.util.*;


public class createchannel extends ServerMessageHandler
{
  private String channelName;

  public createchannel(String str)
  {
    channelName = str;
  }
  protected EchoServer1 getEchoServer()
  {
    return (EchoServer1)getServer();
  }

  
  public void handleMessage()
  {
  	//System.out.println("handling message in ChannelHandler");
	if(!getEchoServer().channelNameExists(channelName))
	{
		//System.out.println("name does not exist");
		getEchoServer().createChannel(channelName, getClient());
		try
		{
			getClient().sendToClient("SERVER MSG> The Channel " + channelName + " has been created");	
		}
		catch(IOException e){}
	
		//getClient().changeChannel(channelName);
	}
	else
	{
		try
		{
			getClient().sendToClient("SERVER MSG> The Channel Name already exists.");			
		}
		catch(IOException e){}
	}
  }

}