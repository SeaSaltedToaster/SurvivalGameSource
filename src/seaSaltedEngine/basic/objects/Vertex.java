package seaSaltedEngine.basic.objects;

import seaSaltedEngine.tools.math.Vector3f;

public class Vertex {

	public Vector3f position;
	public Vector3f normal;
	public Color vertexColor;
	
	public Vertex(Vector3f position, Vector3f normal) {
		this.position = position;
		this.normal = normal;
	}
	
	public Vertex(Vector3f position, Vector3f normal, Color color) {
		this.position = position;
		this.normal = normal;
		this.vertexColor = color;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getNormal() {
		return normal;
	}
	
	public Color getVertexColor() {
		return vertexColor;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}

	public void setVertexColor(Color vertexColor) {
		this.vertexColor = vertexColor;
	}
	
}
