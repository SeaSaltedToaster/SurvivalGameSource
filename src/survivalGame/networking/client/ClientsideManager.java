package survivalGame.networking.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.Component;
import seaSaltedEngine.entity.component.ComponentType;
import seaSaltedEngine.entity.component.ModelComponent;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.entity.EntityPickaxeTest;
import survivalGame.entity.core.EntityIdentifier;
import survivalGame.entity.core.EntityType;
import survivalGame.networking.server.Server;
import survivalGame.resources.Models;
import survivalGame.world.GameWorld;

public class ClientsideManager {

	private static Client clientInstance;
	
	public static void connectHost(survivalGame.networking.server.Client client) {
		clientInstance = new Client(client.getAddress().getHostAddress(), client.getPort(), client.getName());
	}
	
	public static void removeEntityFromServer(String data) {
		String[] tags = data.split("-");
		
		EntityIdentifier identifier = getEntityIdentifier(tags[1]);

		GameWorld.removeWorldEntity(identifier);
	}
	
	public static void addEntityFromServer(String data) {
		String[] tags = data.split("-");
		
		EntityIdentifier identifier = getEntityIdentifier(tags[1]);
		Transform transform = getTransform(tags[2]);
		
		Entity entity = new Entity(transform);
		handleEntityComponents(entity, tags);
		
		GameWorld.addWorldEntity(entity, identifier);
	}
	
	public static void handleEntityComponents(Entity entity, String[] parts) {	
		int size = parts.length - 1;
		for(int i = 3; i < size; i++) {
			buildComponent(parts[i], entity);
		}
	}
	
	private static void buildComponent(String message, Entity entity) {
		String[] tags = message.split(",");
		
		ComponentType type = ComponentType.valueOf(tags[2]);
		Component component = null;
		switch(type) {
			case MODEL:
				component = new ModelComponent(Models.getModelFromID(Integer.parseInt(tags[3])), Integer.parseInt(tags[3]));
				break;
		}
		entity.addComponent(component);
	}
	
	public static Transform getTransform(String tag) {
		String[] parts = tag.split(",");
		
		Vector3f position = posFromString(parts[1]);
		float x = Float.parseFloat(parts[2]);
		float y = Float.parseFloat(parts[3]);
		float z = Float.parseFloat(parts[4]);
		
		return new Transform(position, x, y, z);
	}
	
	private static Vector3f posFromString(String pos) {
		String[] parts = pos.split(":");
		float x = Integer.parseInt(parts[0]);
		float y = Integer.parseInt(parts[1]);
		float z = Integer.parseInt(parts[2]);
		return new Vector3f(x,y,z);
	}
	
	public static EntityIdentifier getEntityIdentifier(String tag) {
		String[] parts = tag.split(",");
		
		int id = Integer.parseInt(parts[1]);
		EntityType type = EntityType.valueOf(parts[2]);
		
		return new EntityIdentifier(id, type);
	}
	
	public static void addHost(String name) {
		survivalGame.networking.server.Client client = null;
		try {
			client = new survivalGame.networking.server.Client(name, InetAddress.getLocalHost(), 25565);
			Server.getClients().add(client);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		ClientsideManager.connectHost(client);
	}
	
	public static void connectUser(String name) {
		survivalGame.networking.server.Client client = null;
		try {
			client = new survivalGame.networking.server.Client(name, InetAddress.getLocalHost(), 25565);
			Server.getClients().add(client);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		ClientsideManager.connectHost(client);
	}
	
	public static void disconnect() {
		clientInstance.disconnect();
	}

	public static Client getClientInstance() {
		return clientInstance;
	}
	
}
