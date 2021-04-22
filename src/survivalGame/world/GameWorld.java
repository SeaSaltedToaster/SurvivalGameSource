package survivalGame.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import survivalGame.entity.core.EntityBatch;
import survivalGame.entity.core.EntityIdentifier;

public class GameWorld {

	private static EntityBatch mainWorldEntityBatch;
	private static List<Entity> toAddOnFrame;
	
	public static void initialize() {
		mainWorldEntityBatch = new EntityBatch();
		toAddOnFrame = new ArrayList<Entity>();
	}
	
	public static void update() {
		mainWorldEntityBatch.getEntities().addAll(toAddOnFrame);
		toAddOnFrame.clear();
	}
	
	public static void addWorldEntity(Entity entity, EntityIdentifier identifier) {
		mainWorldEntityBatch.add(entity);
		mainWorldEntityBatch.getEntityList().put(entity, identifier);
	}
	
	public static void removeWorldEntity(EntityIdentifier identifier) {
		for(Entry<Entity, EntityIdentifier> ei : mainWorldEntityBatch.getEntityList().entrySet()) {
			if(ei.getValue().getEntityID() == identifier.getEntityID() || ei.getValue().getType() == identifier.getType()) {
				Logger.Log("Entity Removed from world with an ID of "+ei.getValue().getEntityID());
				mainWorldEntityBatch.getEntities().remove(ei.getKey());
				mainWorldEntityBatch.getEntityList().remove(identifier, ei.getKey());
			}
		}
	}
	
	public static void updateEntityTransform(Transform transform, EntityIdentifier identifier) {
		for(Entry<Entity, EntityIdentifier> ei : mainWorldEntityBatch.getEntityList().entrySet()) {
			if(ei.getValue().getEntityID() == identifier.getEntityID() || ei.getValue().getType() == identifier.getType()) {
				ei.getKey().setTransform(transform);
			}
		}
	}

	public static EntityBatch getMainWorldEntityBatch() {
		return mainWorldEntityBatch;
	}

	public static List<Entity> getToAddOnFrame() {
		return toAddOnFrame;
	}
	
}
