package seaSaltedEngine.entity.component;

public class FrustumCullComponent implements Component {

	private float radius = 25;
	
	public FrustumCullComponent(float objectSize) {
		this.radius = objectSize;
	}
	
	@Override
	public void update() {
		
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	@Override
	public String getComponentType() {
		return "FrustumCull";
	}

	@Override
	public boolean changesRenderer() {
		return false;
	}

}
