package survivalGame.networking.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;

import survivalGame.networking.server.packets.Packet;

public class ServerBroadcaster {
	
	public static String SERVER_MESSAGE_STARTER = "\\sm";
	public static String CLIENT_MESSAGE_STARTER = "\\cm";
	
	public static void broadcast(String message, List<Client> clients, DatagramSocket sender) {
		for (Client client : clients) {
			send(message, client, sender);
		}
	}
	
	public static void broadcastPacket(Packet packet, List<Client> clients, DatagramSocket sender) {
		for (Client client : clients) {
			send(new String(packet.getData()), client, sender);
		}
	}
	
	public static void send(String message, Client reciever, DatagramSocket sender) {
		try {
			message = message + SERVER_MESSAGE_STARTER;
			byte[] data = message.getBytes();
			
			DatagramPacket packet = new DatagramPacket(data, data.length, reciever.getAddress(), reciever.getPort());
			sender.send(packet);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
