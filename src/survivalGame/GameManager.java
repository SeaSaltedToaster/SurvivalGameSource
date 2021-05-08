package survivalGame;

import seaSaltedEngine.Engine;
import survivalGame.resources.Models;
import survivalGame.world.GameWorld;
import survivalGame.world.TerrainGenerator;

public class GameManager {

	public static void initGame() { //Initialize Game Systems
		Models.loadModelCache();
		GameWorld.initialize();
		TerrainGenerator.generateTerrain(Engine.getCamera().getPosition());
	}
	
}