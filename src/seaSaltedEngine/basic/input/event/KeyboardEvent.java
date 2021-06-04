package seaSaltedEngine.basic.input.event;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFWKeyCallback;

import seaSaltedEngine.guis.Listener;

public class KeyboardEvent extends GLFWKeyCallback implements IKeyEvent {

	private List<Listener> listeners = new ArrayList<Listener>();
	private List<Listener> Rlisteners = new ArrayList<Listener>();
	
	@Override
	public void invoke(long arg0, int arg1, int arg2, int arg3, int arg4) {
		onKeyInputRecieved(arg1, arg3, arg4);
	}
	
	private void onKeyInputRecieved(int key, int action, int mods) {
		notifyListeners(key, action);
	}
	
	public void notifyListeners(int key, int action) {
		listeners.removeAll(Rlisteners);
		Rlisteners.clear();
		for(Listener listener : listeners) {
			listener.notify(key+":;:"+action);
		}
	}
	
	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	public void removeListener(Listener listener) {
		Rlisteners.add(listener);
	}
	
	public List<Listener> getListeners() {
		return listeners;
	}

}
