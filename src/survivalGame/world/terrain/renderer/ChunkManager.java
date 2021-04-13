package survivalGame.world.terrain.renderer;

import java.util.HashMap;
import java.util.Map.Entry;

import seaSaltedEngine.basic.logger.Logger;
import survivalGame.world.terrain.TerrainChunk;

public class ChunkManager {
	
	private HashMap<Boolean, TerrainChunk> worldTerrainList;
	private ChunkRenderer renderer;
	
	public ChunkManager() {
		this.worldTerrainList = new HashMap<Boolean, TerrainChunk>();
		this.renderer = new ChunkRenderer();
	}
	
	public void renderChunks() {
		for(Entry<Boolean, TerrainChunk> ei : worldTerrainList.entrySet()) {
			ei.getValue().update();
			renderer.renderEntities(ei.getValue());
		}
	}
	
	public void setLoadState(TerrainChunk chunk, boolean isLoaded) {
		for(Entry<Boolean, TerrainChunk> ei : worldTerrainList.entrySet()) {
			if(ei.getValue().getTransform().equals(chunk.getTransform())) {
				
			}
		}
	}

	public HashMap<Boolean, TerrainChunk> getWorldTerrainList() {
		return worldTerrainList;
	}

	public ChunkRenderer getRenderer() {
		return renderer;
	}

}
