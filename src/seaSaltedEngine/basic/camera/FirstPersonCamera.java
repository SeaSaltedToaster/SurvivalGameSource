package seaSaltedEngine.basic.camera;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.EngineConstants;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.basic.input.keybinding.Controls;
import seaSaltedEngine.render.display.Window;
import seaSaltedEngine.tools.MousePicker;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.Vector3f;

public class FirstPersonCamera extends Camera {

	private float currentSpeed = 0;
	private float upwardsSpeed = 0;
	
	private float multiplier = 25;
	private boolean uiHover = false;
	
	private MousePicker picker;
	
	public FirstPersonCamera() {
		picker = new MousePicker(this);
	}
	
	@Override
	public void update() {
		if(this.cancelUpdate) {
			lastY = (float) Mouse.getDy();
			lastX = (float) Mouse.getDx();
			return;
		}
		
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_P)) OpenGL.setPolygonWire();
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_O)) OpenGL.setPolygonFill();
		
		updateClamps();
		updatePitch();
		updateYaw();
		checkInputs();
		
		float distance = (float) ((float) Math.sin(currentSpeed) * multiplier * Window.getDelta());
		float dx = -(float)(distance * Math.sin(Math.toRadians(-yaw)));
		float dz = -(float)(distance * Math.cos(Math.toRadians(-yaw)));
		increasePosition(dx, (float) (upwardsSpeed*Window.getDelta())*multiplier, dz);
		
//		GameWorld.getMainWorldEntityBatch().getEntities().get(0).getTransform().setPosition(getRayDist(1f));
	}
	
	public Vector3f getRayDist(float dist) {
		float RAY_DIST = dist;
		float cdx = -(float)(RAY_DIST * Math.sin(Math.toRadians(-yaw)));
		float cdz = -(float)(RAY_DIST * Math.cos(Math.toRadians(-yaw)));
		return picker.getCurrentIntRay(position.add(new Vector3f(cdx*10,0,cdz*10)), 5);
	}
	
	private void updateClamps() {
		if( (int) this.pitch >= 360 )
			pitch = 0;
		if( (int) this.yaw >= 360 )
			yaw = 0;
		if( (int) this.roll >= 360 )
			roll = 0;
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
