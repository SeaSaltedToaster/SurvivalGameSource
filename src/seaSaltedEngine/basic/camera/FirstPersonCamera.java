package seaSaltedEngine.basic.camera;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.EngineConstants;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.basic.input.keybinding.Controls;
import seaSaltedEngine.render.display.Window;
import seaSaltedEngine.tools.OpenGL;

public class FirstPersonCamera extends Camera {

	private float currentSpeed = 0;
	private float upwardsSpeed = 0;
	
	private float multiplier = 15;
	private boolean uiHover = false;
	
	@Override
	public void update() {
		if(this.cancelUpdate) {
			lastY = (float) Mouse.getDy();
			lastX = (float) Mouse.getDx();
			return;
		}
		
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_P)) OpenGL.setPolygonWire();
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_O)) OpenGL.setPolygonFill();
		
		updatePitch();
		updateYaw();
		checkInputs();
		
		float distance = (float) ((float) Math.sin(currentSpeed) * multiplier * Window.getDelta());
		float dx = -(float)(distance * Math.sin(Math.toRadians(-yaw)));
		float dz = -(float)(distance * Math.cos(Math.toRadians(-yaw)));
		increasePosition(dx, (float) (upwardsSpeed*Window.getDelta())*multiplier, dz);
	}
	
	private void updatePitch() {
		pitch += (Mouse.getDy() - lastY) * EngineConstants.sensitivity / 2;
		lastY = (float) Mouse.getDy();
	}
	
	private void updateYaw() {
		yaw += (Mouse.getDx() - lastX) * EngineConstants.sensitivity / 2;
		lastX = (float) Mouse.getDx();
	}
	
	private void checkInputs() {
		if(InputHandler.isKeyPressed(Controls.getControl("Move Forward").getKey())) {
			this.currentSpeed = 1;
		} else if(InputHandler.isKeyPressed(Controls.getControl("Move Backward").getKey())) {
			this.currentSpeed = -1;
		} else {
			this.currentSpeed = 0;
		}
		
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			this.upwardsSpeed = 1;
		} else if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			this.upwardsSpeed = -1;
		} else {
			this.upwardsSpeed = 0;
		}
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}

	public boolean isUiHover() {
		return uiHover;
	}

	public void setUiHover(boolean uiHover) {
		this.uiHover = uiHover;
	}
	
}
