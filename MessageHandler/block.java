package MessageHandler;

import ocsf.server.*;
import java.io.*;
import java.util.*;


public class block extends ServerMessageHandler
{
  private String myId;

  public block(String str)
  {
    myId = str;
  }
  public void setBlocker(ConnectionToClient client)
  {
	HashSet<String> whoBlocksMe; 
	if(client.getInfo("whoBlocksMe")!= null)
	{
	  whoBlocksMe = (HashSet<String>)client.getInfo("whoBlocksMe");
	  whoBlocksMe.add((String)getClient().getInfo("id"));
	  client.setInfo("whoBlocksMe", whoBlocksMe);
	}
	else
	{
		whoBlocksMe = new HashSet<String>();
		whoBlocksMe.add((String)getClient().getInfo("id"));
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
		System.out.println((String)client.getInfo("id") + " " + id);
		String clientId = (String)client.getInfo("id"); 
		System.out.println(clientId.equals(id));
		if(clientId.equals(id))
		{
			setBlocker(client);
			exists = true;
		}
	}
	return exists;  
  }
  
  public void handleMessage()
  {
	if(idExists(myId))
	{
		HashSet<String> blockList = new HashSet<String>();
		//HashMap savedInfo = getClient().getSavedInfo();
		if(getClient().getInfo("block list")!= null)
		{
			blockList = (HashSet<String>)getClient().getInfo("block list");
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
			getClient().sendToClient("SERVER MSG> The user " + myId + " does not exist");			
		}
		catch(Exception e){}

	}
  }

}