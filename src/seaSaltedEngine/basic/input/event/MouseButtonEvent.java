package seaSaltedEngine.basic.input.event;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import seaSaltedEngine.guis.Listener;

public class MouseButtonEvent extends GLFWMouseButtonCallback implements IKeyEvent {

	private List<Listener> listeners = new ArrayList<Listener>();
	
	private boolean isRightDown;
	private boolean isLeftDown;
	
	@Override
	public void invoke(long arg0, int arg1, int arg2, int arg3) {
		onMouseButtonEvent(arg1, arg2, arg3);
	}
	
	public void onMouseButtonEvent(int key, int action, int mods) {
		notifyListeners(key, action);
		
		if(action == GLFW.GLFW_PRESS && key == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
			isLeftDown = true;
		} else {
			isLeftDown = false;
		}
	}
	
	public void notifyListeners(int key, int action) {
		for(Listener listener : listeners) {
			listener.notify(key+";:;"+action);
		}
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	public List<Listener> getListeners() {
		return listeners;
	}

	public boolean isRightDown() {
		return isRightDown;
	}

	public boolean isLeftDown() {
		return isLeftDown;
	}
	
}
