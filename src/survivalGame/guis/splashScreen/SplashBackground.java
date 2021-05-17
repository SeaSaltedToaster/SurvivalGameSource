package survivalGame.guis.splashScreen;

import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.render.model.texture.Texture;
import seaSaltedEngine.render.model.texture.TextureLoader;

public class SplashBackground extends UiComponent {

	public SplashBackground() {
		super(0);
		
		Texture textureID = new Texture(TextureLoader.loadTexture("BOBO"));
		this.setGuiTexture(textureID);
		this.setHasTexture(true);
		this.setScale(1, 1);
	}

}
