package survivalGame.networking.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Set;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.objects.Transform;
import survivalGame.entity.core.EntityIdentifier;
import survivalGame.networking.server.ServerBroadcaster;
import survivalGame.networking.server.packets.ConnectionPacket;
import survivalGame.networking.server.packets.DisconnectPacket;
import survivalGame.networking.server.packets.Packet;
import survivalGame.networking.server.packets.PacketTypes;
import survivalGame.world.GameWorld;

public class ClientThread extends Thread {

	private boolean isRunning;
	private DatagramSocket serverSocket;
	
	public void startClientThread(DatagramSocket serverSocket) {
		this.isRunning = true;
		this.serverSocket = serverSocket;
		this.setName("ClientThread");
		
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		for(Thread thread : threadSet) {
			if(thread.getName().equalsIgnoreCase(getName()))
				return;
		}
		
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
				
			}

			String message = new String(data);
			
			if(message.contains(ServerBroadcaster.SERVER_MESSAGE_STARTER)) 
				message = message.substring(0, message.indexOf(ServerBroadcaster.SERVER_MESSAGE_STARTER));
			
			parsePacket(data, packet.getAddress(), packet.getPort());
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
            packet.getData();
            //TODO Acknowledge new user join
            break;
        case DISCONNECT:
        	packet = new DisconnectPacket(data);
        	//TODO Acknowledge user left
            break;
        case MOVE:
        	//TODO Move player on client side
            break;
        case SERVER_CLOSE:
        	ClientsideManager.disconnect();
        	break;
        case ENTITY_CREATED:
        	ClientsideManager.addEntityFromServer(message);
        	Logger.Log("Entity Created on client sent from server.");
        	break;
        case ENTITY_REMOVED:
        	ClientsideManager.removeEntityFromServer(message);
        	break;
        case ENTITY_TRANSFORM_UPDATE:
        	String[] parts = message.split("-");
        	Transform transform = ClientsideManager.getTransform(parts[2]);
        	EntityIdentifier identifier = ClientsideManager.getEntityIdentifier(parts[1]);
        	GameWorld.updateEntityTransform(transform, identifier);
        	break;
        }
    }
	
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
}
