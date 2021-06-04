package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;

public class CloseButton extends SidebarButton {

	public CloseButton() {
		super(null);
		this.setExitButton(true);
		
		//Do audio buttons settings
		this.setPosition(-0.7f, -0.55f);
		this.setColor(UiColors.WHITE);
	}

}
