package main;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import interfaces.ConnectionManager;

public class Client {

	private final ClientConnection connection = new ClientConnection();
	private final Socket socket = new Socket();
	private final ConnectionManager connectionManager;

	public Client(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;

	}

	public void connect(String ip, int port) throws UnknownHostException, IOException {
		socket.connect(new InetSocketAddress(InetAddress.getByName(ip), port));
		connection.establish(socket, connectionManager);

	}

	public Boolean isConnected() {
		return connection.isConnected();
	}

	public void send(String message) {
		connection.send(message);
	}

	public void disconnect() {
		try {
			connection.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
