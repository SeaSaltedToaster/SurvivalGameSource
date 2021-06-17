package survivalGame.guis;

import survivalGame.guis.chat.UiChat;
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
	
	//Sub menus
	private static WorldSelectionMenu worldSelectionMenu;
	private static UiChat chat;
	
	public static void init() {
		pauseMenu = new PauseMenu();
		mainMenu = new MainMenu();
		settingsMenu = new SettingsMenu();
		
		worldSelectionMenu = new WorldSelectionMenu();
		chat = new UiChat();
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

	public static UiChat getChat() {
		return chat;
	}

	public static boolean isInMenu() {
		return isInMenu;
	}

	public static void setInMenu(boolean isInMenu) {
		GameMenus.isInMenu = isInMenu;
	}

}
