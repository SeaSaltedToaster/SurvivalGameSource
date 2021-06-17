package survivalGame.entity.components.collision;

import seaSaltedEngine.entity.component.Component;

public class SphereColliderComponent implements Component {

	public SphereColliderComponent() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public String getComponentType() {
		return "Sphere_Collider";
	}

	@Override
	public boolean changesRenderer() {
		return false;
	}

}
