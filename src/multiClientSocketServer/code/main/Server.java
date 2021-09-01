package multiClientSocketServer.code.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import multiClientSocketServer.code.interfaces.ConnectionManager;

public class Server {

	private final ConnectionManager clientManager;

	private ServerSocket serverSocket;

	public Boolean started = false;

	public Server(String ip, int port, ConnectionManager clientManager) {
		this.clientManager = clientManager;
		try {
			this.serverSocket = new ServerSocket();

			this.serverSocket.bind(new InetSocketAddress(ip, port));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {

		if (!started)
			new Thread(new Runnable() {

				@Override
				public void run() {
					started = true;
					while (true) {
						try {
							ClientConnection connection = new ClientConnection();
							Socket sock = serverSocket.accept();
							connection.establish(sock, clientManager);
							clientManager.onConnect(connection);
						} catch (IOException e) {
							try {
								serverSocket.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(0);
						}
					}

				}
			}).start();

	}

	public String getIpAddress() {
		return this.serverSocket.getInetAddress().getHostAddress();
	}

	public String getHostName() {
		return this.serverSocket.getInetAddress().getHostName();
	}

	public int getPort() {
		return this.serverSocket.getLocalPort();
	}

	public ConnectionManager getClientManager() {
		return clientManager;
	}

}
