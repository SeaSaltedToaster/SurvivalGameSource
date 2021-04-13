package seaSaltedEngine.basic.input;

import seaSaltedEngine.basic.input.event.KeyboardEvent;

public class Keyboard {

	public KeyboardEvent event;
	
	public Keyboard() {
		event = new KeyboardEvent();
	}

	public KeyboardEvent getEvent() {
		return event;
	}
	
}
