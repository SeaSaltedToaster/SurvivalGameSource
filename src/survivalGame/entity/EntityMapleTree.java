package survivalGame.entity;

import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.ModelComponent;
import survivalGame.resources.Models;

public class EntityMapleTree extends Entity {
	
	public EntityMapleTree(Transform transform) {
		super(transform);
		
		this.addComponent(new ModelComponent(Models.getModelFromID(3), 1));
	}

}
