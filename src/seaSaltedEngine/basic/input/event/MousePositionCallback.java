package seaSaltedEngine.basic.input.event;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MousePositionCallback extends GLFWCursorPosCallback {

	private double dx, dy;
	
	public MousePositionCallback() {
		
	}
	
	@Override
	public void invoke(long arg0, double arg1, double arg2) {
		this.dx = arg1;
		this.dy = arg2;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

}
