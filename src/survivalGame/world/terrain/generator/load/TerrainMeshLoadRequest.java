package survivalGame.world.terrain.generator.load;

import seaSaltedEngine.render.resourceManagement.GlRequest;
import survivalGame.world.terrain.TerrainChunk;

public class TerrainMeshLoadRequest extends GlRequest {
	
	protected TerrainChunk chunk;
	protected boolean isNewChunk;
	
	public TerrainMeshLoadRequest(TerrainChunk chunk, boolean isNewChunk) {
		this.chunk = chunk;
	}
	
	@Override
	public void execute() {
		//Generate Mesh Data
		chunk.generateMesh(isNewChunk);
	}

	public TerrainChunk getChunk() {
		return chunk;
	}

	public void setChunk(TerrainChunk chunk) {
		this.chunk = chunk;
	}

}
