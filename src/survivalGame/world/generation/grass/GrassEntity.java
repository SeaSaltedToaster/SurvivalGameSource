package survivalGame.world.generation.grass;

import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import survivalGame.resources.Models;
import survivalGame.world.generation.grass.component.GrassModelComponent;
import survivalGame.world.generation.grass.component.GrassSwayComponent;

public class GrassEntity extends Entity {

	private Color grassColor;
	
	public GrassEntity(Transform transform) {
		super(transform);
		
		this.addComponent(new GrassSwayComponent());
		this.addComponent(new GrassModelComponent(Models.getModelFromID(4)));
		this.grassColor = new Color(0,1,0);
	}

	public Color getGrassColor() {
		return grassColor;
	}

	public void setGrassColor(Color grassColor) {
		this.grassColor = grassColor;
	}
	
}
