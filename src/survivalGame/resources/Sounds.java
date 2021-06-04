package survivalGame.resources;

import seaSaltedEngine.audio.management.AudioMaster;
import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import survivalGame.resources.resourceManagment.AudioBackground;

public class Sounds {

	public static int BOUNCE = AudioMaster.loadSound("click");
	
	public static void init() {
		AudioBackground task = new AudioBackground();
		GlRequestProcessor.sendRequest(task);
	}
	
	public static void loadAll() {
		BOUNCE = AudioMaster.loadSound("click");
	}

}
