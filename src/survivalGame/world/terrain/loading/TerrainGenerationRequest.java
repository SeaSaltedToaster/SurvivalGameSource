package survivalGame.world.terrain.loading;

import seaSaltedEngine.render.resourceManagement.GlRequest;
import survivalGame.world.terrain.TerrainChunk;

public class TerrainGenerationRequest extends GlRequest {

	private TerrainChunk chunk;
	
	public TerrainGenerationRequest(TerrainChunk chunk) {
		this.chunk = chunk;
	}
	
	@Override
	public void execute() {
		chunk.generate();
		chunk.setMeshDataReady(true);
	}

}
