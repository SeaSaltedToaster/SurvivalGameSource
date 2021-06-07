package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.guis.settings.tabs.DisplayTab;
import survivalGame.resources.TextureRepository;

public class DisplayButton extends SidebarButton {

	//Tab Instance
	private static DisplayTab displayTab;
			
	public DisplayButton() {
		super(displayTab = new DisplayTab());
				
		//Do audio buttons settings
		this.setPosition(-0.7f, 0.2f);
		this.setScale(this.getScale().divide(1.5f));
		this.setColor(UiColors.WHITE);	
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.DISPLAY));
	}

	public static DisplayTab getDisplayTab() {
		return displayTab;
	}
	
}
