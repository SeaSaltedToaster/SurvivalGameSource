package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import survivalGame.guis.settings.tabs.ControlsTab;

public class ControlsButton extends SidebarButton {

	//Tab Instance
	private static ControlsTab controlsTab;
		
	public ControlsButton() {
		super(controlsTab = new ControlsTab());
			
		//Do audio buttons settings
		this.setPosition(-0.7f, 0.3f);
		this.setColor(UiColors.WHITE);
			
	}

	public static ControlsTab getControlsTab() {
		return controlsTab;
	}

}
