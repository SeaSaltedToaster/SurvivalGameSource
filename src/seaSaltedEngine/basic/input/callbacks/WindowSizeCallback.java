package seaSaltedEngine.basic.input.callbacks;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.opengl.GL11;

import seaSaltedEngine.basic.logger.Logger;

public class WindowSizeCallback extends GLFWFramebufferSizeCallback {

	@Override
	public void invoke(long arg0, int arg1, int arg2) {
		GL11.glViewport(0, 0, arg1, arg2);
		Logger.Log("Display resized to "+arg1+"x"+arg2);
	}

}
