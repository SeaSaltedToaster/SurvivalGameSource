package seaSaltedEngine.basic.input.event;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.guis.Listener;

public interface IKeyEvent {

	public List<Listener> listeners = new ArrayList<Listener>();
	
	public default void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	void notifyListeners(int arg0, int arg1);
	
}
