package survivalGame.world.terrain.generator.load;

import seaSaltedEngine.render.resourceManagement.GlRequest;
import seaSaltedEngine.render.resourceManagement.main.MainRequestProcessor;
import survivalGame.world.terrain.TerrainChunk;

public class TerrainLoadRequest extends GlRequest {

	protected TerrainChunk chunk;
	protected boolean newChunk;
	
	public TerrainLoadRequest(TerrainChunk chunk, boolean newChunk) {
		this.chunk = chunk;
		this.newChunk = newChunk;
	}
	
	@Override
	public void execute() {
		//Generate Mesh Data
		chunk.generate(newChunk);
		
		//Send Mesh Load Request
		TerrainMeshLoadRequest request = new TerrainMeshLoadRequest(chunk, newChunk);
		MainRequestProcessor.sendRequest(request);
	}

	public TerrainChunk getChunk() {
		return chunk;
	}

	public void setChunk(TerrainChunk chunk) {
		this.chunk = chunk;
	}

}
