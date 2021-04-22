package seaSaltedEngine.render.resourceManagement.main;

import seaSaltedEngine.render.resourceManagement.GlRequest;
import seaSaltedEngine.render.resourceManagement.GlRequestQueue;

public class MainRequestProcessor {
	
	private static GlRequestQueue requestQueue = new GlRequestQueue();
	
	public static void sendRequest(GlRequest request) {
		requestQueue.addRequest(request);
	}
	 
	public static void dealWithTopRequests() {
		while (requestQueue.hasRequests()) {
		     requestQueue.acceptNextRequest().execute();
		     break;
		} 
	}

	public static void completeAllRequests() {
		while (requestQueue.hasRequests())
			requestQueue.acceptNextRequest().execute();
	}

}
