package survivalGame;

import survivalGame.resources.Models;
import survivalGame.world.GameWorld;
import survivalGame.world.terrain.render.TerrainRenderer;

public class GameManager {

	public static void initGame() { //Initialize Game Systems
		Models.loadModelCache();
		TerrainRenderer.init();
		GameWorld.initialize();
//		TerrainGenerator.generateTerrain(Engine.getCamera().getPosition());
	}
	
}