package ocsf_lab;

import java.io.IOException;
import java.util.Scanner;

import com.lloseng.ocsf.client.ObservableClient;

public class Client extends ObservableClient {

	public Client(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object message) {
		System.out.println(message);
	}

	@Override
	protected void connectionClosed() {
		System.out.println("Disconnected!!");
	}

	@Override
	protected void connectionEstablished() {
		System.out.println("Connected!!");
	}

	public static void main(String[] args) {
		Client c = new Client("10.2.12.83", 5001);
		try {
			c.openConnection();
			Scanner sc = new Scanner(System.in);
			String input;
			if (c.isConnected()) {
				input = sc.nextLine();
				if (input.equals("quit")) {
					c.closeConnection();
				}
				c.sendToServer(input);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
