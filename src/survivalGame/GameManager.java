package survivalGame;

import survivalGame.resources.Models;
import survivalGame.world.GameWorld;

public class GameManager {

	public static void initGame() { //Initialize Game Systems
		Models.loadModelCache();
		GameWorld.initialize();
//		TerrainGenerator.generateTerrain(Engine.getCamera().getPosition());
	}
	
}