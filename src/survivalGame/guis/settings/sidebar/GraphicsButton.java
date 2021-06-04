package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import survivalGame.guis.settings.tabs.GraphicsTab;

public class GraphicsButton extends SidebarButton {

	//Tab Instance
	private static GraphicsTab graphicsTab;
				
	public GraphicsButton() {
		super(graphicsTab = new GraphicsTab());
					
		//Do audio buttons settings
		this.setPosition(-0.7f, -0.2f);
		this.setColor(UiColors.WHITE);		
	}

	public static GraphicsTab getGraphicsTab() {
		return graphicsTab;
	}
		

}
