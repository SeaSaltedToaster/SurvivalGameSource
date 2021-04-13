package seaSaltedEngine.render.batch;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.entity.Entity;

public interface IBatch {

	List<Entity> entities = new ArrayList<Entity>();
	
	void update();
	
	public default void add(Entity mesh) {
		entities.add(mesh);
	}
	
	public default List<Entity> getEntities() {
		return entities;
	}
	
}
