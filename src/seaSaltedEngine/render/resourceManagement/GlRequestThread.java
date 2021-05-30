package seaSaltedEngine.render.resourceManagement;

public class GlRequestThread extends Thread {

	private static boolean running = true;
	
	public void run() {
		this.setName("Background Loading thread");
		while(running) {
			GlRequestProcessor.dealWithTopRequests();
		}
	}
	
	public static void stopThread() {
		GlRequestThread.running = false;
	}

	public static boolean isRunning() {
		return running;
	}

	public static void setRunning(boolean running) {
		GlRequestThread.running = running;
	}
	
}
