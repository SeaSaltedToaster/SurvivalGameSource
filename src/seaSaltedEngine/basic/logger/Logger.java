package seaSaltedEngine.basic.logger;

import seaSaltedEngine.basic.time.Time;

public class Logger {

	private static LoggerType type;
	
	public Logger(LoggerType type) {
		Logger.type = type;
	}
	
	public static void Log(String message) {
		if(type == LoggerType.CONSOLE || type == LoggerType.BOTH) 
			System.out.println("["+Time.getRealTime()+"]"+" INFO: "+message);
	}
	
	public static void ServerLog(String message) {
		if(type == LoggerType.CONSOLE || type == LoggerType.BOTH) 
			System.out.println("["+Time.getRealTime()+"]"+" SERVER INFO: "+message);
	}
	
}
