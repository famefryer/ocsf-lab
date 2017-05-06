package ocsf_lab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;

public class MyClient extends AbstractServer {
	List<ConnectionToClient> listName = new ArrayList<>();
	String message = "";

	public MyClient(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if (msg.toString().substring(0, 4).equals("Login")) {
			client.setInfo("username", msg);
		}
		if (msg.toString().substring(0, 5).equals("Logout")) {
			clientDisconnected(client);
		}
		if (msg.toString().substring(0, 2).equals("To:")) {
			try {
				String to = "a";
				
				client.sendToClient(msg.toString().substring(3, msg.toString().length() - 1));
			} catch (IOException e) {
				System.out.println("No one use that name!!");
			}
		}
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		while (client.getInfo("username") != null) {
		}
		listName.add(client.getInfo("username").toString());
		System.out.println("Client " + client.getInfo("username") + " connected");
	}

	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		super.clientDisconnected(client);
		listName.remove(client.getInfo("username"));
	}

}
