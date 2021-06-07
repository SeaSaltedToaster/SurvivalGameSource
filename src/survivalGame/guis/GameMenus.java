package survivalGame.guis;

import survivalGame.guis.mainMenu.MainMenu;
import survivalGame.guis.mainMenu.worldSelection.WorldSelectionMenu;
import survivalGame.guis.pauseMenu.PauseMenu;
import survivalGame.guis.settings.SettingsMenu;

public class GameMenus {

	//Boolean to keep track
	private static boolean isInMenu = false;
	
	//Menu Instance
	private static PauseMenu pauseMenu;
	private static SettingsMenu settingsMenu; 
	private static MainMenu mainMenu;
	private static WorldSelectionMenu worldSelectionMenu;
	
	public static void init() {
		mainMenu = new MainMenu();
		pauseMenu = new PauseMenu();
		settingsMenu = new SettingsMenu();
		worldSelectionMenu = new WorldSelectionMenu();
	}

	public static PauseMenu getPauseMenu() {
		return pauseMenu;
	}

	public static SettingsMenu getSettingsMenu() {
		return settingsMenu;
	}

	public static MainMenu getMainMenu() {
		return mainMenu;
	}
	
	public static WorldSelectionMenu getWorldSelectionMenu() {
		return worldSelectionMenu;
	}

	public static boolean isInMenu() {
		return isInMenu;
	}

	public static void setInMenu(boolean isInMenu) {
		GameMenus.isInMenu = isInMenu;
	}

}
