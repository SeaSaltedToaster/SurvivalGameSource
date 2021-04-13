package testing;

import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import survivalGame.entity.EntityPickaxeTest;
import survivalGame.entity.core.EntityBatch;

public class TestEntityBatch {

	public static EntityBatch getBatch() {
		
		EntityBatch batch = new EntityBatch();

		Entity entity = new EntityPickaxeTest(Transform.Default);
		batch.add(entity);
		
		return batch;
	}
	
}
