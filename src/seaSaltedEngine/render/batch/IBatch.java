package seaSaltedEngine.render.batch;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.entity.Entity;

public interface IBatch {

	List<Entity> entities = new ArrayList<Entity>();
	List<Entity> toAdd = new ArrayList<Entity>();
	
	BatchType batchType = BatchType.STATIC;
	
	public default void add(Entity mesh) {
		toAdd.add(mesh);
	}
	
	public default List<Entity> getEntities() {
		entities.addAll(toAdd);
		toAdd.clear();
		return entities;
	}

	public default BatchType getBatchType() {
		return batchType;
	}
	
	Object clone() throws CloneNotSupportedException;
	
}
