package survivalGame.world;

import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.loading.TerrainGenerationRequest;
import survivalGame.world.terrain.renderer.ChunkManager;

public class TerrainGenerator {

	private static ChunkManager manager = new ChunkManager();
	
	public static final int TERRAIN_SIZE = 256;
	private static final int renderDistance = 1;
	private static final int viewDistance = renderDistance * TERRAIN_SIZE;
	
	public static void generateTerrain(Vector3f playerPosition) {
		//Loop through area new player
		for(float x = (float) playerPosition.x; x < playerPosition.x+viewDistance; x+=(TERRAIN_SIZE/2)-0.5f) {
			for(float y = (float) playerPosition.y; y < playerPosition.z+viewDistance; y+=(TERRAIN_SIZE/2)-0.5f) {
				//Add new chunk at position (x,y)
				createNewChunk(x,y);
			}
		}
	}
	
	private static void createNewChunk(float x, float z) {
		//Create Instance
		TerrainChunk chunk = new TerrainChunk(new Vector3f(x,0,z));
		
		//Check if it exists
		if(!manager.containsChunk(chunk.getTransform()))
			manager.getWorldTerrainList().add(chunk);
		else
			return;
		
		//Load variables
		chunk.start();
		
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