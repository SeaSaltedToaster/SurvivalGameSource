package survivalGame.networking.server.packets.entity;

import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.Component;
import seaSaltedEngine.entity.component.ModelComponent;
import survivalGame.entity.core.EntityIdentifier;
import survivalGame.networking.server.packets.Packet;

public class EntityCreatedPacket extends Packet {

	public Entity entity;
	public EntityIdentifier identifier;
	
	public EntityCreatedPacket(Entity entity, EntityIdentifier identifier) {
		super(04);
		
		this.entity = entity;
		this.identifier = identifier;
	}

	@Override
	public byte[] getData() {
		String baseEntityString = new String("04" + "EntityCreated-[Identifier,"+identifier.getEntityID()+","+identifier.getType().name()+",]-[Transform,"+entity.getTransform().toString()+",]-");
		baseEntityString = handleEntityComponent(baseEntityString);
		baseEntityString = baseEntityString + ";|;"; //End Identifier
		return baseEntityString.getBytes();
	}
	
	private String handleEntityComponent(String string) {
		String newString = string;
		for(Component component : entity.getComponents()) {
			switch(component.getComponentType()) {
				case MODEL:
					ModelComponent modelComponent = (ModelComponent) component;
					newString = newString + "[,Component,MODEL," + modelComponent.getModelID() + ",]-";
					break;
			}
		}
		return newString;
	}

	public Entity getEntity() {
		return entity;
	}

	public EntityIdentifier getIdentifier() {
		return identifier;
	}

}
