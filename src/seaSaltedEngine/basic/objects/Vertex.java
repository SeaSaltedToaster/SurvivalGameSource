package seaSaltedEngine.basic.objects;

import seaSaltedEngine.tools.math.Vector3f;

public class Vertex {

	public Vector3f position;
	public Vector3f normal;
	
	public Vertex(Vector3f position, Vector3f normal) {
		this.position = position;
		this.normal = normal;
	}
	public Vector3f getPosition() {
		return position;
	}
	public Vector3f getNormal() {
		return normal;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}
	
}
