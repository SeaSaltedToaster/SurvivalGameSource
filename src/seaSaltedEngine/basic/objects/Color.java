package seaSaltedEngine.basic.objects;

import seaSaltedEngine.tools.math.Vector3f;

public class Color {
	
	protected float r,g,b;

	public Color(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public float getR() {
		return r;
	}

	public float getG() {
		return g;
	}

	public float getB() {
		return b;
	}

	public void setR(float r) {
		this.r = r;
	}

	public void setG(float g) {
		this.g = g;
	}

	public void setB(float b) {
		this.b = b;
	}
	
	public Color add(Vector3f other) {
        float x = this.r + other.x;
        float y = this.g + other.y;
        float z = this.b + other.z;
        return new Color(x, y, z);
    }
	
	public Vector3f toVector() {
		return new Vector3f(r,g,b);
	}

}
