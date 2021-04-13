package survivalGame.world;

import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.loading.TerrainGenerationRequest;
import survivalGame.world.terrain.renderer.ChunkManager;

public class TerrainGenerator {

	private static ChunkManager manager = new ChunkManager();
	
	private static final int size = 32;
	private static final int distance = 16;
	
	public static void generateTerrain() {
		for(int x = -distance; x < distance; x++) {
			for(int y = -distance; y < distance; y++) {
				//Create Instance
				TerrainChunk chunk = new TerrainChunk(new Vector3f(x*size,0,y*size));
				manager.getWorldTerrainList().put(false, chunk);
				
				//Background Loading
				TerrainGenerationRequest request = new TerrainGenerationRequest(chunk);
				GlRequestProcessor.sendRequest(request);
			}
		}
	}

	public static ChunkManager getManager() {
		return manager;
	}

	public static int getSize() {
		return size;
	}

	public static int getDistance() {
		return distance;
	}
	
}
