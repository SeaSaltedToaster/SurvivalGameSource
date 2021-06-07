package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.guis.settings.tabs.GraphicsTab;
import survivalGame.resources.TextureRepository;

public class GraphicsButton extends SidebarButton {

	//Tab Instance
	private static GraphicsTab graphicsTab;
				
	public GraphicsButton() {
		super(graphicsTab = new GraphicsTab());
					
		//Do audio buttons settings
		this.setPosition(-0.7f, 0f);
		this.setScale(this.getScale().divide(1.5f));
		this.setColor(UiColors.WHITE);		
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.GFX));
	}

	public static GraphicsTab getGraphicsTab() {
		return graphicsTab;
	}
		

}
