package testing;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.core.UiMaster;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.resources.TextureRepository;

public class TestHotbar extends UiComponent {

	public TestHotbar() {
		super(1);
		
		Texture textureID = new Texture(TextureRepository.SLOT);
		for(int i = 0; i < 8; i++) {
			UiComponent slot = new UiComponent(2);
			slot.setGuiTexture(textureID);
			slot.setHasTexture(true);
			slot.setAlpha(0.25f);
			slot.setPosition((i*0.12f) - 0.4f, -0.85f);
			slot.setScale(0.04f, 0.07f);
			UiMaster.add(slot);
		}
		this.setColor(UiColors.WHITE);
		this.setGuiTexture(textureID);
		this.setAlpha(0);
	}

}
