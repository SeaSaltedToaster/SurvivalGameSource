package seaSaltedEngine.render.resourceManagement.main;

import seaSaltedEngine.render.resourceManagement.GlRequest;

public abstract class MainRequest extends GlRequest {

	public boolean isCompleted;
	
	public abstract void execute();
	
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
