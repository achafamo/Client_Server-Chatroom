package server;

import ocsf.server.*;
import java.io.*;
import java.util.*;


public class ServerBlockHandler extends ServerMessageHandler
{
  private String myId;

  public ServerBlockHandler(String str)
  {
    myId = str;
  }
  public void setBlocker(ConnectionToClient client)
  {
	ArrayList<String> whoBlocksMe; 
	if(client.getSavedInfo().containsKey("whoBlocksMe"))
	{
	  whoBlocksMe = (ArrayList<String>)client.getInfo("whoBlocksMe");
	  whoBlocksMe.add((String)client.getInfo("id"));
	  client.setInfo("whoBlocksMe", whoBlocksMe);
	}
  }
  public boolean idExists(String id )
  {
	boolean exists = false;
	Thread[] clientConnections = getServer().getClientConnections(); 
	
	for (Thread connection:clientConnections)
	{
		ConnectionToClient client = (ConnectionToClient)connection;
		if((client.getInfo("id")==id))
		{
			setBlocker(getClient());
			exists = true;
		}
	}
	return exists;  
  }
  
  public void handleMessage()
  {
	if(idExists(myId))
	{
		ArrayList<Object> blockList = new ArrayList<Object>();
		HashMap savedInfo = getClient().getSavedInfo();
		if(savedInfo.containsKey("block list"))
		{
			blockList = (ArrayList<Object>)getClient().getInfo("block list");
			blockList.add(myId);
		}
		else
		{
			blockList.add(myId);
			getClient().setInfo("block list", blockList );	
		}	
		try 
		{
			getClient().sendToClient("SERVER MSG> The user " + myId + " has been blocked");						
		}
		catch(Exception e){}
	}
	else
	{
		try
		{
			getClient().sendToClient("SERVER MSG> The user" + myId + "does not exist");			
		}
		catch(Exception e){}

	}
  }

}