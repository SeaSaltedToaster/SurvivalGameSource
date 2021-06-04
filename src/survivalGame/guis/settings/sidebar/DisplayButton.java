package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import survivalGame.guis.settings.tabs.DisplayTab;

public class DisplayButton extends SidebarButton {

	//Tab Instance
	private static DisplayTab displayTab;
			
	public DisplayButton() {
		super(displayTab = new DisplayTab());
				
		//Do audio buttons settings
		this.setPosition(-0.7f, 0.05f);
		this.setColor(UiColors.WHITE);		
	}

	public static DisplayTab getDisplayTab() {
		return displayTab;
	}
	
}
