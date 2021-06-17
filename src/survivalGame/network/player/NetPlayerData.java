package survivalGame.network.player;

import java.util.UUID;

public class NetPlayerData {

	//Player net data
	private String username;
	private UUID id;

	public NetPlayerData(String username, UUID id) {
		this.username = username;
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public UUID getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	
}
