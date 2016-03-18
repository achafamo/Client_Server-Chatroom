package server;
import ocsf.server.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class Channel
{
	String channelName;
	private ArrayList<ConnectionToClient> clientList;
	private AbstractServer server;
	private boolean notPublic;
	public Channel(String name)
	{
		channelName = name;
	}


	public void addClient(ConnectionToClient client)
	{
		if(!(clientList==null))
			clientList.add(client);
		else
		{
			clientList = new ArrayList<ConnectionToClient>();
			clientList.add(client);
		}
	}
	public boolean removeClient(ConnectionToClient client)
	{
		if (clientList.contains(client))
		{
			clientList.remove(clientList.indexOf(client));
		}
		else
		{
			return false;
		}
		return true;
	}
	public void sendMessageToChannel(Object message)
	{
		for(int i = 0; i<clientList.size(); i++)
		{
	      try
	      {
	      	ConnectionToClient client = clientList.get(i);
	        client.sendToClient(message);
	      }
	      catch (Exception ex) {}
		}
	}
	public boolean channelEmpty()
	{
		return (clientList.size()==0);
	}

}