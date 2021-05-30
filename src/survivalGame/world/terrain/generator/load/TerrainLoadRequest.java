package survivalGame.world.terrain.generator.load;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.basic.logger.Logger;
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
		//Get start time
		double startTime = GLFW.glfwGetTime();
		
		//Generate Mesh Data
		chunk.generate(newChunk);
		
		//Log time to generate
		Logger.Log("Chunk load time: "+ (GLFW.glfwGetTime() - startTime) ); 
		
		//Send Mesh Load Request
		TerrainMeshLoadRequest request = new TerrainMeshLoadRequest(chunk);
		MainRequestProcessor.sendRequest(request);
	}

	public TerrainChunk getChunk() {
		return chunk;
	}

	public void setChunk(TerrainChunk chunk) {
		this.chunk = chunk;
	}

}
