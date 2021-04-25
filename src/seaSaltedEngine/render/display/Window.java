package seaSaltedEngine.render.display;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import seaSaltedEngine.Engine;
import seaSaltedEngine.tools.GLFWUtils;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

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
	
	private void createIcon() {
		ByteBuffer bufferedImage = IconLoader.loadImageToByteBuffer("./resources/res/icon.png");
		
		GLFWImage image = new GLFWImage(bufferedImage);
		GLFWImage.Buffer images = GLFWImage.malloc(1);
        images.put(0, image);
		
		glfwSetWindowIcon(windowID, images);
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

	public long getWindowID() {
		return windowID;
	}

	public static int getCurrentWidth() {
		return currentWidth;
	}

	public static int getCurrentHeight() {
		return currentHeight;
	}

	public static double getDelta() {
		return delta;
	}

}
