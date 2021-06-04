package seaSaltedEngine.basic.statistics;

import java.util.ArrayList;
import java.util.List;

public class Debugger {

	private static List<String> drawCallCount;
	
	public static void init() {
		drawCallCount = new ArrayList<String>();
	}
	
	public static void report(String report) {
		drawCallCount.add(report);
	}
	
	public static void clear() {
		drawCallCount.clear();
	}
	
}
