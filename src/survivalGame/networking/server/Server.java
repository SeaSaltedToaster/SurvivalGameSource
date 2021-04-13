package survivalGame.networking.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.entity.EntityPickaxeTest;
import survivalGame.entity.core.EntityIdentifier;
import survivalGame.entity.core.EntityType;
import survivalGame.networking.server.packets.ServerClosePacket;
import survivalGame.networking.server.packets.entity.EntityCreatedPacket;

public class Server {
	
	private static DatagramSocket serverSocket;
	private static ServerThread thread;
	private static ServerCommandThread commandThread;
	
	private static List<Client> clients = new ArrayList<Client>();
	
	public static void open(int port) {
		try {
			serverSocket = new DatagramSocket(port);
			thread = new ServerThread();
			thread.startServerThread(serverSocket);
			commandThread = new ServerCommandThread();
			commandThread.startServerThread(serverSocket);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		Logger.ServerLog("Sucessfully Created Server.");
	}
	
	public static void updateServer() {
		
	}
	
	public static void stopServer() {
		ServerClosePacket closePacket = new ServerClosePacket();
		closePacket.writeData();
		
		sendDataToAllClients("03Server_Closed_By_Host");
		
		thread.setRunning(false);
		commandThread.setRunning(false);
		serverSocket.close();
	}
	
	public static void addPlayer(Client addClient, String username) {
		for(Client client : clients) {
			if(client.getAddress().equals(addClient.getAddress())) {
				Logger.ServerLog(username + " has joined the game."+" [" + addClient.getAddress().getHostAddress().replace("/", "") + ":" + addClient.port + "]");
				clients.add(addClient);
				
				EntityCreatedPacket entityPacket = new EntityCreatedPacket(new EntityPickaxeTest(new Transform(new Vector3f(0,0,0),0,0,0)), new EntityIdentifier(1, EntityType.PICKAXE_TEST));
				entityPacket.writeData();
				
				return;
			}
		}
	}
	
	public static void removePlayer(Client removeClient, String username) {
		for(Client client : clients) {
			if(client.getAddress().equals(removeClient.getAddress())) {
				clients.remove(client);
				Logger.ServerLog(username + " has left the game." + " [" + removeClient.getAddress().getHostAddress() + ":" + removeClient.port + "]");
				return;
			}
		}
	}
	
	public static void send(String message, Client reciever, DatagramSocket sender) {
		try {
			message = message + ServerBroadcaster.SERVER_MESSAGE_STARTER;
			byte[] data = message.getBytes();
			
			DatagramPacket packet = new DatagramPacket(data, data.length, reciever.getAddress(), reciever.getPort());
			sender.send(packet);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void sendDataToAllClients(String data) {
		for(Client client : clients) {
			send(data, client, serverSocket);
		}
	}

	public static DatagramSocket getServerSocket() {
		return serverSocket;
	}

	public static ServerThread getThread() {
		return thread;
	}

	public static List<Client> getClients() {
		return clients;
	}

}
