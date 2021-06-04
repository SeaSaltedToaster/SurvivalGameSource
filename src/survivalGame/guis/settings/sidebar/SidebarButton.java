package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiComponent;
import survivalGame.guis.GameMenus;

public class SidebarButton extends UiComponent {

	//Ui the button will open
	private UiComponent open;
	private boolean exitButton;
	
	public SidebarButton(UiComponent open) {
		super(3);
		doButtonSettings(open);
	}
	
	private void doButtonSettings(UiComponent open) {
		this.open = open;
		this.setScale(0.06f, 0.1f);
	}
	
	@Override
	public void onClick() {
		GameMenus.getSettingsMenu().closeAllTabs();
		if(!exitButton)
			this.open.setActive(true);
		else
			GameMenus.getSettingsMenu().close();
	}

	public UiComponent getOpen() {
		return open;
	}

	public void setExitButton(boolean exitButton) {
		this.exitButton = exitButton;
	}

}
