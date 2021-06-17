package seaSaltedEngine.basic.input.callbacks;

import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

import seaSaltedEngine.basic.logger.Logger;

public class WindowSizeCallback extends GLFWFramebufferSizeCallback {

	@Override
	public void invoke(long arg0, int arg1, int arg2) {
		GLFWVidMode vidmode = glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		int x = (vidmode.width() - arg1);
		int y = (vidmode.height() - arg2);
		GL11.glViewport(x, y, arg1, arg2);
		Logger.Log("Display resized to "+arg1+"x"+arg2);
	}

}
