package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.guis.settings.tabs.ModTab;
import survivalGame.resources.TextureRepository;

public class ModButton extends SidebarButton {

	private static ModTab modTab;
	
	public ModButton() {
		super(modTab = new ModTab());

		//Do audio buttons settings
		this.setPosition(-0.7f, -0.4f);
		this.setActive(false);
		this.setScale(this.getScale().divide(1.5f));
		this.setColor(UiColors.WHITE);		
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.FOLDER));
	}

	public static ModTab getModTab() {
		return modTab;
	}

}
