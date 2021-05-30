package survivalGame.entity.world;

import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.FrustumCullComponent;
import seaSaltedEngine.entity.component.ModelComponent;
import survivalGame.resources.Models;

public class EntityTree extends Entity {
	
	public EntityTree(Transform transform) {
		super(transform);
		transform.setScale(2);
		
		this.addComponent(new ModelComponent(Models.getModelFromID(3), 3));
		this.addComponent(new FrustumCullComponent(5));
	}

}
