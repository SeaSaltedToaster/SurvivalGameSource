package seaSaltedEngine.render.resourceManagement;

import java.util.ArrayList;
import java.util.List;

public class GlRequestQueue {

	private List<GlRequest> requestQueue = new ArrayList<>();
	
	public synchronized void addRequest(GlRequest request) {
		this.requestQueue.add(request);
	}
	
	public synchronized GlRequest acceptNextRequest() {
		if(!GlRequestThread.isRunning()) return null;
		return this.requestQueue.remove(0);
	}

	public synchronized boolean hasRequests() {
		return !this.requestQueue.isEmpty();
	}
	
}
