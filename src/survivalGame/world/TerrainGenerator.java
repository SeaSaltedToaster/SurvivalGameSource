package survivalGame.world;

import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.loading.TerrainGenerationRequest;
import survivalGame.world.terrain.renderer.ChunkManager;

public class TerrainGenerator {

	private static ChunkManager manager = new ChunkManager();
	
	public static final int TERRAIN_SIZE = 64;
	private static final int renderDistance = 2;
	private static final int viewDistance = renderDistance * TERRAIN_SIZE;
	
	public static void generateTerrain() {
		createNewChunk(0,0);
//		for(int x = -viewDistance; x < viewDistance; x+=TERRAIN_SIZE/2) {
//			for(int y = -viewDistance; y < viewDistance; y+=TERRAIN_SIZE/2) {
//				//Add new chunk at position (x,y)
//				createNewChunk(x,y);
//			}
//		}
	}
	
	private static void createNewChunk(int x, int z) {
		//Create Instance
		TerrainChunk chunk = new TerrainChunk(new Vector3f(x,0,z));
		
		//Check if it exists
		if(!manager.containsChunk(chunk.getTransform()))
			manager.getWorldTerrainList().add(chunk);
		
		//Background Loading
		TerrainGenerationRequest request = new TerrainGenerationRequest(chunk);
		GlRequestProcessor.sendRequest(request);
	}

	public static ChunkManager getManager() {
		return manager;
	}

	public static int getSize() {
		return TERRAIN_SIZE;
	}

	public static int getDistance() {
		return viewDistance;
	}
	
}
