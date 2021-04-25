package survivalGame.networking.server;

public class ServerMain {
	
	public static void main(String[] args) {
		Server.open(25565);
		while(Server.isRunning()) {
			Server.updateServer();
		}
		Server.stopServer();
	}

}
