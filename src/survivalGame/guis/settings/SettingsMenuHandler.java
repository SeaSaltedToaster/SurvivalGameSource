package survivalGame.guis.settings;

import survivalGame.guis.GameMenus;

public class SettingsMenuHandler {

	public void handleReturn(String from) {
		switch(from) {
			case "PauseMenu":
				GameMenus.getPauseMenu().setActive(true); 
				break;
			case "MainMenu":
				GameMenus.getMainMenu().setActive(true); 
				break;
		}
	}

}
