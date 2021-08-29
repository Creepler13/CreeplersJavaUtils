package multiClientSocketServer.interfaces;

import multiClientSocketServer.main.Client;
import multiClientSocketServer.main.ClientConnection;

public interface ConnectionManager {

	public void onDisconnect(ClientConnection client);

	public void onConnect(ClientConnection client);

	public void onMessage(ClientConnection client, String message);

	public void onConnectionError(Client client, Exception e);

}
