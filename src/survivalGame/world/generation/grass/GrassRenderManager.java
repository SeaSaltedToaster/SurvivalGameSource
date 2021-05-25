package survivalGame.world.generation.grass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seaSaltedEngine.entity.Entity;
import survivalGame.entity.core.EntityBatch;
import survivalGame.world.generation.grass.renderer.GrassRenderer;

public class GrassRenderManager {

	private static GrassRenderer renderer;
	private static List<Entity> grass;
	
	public static void init() {
		renderer = new GrassRenderer();
		grass = new ArrayList<Entity>();
	}
	
	public static void renderGrass() {
		EntityBatch batch = getGrassBatch();
		renderer.render(batch);
	}
	
	private static EntityBatch getGrassBatch() {
		EntityBatch batch = new EntityBatch();
		batch.getEntities().clear();
		
		List<Entity> entityList = grass;
		batch.getEntities().addAll(entityList);
		
		return batch;
	}

	public static List<Entity> getGrass() {
		return grass;
	}
	
}
