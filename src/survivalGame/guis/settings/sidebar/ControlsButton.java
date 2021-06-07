package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.guis.settings.tabs.ControlsTab;
import survivalGame.resources.TextureRepository;

public class ControlsButton extends SidebarButton {

	//Tab Instance
	private static ControlsTab controlsTab;
		
	public ControlsButton() {
		super(controlsTab = new ControlsTab());
			
		//Do audio buttons settings
		this.setPosition(-0.7f, 0.4f);
		this.setScale(this.getScale().divide(1.5f));
		this.setColor(UiColors.WHITE);
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.CONTROL));
	}

	public static ControlsTab getControlsTab() {
		return controlsTab;
	}

}
