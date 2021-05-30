package survivalGame.resources;

import seaSaltedEngine.audio.management.AudioMaster;

public class Sounds {

	public static int BOUNCE;
	
	public Sounds() {
		BOUNCE = AudioMaster.loadSound("click");
	}

}
