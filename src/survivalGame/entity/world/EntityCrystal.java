package survivalGame.entity.world;

import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.FrustumCullComponent;
import seaSaltedEngine.entity.component.ModelComponent;
import survivalGame.resources.Models;

public class EntityCrystal extends Entity {
	
	public EntityCrystal(Transform transform) {
		super(transform);
		
		this.addComponent(new ModelComponent(Models.getModelFromID(5), 5));
		this.addComponent(new FrustumCullComponent(2));
	}


}
