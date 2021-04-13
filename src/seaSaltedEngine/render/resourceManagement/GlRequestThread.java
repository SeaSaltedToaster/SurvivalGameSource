package seaSaltedEngine.render.resourceManagement;

public class GlRequestThread extends Thread {

	private boolean running = true;
	
	public void run() {
		while(running) {
			GlRequestProcessor.dealWithTopRequests();
		}
	}
	
	public void stopThread() {
		this.running = false;
	}
	
}
