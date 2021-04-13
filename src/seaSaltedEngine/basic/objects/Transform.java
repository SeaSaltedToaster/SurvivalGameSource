package seaSaltedEngine.basic.objects;

import seaSaltedEngine.tools.math.Vector3f;

public class Transform {

	private Vector3f position;
	private float rx,ry,rz;
	
	public Transform(Vector3f position, float rx, float ry, float rz) {
		this.position = position;
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getRx() {
		return rx;
	}

	public float getRy() {
		return ry;
	}

	public float getRz() {
		return rz;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setRx(float rx) {
		this.rx = rx;
	}

	public void setRy(float ry) {
		this.ry = ry;
	}

	public void setRz(float rz) {
		this.rz = rz;
	}
	
	public String toString() {
		return position.toString()+","+rx+","+ry+","+rz;
	}
	
	public static Transform Default = new Transform(new Vector3f(0,0,0),0,0,0); 
	
}
