package seaSaltedEngine.basic.camera;

import seaSaltedEngine.tools.math.Vector3f;

public class Camera {

	protected Vector3f position;
	protected float yaw, pitch, roll;
	protected float lastX = 0;
	protected float lastY = 0;
	
	protected boolean cancelUpdate;
	
	public Camera() {
		this.position = new Vector3f(0,10,0);
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

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	public void setLastX(float lastX) {
		this.lastX = lastX;
	}

	public void setLastY(float lastY) {
		this.lastY = lastY;
	}

	public void setCancelUpdate(boolean cancelUpdate) {
		this.cancelUpdate = cancelUpdate;
	}
	
}
