package survivalGame.entity.world;

import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.FrustumCullComponent;
import survivalGame.resources.Models;
import survivalGame.world.generation.grass.component.GrassModelComponent;
import survivalGame.world.generation.grass.component.GrassSwayComponent;

public class EntityGrass extends Entity {

	private Color grassColor;
	
	public EntityGrass(Transform transform) {
		super(transform);
		
		this.addComponent(new GrassSwayComponent());
		this.addComponent(new GrassModelComponent(Models.getModelFromID(4)));
		this.addComponent(new FrustumCullComponent(5));
		this.grassColor = new Color(0.2f,0.8f,0.2f);
	}

	public Color getGrassColor() {
		return grassColor;
	}

	public void setGrassColor(Color grassColor) {
		this.grassColor = grassColor;
	}
	
}
