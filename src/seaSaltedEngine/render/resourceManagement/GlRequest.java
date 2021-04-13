package seaSaltedEngine.render.resourceManagement;

public abstract class GlRequest {

	public boolean isCompleted;
	
	public abstract void execute();
	
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
}
