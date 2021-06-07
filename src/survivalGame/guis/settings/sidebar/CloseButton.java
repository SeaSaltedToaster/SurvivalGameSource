package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.resources.TextureRepository;

public class CloseButton extends SidebarButton {

	public CloseButton() {
		super(null);
		this.setExitButton(true);
		
		//Do audio buttons settings
		this.setPosition(-0.7f, -0.6f);
		this.setScale(this.getScale().divide(1.5f));
		this.setColor(UiColors.WHITE);
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.BACK));
	}

}
