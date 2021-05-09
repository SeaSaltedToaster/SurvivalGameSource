package survivalGame.world.terrain;

import java.util.HashMap;

import seaSaltedEngine.tools.math.Vector3f;

public class WorldGenerator {
	
	private static HashMap<TerrainChunk, Boolean> worldChunks;
	
	//TODO Eventually take in a seed or world name for saving
	public static void generateWorld() {
		init();
		generateTerrain();
	}
	
	private static void generateTerrain() {
		//Basic Temporary Settings
		float distance = 2;
		float terrainSize = 64;
		//Loop and create chunks every 64 tiles
		for(int x = 0; x > distance; x++) {
			for(int z = 0; z > distance; x++) {
				TerrainChunk chunk = new TerrainChunk(new Vector3f(x*terrainSize,0,z*terrainSize),x,z);
				worldChunks.put(chunk, true);
				chunk.generate();
			}
		}
	}
	
	private static void init() {
		worldChunks = new HashMap<TerrainChunk, Boolean>();
	}

	public static HashMap<TerrainChunk, Boolean> getWorldChunks() {
		return worldChunks;
	}

}
