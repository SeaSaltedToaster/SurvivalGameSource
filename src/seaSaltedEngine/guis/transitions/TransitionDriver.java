package seaSaltedEngine.guis.transitions;

public abstract class TransitionDriver {

	private float currentTime = 0.0F;
	private float length;
	private boolean oneRep = false;
	   
	public TransitionDriver(float length) {
		this.length = length;
	}
	  
	public float update(float delta) {
		 this.currentTime += delta;
		 if (this.currentTime >= this.length) {
			this.currentTime %= this.length;
			this.oneRep = true;
		 } 
		float time = this.currentTime / this.length;
		return calculateValue(time);
	}
	
	public float reverse(float delta) {
		 this.currentTime -= delta;
		 if (this.currentTime >= this.length) {
			this.currentTime %= this.length;
		this.oneRep = true;
		} 
		float time = this.currentTime / this.length;
		return calculateValue(time);
	}
 
	protected abstract float calculateValue(float paramFloat);
	 
	public boolean hasCompletedOnePeriod() {
		return this.oneRep;
	}
	
}

