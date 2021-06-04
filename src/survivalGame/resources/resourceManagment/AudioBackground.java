package survivalGame.resources.resourceManagment;

import seaSaltedEngine.render.resourceManagement.GlRequest;
import survivalGame.resources.Sounds;

public class AudioBackground extends GlRequest {

	public AudioBackground() {
		
	}

	@Override
	public void execute() {
		Sounds.loadAll();
	}

}
