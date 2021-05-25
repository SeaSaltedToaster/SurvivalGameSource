package survivalGame.world;

import java.util.Map.Entry;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import survivalGame.entity.core.EntityBatch;
import survivalGame.entity.core.EntityIdentifier;
import survivalGame.world.generation.grass.GrassRenderManager;

public class GameWorld {

	//Handles Everything to do with entities in the game world
	private static EntityBatch entities;
	
	public static void initialize() {
		//Create Entity Batch
		entities = new EntityBatch();
		
		//Initialize Terrains
		GrassRenderManager.init();
	}
	
	public static void addWorldEntity(Entity entity, EntityIdentifier identifier) {
		entities.add(entity);
		entities.getEntityList().put(entity, identifier);
	}
	
	public static void removeWorldEntity(EntityIdentifier identifier) {
		for(Entry<Entity, EntityIdentifier> ei : entities.getEntityList().entrySet()) {
			if(ei.getValue().getEntityID() == identifier.getEntityID() || ei.getValue().getType() == identifier.getType()) {
				Logger.Log("Entity Removed from world with an ID of "+ei.getValue().getEntityID());
				entities.getEntities().remove(ei.getKey());
				entities.getEntityList().remove(identifier, ei.getKey());
			}
		}
	}
	
	public static void updateEntityTransform(Transform transform, EntityIdentifier identifier) {
		for(Entry<Entity, EntityIdentifier> ei : entities.getEntityList().entrySet()) {
			if(ei.getValue().getEntityID() == identifier.getEntityID() || ei.getValue().getType() == identifier.getType()) {
				ei.getKey().setTransform(transform);
			}
		}
	}

	public static EntityBatch getMainWorldEntityBatch() {
		return entities;
	}
	
}
