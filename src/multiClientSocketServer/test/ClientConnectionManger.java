package multiClientSocketServer.test;

import javax.swing.JTextPane;

import multiClientSocketServer.code.interfaces.ConnectionManager;
import multiClientSocketServer.code.main.Client;
import multiClientSocketServer.code.main.ClientConnection;

public class ClientConnectionManger implements ConnectionManager {

	private JTextPane text;

	public ClientConnectionManger(JTextPane text) {
		// TODO Auto-generated constructor stub
		this.text = text;
	}

	@Override
	public void onDisconnect(ClientConnection client) {
		// TODO Auto-generated method stub
		System.out.println("Disconnected");
	}

	@Override
	public void onConnect(ClientConnection client) {
		// TODO Auto-generated method stub
		System.out.println("Connected");
		text.setText(
				text.getText() + "Welcome to this Chat! you can set you username with 'SetUsername username'" + "\n");
	}

	@Override
	public void onMessage(ClientConnection client, String message) {
		text.setText(text.getText() + message + "\n");
	}

	@Override
	public void onConnectionError(Client client, Exception e) {
		// TODO Auto-generated method stub
		
	}

}
