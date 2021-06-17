package seaSaltedEngine.render.resourceManagement.main;

import seaSaltedEngine.render.resourceManagement.GlRequest;

public abstract class MainRequest extends GlRequest {

	public boolean isCompleted;
	public int requestId;
	
	public abstract void execute();
	
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

}
