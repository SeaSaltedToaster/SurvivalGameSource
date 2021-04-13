package survivalGame;

import survivalGame.resources.Models;
import survivalGame.world.GameWorld;
import survivalGame.world.TerrainGenerator;

public class GameManager {

	public static void initGame() { //Initialize Game Systems
		GameWorld.initialize();
		Models.loadModelCache();
		TerrainGenerator.generateTerrain();
	}
	
}