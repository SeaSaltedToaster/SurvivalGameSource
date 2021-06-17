package survivalGame.network;

import java.net.InetAddress;

import survivalGame.network.player.NetPlayerData;

public class Client {

	//Username, level, indentification 
	private NetPlayerData playerData;
	
	//Info to send data
	private InetAddress ipAddress;
	private int port;
	
	public Client(NetPlayerData playerData, InetAddress ipAddress, int port) {
		this.playerData = playerData;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public NetPlayerData getPlayerData() {
		return playerData;
	}

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPlayerData(NetPlayerData playerData) {
		this.playerData = playerData;
	}

	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
