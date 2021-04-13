package seaSaltedEngine.guis;

public interface Listener {

	abstract void notify(String update);
	
	void cancel();
	
}
