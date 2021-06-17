package seaSaltedEngine.render.resourceManagement.main;

import org.lwjgl.system.MemoryUtil;

import seaSaltedEngine.render.resourceManagement.GlRequest;

public class MainRequestProcessor {
	
	private static MainRequestQueue requestQueue = new MainRequestQueue();
	
	public static void sendRequest(GlRequest request) {
		if(request.getRequestId() == MemoryUtil.NULL) request.setRequestId(-1);
		requestQueue.addRequest(request);
	}
	 
	public static void dealWithTopRequests() {
		if(requestQueue.hasRequests())
			requestQueue.acceptNextRequest().execute();
	}

	public static void completeAllRequests() {
		while (requestQueue.hasRequests())
			requestQueue.acceptNextRequest().execute();
	}

}
