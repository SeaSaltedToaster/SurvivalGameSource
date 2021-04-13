package seaSaltedEngine.basic.camera;

import seaSaltedEngine.tools.math.Vector3f;

public class Camera {

	protected Vector3f position;
	protected float yaw, pitch, roll;
	protected float lastX = 0;
	protected float lastY = 0;
	
	public Camera() {
		this.position = new Vector3f(0,1,-5);
		this.pitch = 0;
		this.yaw = 180;
		this.roll = 0;
	}
	
	public void update() {
		
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public float getRoll() {
		return roll;
	}
	
}
