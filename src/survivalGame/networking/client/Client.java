package survivalGame.networking.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import survivalGame.networking.server.ServerBroadcaster;
import survivalGame.networking.server.packets.ConnectionPacket;
import survivalGame.networking.server.packets.DisconnectPacket;

public class Client {
	
	private DatagramSocket socket;
	public InetAddress address;
	public int port;
	
	private String username;
	private boolean connected;
	private ClientThread thread;

	public Client(String address, int port, String name) {
		try {
			this.address = InetAddress.getByName(address);
			this.port = port;
			this.username = name;
			this.socket = new DatagramSocket();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		thread = new ClientThread();
		thread.startClientThread(socket);
		
		ConnectionPacket packet = new ConnectionPacket(username);
		packet.writeData(this);
		connected = true;
	}
	
	public void send(String message) {
		try {
			message += ServerBroadcaster.CLIENT_MESSAGE_STARTER;
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			socket.send(packet);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		if(!connected)
			return;
		
		DisconnectPacket packet = new DisconnectPacket(username);
		packet.writeData(this);
		
		thread.setRunning(false);
		socket.close();
		connected = false;
	}

}
