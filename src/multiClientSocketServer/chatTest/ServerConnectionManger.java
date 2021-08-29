package multiClientSocketServer.chatTest;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

import multiClientSocketServer.interfaces.ConnectionManager;
import multiClientSocketServer.main.Client;
import multiClientSocketServer.main.ClientConnection;

public class ServerConnectionManger implements ConnectionManager {

//	private HashMap<ClientConnection, String> clients = new HashMap<ClientConnection, String>();

	private ArrayList<ClientConnection> clients = new ArrayList<>();

	private DefaultListModel<String> list;

	private JTextArea textArea;

	public ServerConnectionManger(DefaultListModel<String> list, JTextArea textArea) {
		this.list = list;
		this.textArea = textArea;
	}

	@Override
	public void onDisconnect(ClientConnection client) {
		// TODO Auto-generated method stub
		clients.remove(client);
		System.out.println("client " + client.getLocalIpAddress() + " disconnected");
		updateList();
	}

	@Override
	public void onConnect(ClientConnection client) {
		// TODO Auto-generated method stub
		client.setName(client.getLocalIpAddress());
		clients.add(client);
		System.out.println(client.socket.getRemoteSocketAddress());
		System.out.println("client " + client.getLocalIpAddress() + " connected");

		updateList();
	}

	@Override
	public void onMessage(ClientConnection client, String message) {
		// TODO Auto-generated method stub
		if (message.startsWith("SetUsername")) {
			String username = message.split("SetUsername")[1].trim();
			clients.get(clients.indexOf(client)).setName(username);
			client.send("Set username to " + username);
			updateList();
			return;

		}

		broadcastMessage(client + ": " + message);

	}

	public void broadcastMessage(String message) {
		for (ClientConnection clientConnection : clients) {
			clientConnection.send(message + "\n");
		}

		textArea.setText(textArea.getText() + "" + message + "\n");
	}

	public void updateList() {
		list.clear();
		for (ClientConnection client : clients) {
			list.addElement(client.toString());
		}
	}

	@Override
	public void onConnectionError(Client client, Exception e) {
		// TODO Auto-generated method stub
		
	}

}
