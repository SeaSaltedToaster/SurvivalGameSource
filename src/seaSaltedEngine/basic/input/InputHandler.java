package seaSaltedEngine.basic.input;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.callbacks.WindowSizeCallback;
import seaSaltedEngine.render.display.Window;

public class InputHandler {

	private Mouse mouseInstance;
	private Keyboard keyboardInstance;
	
	private WindowSizeCallback size;
	
	public InputHandler(Window windowInstance) {
		mouseInstance = new Mouse();
		keyboardInstance = new Keyboard();
		size = new WindowSizeCallback();
		
		init(windowInstance);
	}
	
	public void init(Window windowInstance) {
		GLFW.glfwSetMouseButtonCallback(windowInstance.getWindowID(), mouseInstance.getButtonEvent());
		GLFW.glfwSetKeyCallback(windowInstance.getWindowID(), keyboardInstance.getEvent());
		GLFW.glfwSetCursorPosCallback(windowInstance.getWindowID(), mouseInstance.getPositionCallback());
		GLFW.glfwSetFramebufferSizeCallback(windowInstance.getWindowID(), size);
	}
	
	public static boolean isKeyPressed(int key)
	{
		 return (GLFW.glfwGetKey(Engine.getWindowInstance().getWindowID(), key) == GLFW.GLFW_PRESS);
	}
	
	public static boolean isKeyReleased(int key)
	{
	    return (GLFW.glfwGetKey(Engine.getWindowInstance().getWindowID(), key) == GLFW.GLFW_RELEASE);
	}
	
	public void pollInput() {
		mouseInstance.update();
	}

	public Mouse getMouseInstance() {
		return mouseInstance;
	}

	public Keyboard getKeyboardInstance() {
		return keyboardInstance;
	}

	public void setMouseInstance(Mouse mouseInstance) {
		this.mouseInstance = mouseInstance;
	}

	public void setKeyboardInstance(Keyboard keyboardInstance) {
		this.keyboardInstance = keyboardInstance;
	}
	
}
