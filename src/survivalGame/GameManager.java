package survivalGame;

import survivalGame.resources.Models;
import survivalGame.world.GameWorld;
import survivalGame.world.terrain.renderer.TerrainRenderer;

public class GameManager {

	public static void initGame() { //Initialize Game Systems
		Models.loadModelCache();
		TerrainRenderer.init();
		GameWorld.initialize();
		
//		try {
//			Process proc = Runtime.getRuntime().exec("java -jar resources/res/server/Survival.jar");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
}