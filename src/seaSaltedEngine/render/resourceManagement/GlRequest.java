package seaSaltedEngine.render.resourceManagement;

public abstract class GlRequest {

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
