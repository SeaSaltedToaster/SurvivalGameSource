package survivalGame.entity.core;

public class EntityIdentifier {
	
	public int entityID;
	public EntityType type;
	
	public EntityIdentifier(int entityID, EntityType type) {
		this.entityID = entityID;
		this.type = type;
	}

	public int getEntityID() {
		return entityID;
	}

	public EntityType getType() {
		return type;
	}

	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

}
