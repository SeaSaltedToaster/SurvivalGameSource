package survivalGame.entity.core;

import java.util.HashMap;

import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.render.batch.IBatch;

public class EntityBatch implements IBatch {

	private HashMap<Entity, EntityIdentifier> entityList = new HashMap<Entity, EntityIdentifier>();
	
	@Override
	public void update() {
		
	}

	public HashMap<Entity, EntityIdentifier> getEntityList() {
		return entityList;
	}

	public void setEntityList(HashMap<Entity, EntityIdentifier> entityList) {
		this.entityList = entityList;
	}

}
