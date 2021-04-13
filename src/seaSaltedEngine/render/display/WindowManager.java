package seaSaltedEngine.render.display;

import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import seaSaltedEngine.Engine;

public class WindowManager {
	    
	public static double getWindowSizeX(long windowID) {
	    IntBuffer posX = BufferUtils.createIntBuffer(1);
	    glfwGetWindowSize(windowID, posX, null);
	    return posX.get(0);
	}
	    
	public static double getWindowSizeY(long windowID) {
	    IntBuffer posY = BufferUtils.createIntBuffer(1);
	    glfwGetWindowSize(windowID, null, posY);
	    return posY.get(0);
	}
	
	public static void updateWindowSize() {
	    IntBuffer widthBuffer = BufferUtils.createIntBuffer(1); IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
	    GLFW.glfwGetWindowSize(Engine.getWindowInstance().getWindowID(), widthBuffer, heightBuffer);
	    	
	    int width = widthBuffer.get(0); int height = heightBuffer.get(0);
	    GL11.glViewport(0, 0, width, height);
	}
	
	static double lastTime;
	
	public static double getDelta() {
		double currentTime = GLFW.glfwGetTime();
		double delta = (currentTime - lastTime);
		lastTime = GLFW.glfwGetTime();
		return delta;
	}
	
}
