package survivalGame.networking.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import survivalGame.networking.server.packets.ConnectionPacket;
import survivalGame.networking.server.packets.DisconnectPacket;
import survivalGame.networking.server.packets.Packet;
import survivalGame.networking.server.packets.PacketTypes;

public class ServerThread extends Thread {

	private boolean isRunning;
	private DatagramSocket serverSocket;
	
	public void startServerThread(DatagramSocket serverSocket) {
		this.isRunning = true;
		this.serverSocket = serverSocket;
		
		this.start();
	}
	
	@Override
	public void run() {
		while(isRunning) {
			
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				serverSocket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String message = new String(data);
			if(!message.contains(ServerBroadcaster.CLIENT_MESSAGE_STARTER)) 
				continue;
			message = message.substring(0, message.indexOf(ServerBroadcaster.CLIENT_MESSAGE_STARTER)).replace("\"" , "").replace("/", "");
			
			parsePacket(message.getBytes(), packet.getAddress(), packet.getPort());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet = null;
        switch (type) {
        default:
        case INVALID:
        	
            break;
        case LOGIN:
            packet = new ConnectionPacket(data);
            String username = ((ConnectionPacket) packet).getUsername();
            Server.addPlayer(new Client(username, address, port), username);
            break;
        case DISCONNECT:
        	packet = new DisconnectPacket(data);
        	String dcusername = ((DisconnectPacket) packet).getUsername();
            Server.removePlayer(new Client(dcusername, address, port), dcusername);
            break;
        case MOVE:
        	
            break;
        }
    }

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
}
