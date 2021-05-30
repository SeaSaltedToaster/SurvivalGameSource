package survivalGame.world.terrain.generator.load;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.render.resourceManagement.GlRequest;
import survivalGame.world.terrain.TerrainChunk;

public class TerrainMeshLoadRequest extends GlRequest {
	
	protected TerrainChunk chunk;
	
	public TerrainMeshLoadRequest(TerrainChunk chunk) {
		this.chunk = chunk;
	}
	
	@Override
	public void execute() {
		//Get start time
		double startTime = GLFW.glfwGetTime();
				
		//Generate Mesh Data
		chunk.generateMesh();
		
		//Log time to generate
		Logger.Log("Chunk mesh load time: "+ (GLFW.glfwGetTime() - startTime) ); 
	}

	public TerrainChunk getChunk() {
		return chunk;
	}

	public void setChunk(TerrainChunk chunk) {
		this.chunk = chunk;
	}

}
