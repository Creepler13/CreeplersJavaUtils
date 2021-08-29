package multiClientSocketServer.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;

import multiClientSocketServer.interfaces.ConnectionManager;

public class ClientConnection {

	public Socket socket;
	private ConnectionManager connectionManager;

	private Thread thread;
	private AtomicBoolean running = new AtomicBoolean(true);

	private BufferedWriter writer;

	private String name = "";

	public void establish(Socket socket, ConnectionManager clientManager) throws IOException {
		this.socket = socket;
		this.connectionManager = clientManager;
		this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		this.thread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					connected();

					while (running.get()) {
						String line = reader.readLine();
						if (line == null) {
							disconnect();
							continue;
						}
						recievedMessage(line);
					}

					reader.close();
				} catch (SocketException e) {
					try {
						disconnect();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	private void connected() {
		connectionManager.onConnect(this);
	}

	private void recievedMessage(String message) {
		connectionManager.onMessage(this, message);
	}

	public Boolean isConnected() {
		return socket.isConnected();
	}

	public void disconnect() throws IOException {
		running.set(false);
		writer.close();
		connectionManager.onDisconnect(this);
	}

	public String getLocalIpAddress() {
		return this.socket.getInetAddress().getHostAddress();
	}

	public String getLocalHostName() {
		return this.socket.getInetAddress().getHostName();
	}

	public int getLocalPort() {
		return this.socket.getLocalPort();
	}

	public void send(String message) {
		try {
			System.out.println(message);
			writer.write(message);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		if (name != "")
			return name;
		// TODO Auto-generated method stub
		return super.toString();
	}

}
