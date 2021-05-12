package survivalGame.world.terrain.generator.load;

import seaSaltedEngine.render.resourceManagement.GlRequest;
import survivalGame.world.terrain.TerrainChunk;

public class TerrainMeshLoadRequest extends GlRequest {
	
	protected TerrainChunk chunk;
	
	public TerrainMeshLoadRequest(TerrainChunk chunk) {
		this.chunk = chunk;
	}
	
	@Override
	public void execute() {
		chunk.generateMesh();
	}

	public TerrainChunk getChunk() {
		return chunk;
	}

	public void setChunk(TerrainChunk chunk) {
		this.chunk = chunk;
	}

}
