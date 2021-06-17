package survivalGame;

import survivalGame.inventory.item.iconRenderer.IconRenderer;
import survivalGame.resources.Models;
import survivalGame.world.GameWorld;
import survivalGame.world.terrain.renderer.TerrainRenderer;

public class GameManager {

	public static void initGame() { //Initialize Game Systems
		Models.loadModelCache();
		IconRenderer.init();
		TerrainRenderer.init();
		GameWorld.initialize();
		
//		try {
//			Process proc = Runtime.getRuntime().exec("java -jar resources/res/server/Survival.jar");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
}