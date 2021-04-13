package seaSaltedEngine;

import seaSaltedEngine.basic.logger.LoggerType;
import seaSaltedEngine.basic.objects.Color;

public class EngineConfigs {

	public String windowName;
	public int fpsCap;
	
	public Color defaultColor;
	public LoggerType loggerType;
	
	public EngineConfigs() {
		windowName = "SeaSaltedEngine Default Name";
		fpsCap = 60;
		loggerType = LoggerType.BOTH;
		defaultColor = new Color(0,0,0);
	}

	public String getWindowName() {
		return windowName;
	}

	public int getFpsCap() {
		return fpsCap;
	}

	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	public void setFpsCap(int fpsCap) {
		this.fpsCap = fpsCap;
	}

	public Color getDefaultColor() {
		return defaultColor;
	}

	public LoggerType getLoggerType() {
		return loggerType;
	}
	
}
