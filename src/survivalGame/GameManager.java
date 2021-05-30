package survivalGame;

import seaSaltedEngine.guis.text.TextMaster;
import survivalGame.guis.pauseMenu.PauseMenu;
import survivalGame.resources.Models;
import survivalGame.world.GameWorld;
import survivalGame.world.terrain.renderer.TerrainRenderer;

public class GameManager {

	public static void initGame() { //Initialize Game Systems
		Models.loadModelCache();
		TerrainRenderer.init();
		GameWorld.initialize();
		TextMaster.init();
		
		PauseMenu pause = new PauseMenu();
		pause.updateSelf();
		
//		try {
//			Process proc = Runtime.getRuntime().exec("java -jar resources/res/server/Survival.jar");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
}