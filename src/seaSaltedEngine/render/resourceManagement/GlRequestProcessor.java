package seaSaltedEngine.render.resourceManagement;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.system.MemoryUtil;

public class GlRequestProcessor {

	private static GlRequestQueue requestQueue = new GlRequestQueue();
	
	private static List<GlRequestThread> threads = new ArrayList<GlRequestThread>();
	
	public static void init() {
		for(int i = 0; i < 1; i++) {
			GlRequestThread thread = new GlRequestThread();
			thread.setName("Background Loading Thread " + i);
			thread.start(); threads.add(thread);
		}
	}
	
	public static void end() {
		GlRequestThread.stopThread();
	}
	
	public static void sendRequest(GlRequest request) {
		if(request.getRequestId() == MemoryUtil.NULL) request.setRequestId(-1);
		requestQueue.addRequest(request);
	}
	 
	public static void dealWithTopRequests() {
		float remainingTime = 8000.0F;
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
	
	public static void completeTopRequest() {
		if(requestQueue.hasRequests())
			requestQueue.acceptNextRequest().execute();
	}

	public static void completeAllRequests() {
		while (requestQueue.hasRequests())
			requestQueue.acceptNextRequest().execute();
	}
	
}
