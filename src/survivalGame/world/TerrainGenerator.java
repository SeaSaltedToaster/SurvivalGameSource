package survivalGame.world;

import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.loading.TerrainGenerationRequest;
import survivalGame.world.terrain.renderer.ChunkManager;

public class TerrainGenerator {

	private static ChunkManager manager = new ChunkManager();
	
	public static final int size = 64;
	private static final int renderDistance = 2;
	private static final int distance = renderDistance * size;
	
	public static void generateTerrain() {
		for(int x = -distance; x < distance; x+=size/2) {
			for(int y = -distance; y < distance; y+=size/2) {
				//Create Instance
				TerrainChunk chunk = new TerrainChunk(new Vector3f(x,0,y));
				
				//Check if it exists
				if(!manager.containsChunk(chunk.getTransform()))
					manager.getWorldTerrainList().add(chunk);
				
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
