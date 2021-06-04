package survivalGame.guis;

import survivalGame.guis.pauseMenu.PauseMenu;
import survivalGame.guis.settings.SettingsMenu;

public class GameMenus {

	//Boolean to keep track
	private static boolean isInMenu = false;
	
	//Menu Instance
	private static PauseMenu pauseMenu;
	private static SettingsMenu settingsMenu; 
	
	public static void init() {
		pauseMenu = new PauseMenu();
		settingsMenu = new SettingsMenu();
	}

	public static PauseMenu getPauseMenu() {
		return pauseMenu;
	}

	public static SettingsMenu getSettingsMenu() {
		return settingsMenu;
	}

	public static boolean isInMenu() {
		return isInMenu;
	}

	public static void setInMenu(boolean isInMenu) {
		GameMenus.isInMenu = isInMenu;
	}

}
