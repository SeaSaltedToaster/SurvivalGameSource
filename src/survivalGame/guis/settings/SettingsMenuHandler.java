package survivalGame.guis.settings;

import survivalGame.guis.GameMenus;

public class SettingsMenuHandler {

	public void handleReturn(String from) {
		switch(from) {
			case "PauseMenu":
				GameMenus.getPauseMenu().setActive(true);
			case "MainMenu":
				//TODO Open Main Menu
			break;
		}
	}

}
