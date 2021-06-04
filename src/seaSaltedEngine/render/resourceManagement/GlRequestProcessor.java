package seaSaltedEngine.render.resourceManagement;

public class GlRequestProcessor {

	private static GlRequestQueue requestQueue = new GlRequestQueue();
	private static GlRequestThread thread = new  GlRequestThread();
	
	public static void init() {
		thread.start();
	}
	
	public static void end() {
		GlRequestThread.stopThread();
	}
	
	public static void sendRequest(GlRequest request) {
		requestQueue.addRequest(request);
	}
	 
	public static void dealWithTopRequests() {
		float remainingTime = 800.0F;
		long start = System.nanoTime();
		while (requestQueue.hasRequests() && GlRequestThread.isRunning()) {
		     requestQueue.acceptNextRequest().execute();
		     long end = System.nanoTime();
		     long timeTaken = end - start;
		     remainingTime -= (float)timeTaken;
		     start = end;
		     if (remainingTime < 0.0F) {
				 break;
		     }
		} 
	}

	public static void completeAllRequests() {
		while (requestQueue.hasRequests())
			requestQueue.acceptNextRequest().execute();
	}
	
}
