package survivalGame.world.terrain.renderer;

import java.util.ArrayList;
import java.util.List;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.TerrainGenerator;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.TerrainTransform;

public class ChunkManager {
	
	private List<TerrainChunk> worldTerrainList;
	private ChunkRenderer renderer;
	
	public ChunkManager() {
		this.worldTerrainList = new ArrayList<TerrainChunk>();
		this.renderer = new ChunkRenderer();
	}
	
	public void renderChunks() {
		for(TerrainChunk terrain : worldTerrainList) {
			terrain.update();
			renderer.renderEntities(terrain);
		}
	}
	
	public TerrainChunk getTerrainInPosition(Vector3f position) {
		int indexX = (int) (position.getX() / TerrainGenerator.TERRAIN_SIZE);
		int indexY = (int) (position.getZ() / TerrainGenerator.TERRAIN_SIZE);
		
		for(TerrainChunk chunk : worldTerrainList) {
			TerrainTransform transform = chunk.getTransform();
			if(transform.getIndexX() == indexX && transform.getIndexY() == indexY) {
				return chunk;
			}
		}
		return null;
	}
	
	public boolean containsChunk(TerrainTransform position) {
		for(TerrainChunk chunk : worldTerrainList) {
			if(chunk.getTransform().equals(position))
				return true;
		}
		return false;
	}

	public List<TerrainChunk> getWorldTerrainList() {
		return worldTerrainList;
	}

	public ChunkRenderer getRenderer() {
		return renderer;
	}

}
