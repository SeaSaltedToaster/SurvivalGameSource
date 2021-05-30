package seaSaltedEngine.render.display;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import seaSaltedEngine.Engine;
import seaSaltedEngine.tools.GLFWUtils;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
	
	private static int currentWidth = 1280;
	private static int currentHeight = 720;
	
	private static long windowID;
	private static double delta;
	
	public Window(String title) {
		windowID = createWindow(title);
	}
	
	public static Window newInstance(String title) {
		return new Window(title);
	}
	
	private long createWindow(String title) {
		GLFWUtils.init();
		GLFWVidMode vidmode = glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		
		long windowID = GLFW.glfwCreateWindow(currentWidth, currentHeight, title, NULL, NULL);
		
		int x = (vidmode.width() - currentWidth);
		int y = (vidmode.height() - currentHeight);
		
		glfwSetWindowPos(windowID, x/2, y/2);	
		
		glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
		
		glfwSetInputMode(windowID, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		
		glfwShowWindow(windowID);
		
		return windowID;
	}
	
	public void update() {
    	glfwSwapBuffers(windowID);
    	glfwPollEvents();
    	
    	GLFWUtils.sync(Engine.getConfigs().getFpsCap());
    	WindowManager.updateWindowSize();
    	delta = WindowManager.getDelta();
	}
	
	public static boolean shouldClose() {
		if (!glfwWindowShouldClose(windowID))
			return false;
		return true;
	}
	
	public static void setShouldClose(boolean value) {
		glfwSetWindowShouldClose(windowID, value);
	}

	public long getWindowID() {
		return windowID;
	}

	public static double getCurrentWidth() {
		return WindowManager.getWindowSizeX(windowID);
	}

	public static double getCurrentHeight() {
		return WindowManager.getWindowSizeY(windowID);
	}
	
	public static float getAspectRatio() {
		return (float) (getCurrentHeight() / getCurrentWidth());
	}

	public static double getDelta() {
		return delta;
	}

}
