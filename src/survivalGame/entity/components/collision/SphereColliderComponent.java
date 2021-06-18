package survivalGame.entity.components.collision;

import seaSaltedEngine.collision.PhysicsManager;
import seaSaltedEngine.collision.objects.CollisionSphere;
import seaSaltedEngine.entity.component.Component;
import seaSaltedEngine.tools.math.Vector3f;

public class SphereColliderComponent implements Component {

	private CollisionSphere sphere;
	
	public SphereColliderComponent(Vector3f position, float radius) {
		sphere = new CollisionSphere(position, radius, radius, PhysicsManager.getDynamicsWorld());
	}

	@Override
	public void update() {
		sphere.getShape();
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
