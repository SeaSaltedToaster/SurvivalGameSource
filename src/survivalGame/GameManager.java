package survivalGame;

import seaSaltedEngine.guis.text.TextMaster;
import survivalGame.guis.GameMenus;
import survivalGame.resources.Models;
import survivalGame.resources.keybinding.Controls;
import survivalGame.world.GameWorld;
import survivalGame.world.terrain.renderer.TerrainRenderer;

public class GameManager {

	public static void initGame() { //Initialize Game Systems
		Controls.initControls();
		Models.loadModelCache();
		TerrainRenderer.init();
		GameWorld.initialize();
		TextMaster.init();
		GameMenus.init();
		
//		try {
//			Process proc = Runtime.getRuntime().exec("java -jar resources/res/server/Survival.jar");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
}