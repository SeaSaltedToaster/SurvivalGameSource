package seaSaltedEngine.basic.input;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.event.MouseButtonEvent;
import seaSaltedEngine.basic.input.event.MousePositionCallback;
import seaSaltedEngine.render.display.WindowManager;
import seaSaltedEngine.tools.math.Vector2f;

public class Mouse {
	
	public MouseButtonEvent buttonEvent;
	public MousePositionCallback positionCallback;
	
	public static double dx, dy;
	
	public Mouse() {
		buttonEvent = new MouseButtonEvent();
		positionCallback = new MousePositionCallback();
	}
	
	public void update() {
		dx = positionCallback.getDx();
		dy = positionCallback.getDy();
	}
	
	public static double getMouseCoordsX() {
		double x = -1.0 + 2.0 * getCursorPosX() / WindowManager.getWindowSizeX(Engine.getWindowInstance().getWindowID());
		return x;
	}
	    
	public static double getMouseCoordsY() {
	    double y = 1.0 - 2.0 * getCursorPosY() / WindowManager.getWindowSizeY(Engine.getWindowInstance().getWindowID());
	    return y;
	}
	
	public static Vector2f getMousePosition() {
		return new Vector2f((float)getMouseCoordsX(),(float)getMouseCoordsY());
	}
	    
	private static double getCursorPosX() {
	    DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
	    glfwGetCursorPos(Engine.getWindowInstance().getWindowID(), posX, null);
	    return posX.get(0);
	}
	    
	private static double getCursorPosY() {
	    DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
	    glfwGetCursorPos(Engine.getWindowInstance().getWindowID(), null, posY);
	    return posY.get(0);
	}
	
	public static void setMouseVisible(boolean lock) {
	    if(lock)
	    	glfwSetInputMode(Engine.getWindowInstance().getWindowID(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
	    else
	    	glfwSetInputMode(Engine.getWindowInstance().getWindowID(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
	}

	public MouseButtonEvent getButtonEvent() {
		return buttonEvent;
	}

	public void setButtonEvent(MouseButtonEvent buttonEvent) {
		this.buttonEvent = buttonEvent;
	}

	public MousePositionCallback getPositionCallback() {
		return positionCallback;
	}

	public static double getDx() {
		return dx;
	}

	public static double getDy() {
		return dy;
	}

	public void setPositionCallback(MousePositionCallback positionCallback) {
		this.positionCallback = positionCallback;
	}

	public void setDx(double dx) {
		Mouse.dx = dx;
	}

	public void setDy(double dy) {
		Mouse.dy = dy;
	}
	
}
