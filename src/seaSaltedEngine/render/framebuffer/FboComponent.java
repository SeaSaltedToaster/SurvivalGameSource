package seaSaltedEngine.render.framebuffer;

public abstract class FboComponent {

	private int bufferId; 
	private boolean isDepthAttach = false;
	  
	public int getBufferId() {
		return this.bufferId;
	}
	  
	public abstract void init(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
	  
	public abstract void delete();
	  
	protected void setBufferId(int id) {
		this.bufferId = id;
	}
	  
	protected void setAsDepthAttachment() {
		this.isDepthAttach = true;
	}
	  
	protected boolean isDepthAttachment() {
		return this.isDepthAttach;
	}

}
