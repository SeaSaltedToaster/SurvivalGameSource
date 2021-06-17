package survivalGame.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import survivalGame.network.player.NetPlayerData;

public class ClientNetSetup {

	private static int CLIENT_PORT = 25575;
	
	public static Client registerClientData(String username, UUID id) {
		InetAddress address = null;
		int port = CLIENT_PORT;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		NetPlayerData data = handlePlayerData(username, id);
		return new Client(data, address, port);
	}
	
	public static NetPlayerData handlePlayerData(String username, UUID id) {
		return new NetPlayerData(username, id);
	}

}
