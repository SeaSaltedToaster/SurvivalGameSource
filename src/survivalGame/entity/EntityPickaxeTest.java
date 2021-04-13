package survivalGame.entity;

import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.ModelComponent;
import survivalGame.resources.Models;

public class EntityPickaxeTest extends Entity {
	
	public EntityPickaxeTest(Transform transform) {
		super(transform);
		
		this.addComponent(new ModelComponent(Models.getModelFromID(1), 1));
	}

}
