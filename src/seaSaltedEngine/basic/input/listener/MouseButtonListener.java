package seaSaltedEngine.basic.input.listener;

import seaSaltedEngine.guis.Listener;

public class MouseButtonListener implements Listener {

	private boolean registerInputs = true;
	
	@Override
	public void notify(String update) {
		// TODO Parse Input to Mouse Button Object
	}

	@Override
	public void cancel() {
		setRegisterInputs(false);
	}

	public boolean registerInputs() {
		return registerInputs;
	}

	public void setRegisterInputs(boolean registerInputs) {
		this.registerInputs = registerInputs;
	}

}
