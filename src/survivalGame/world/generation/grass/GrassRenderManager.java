package survivalGame.world.generation.grass;

import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.render.batch.IBatch;
import survivalGame.entity.core.EntityBatch;
import survivalGame.world.generation.grass.renderer.GrassRenderer;

public class GrassRenderManager {

	private static GrassRenderer renderer;
	private static IBatch grassBatch;
	
	public static void init() {
		renderer = new GrassRenderer();
		grassBatch = getGrassBatch();
	}
	
	public static void renderGrass() {
		renderer.render(grassBatch);
	}
	
	private static EntityBatch getGrassBatch() {
		EntityBatch batch = new EntityBatch();
		batch.getEntities().clear();
		
		return batch;
	}
	
	public static void addEntity(Entity entity) {
		grassBatch.add(entity);
	}

	public static IBatch getGrass() {
		return grassBatch;
	}
	
}
