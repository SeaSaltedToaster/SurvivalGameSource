package survivalGame.world.generation.grass.component;

import seaSaltedEngine.entity.component.Component;

public class GrassSwayComponent implements Component {

	@Override
	public void update() {
		
	}

	@Override
	public String getComponentType() {
		return "Grass_Sway";
	}

	@Override
	public boolean changesRenderer() {
		return false;
	}

}
