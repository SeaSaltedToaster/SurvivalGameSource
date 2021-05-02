package survivalGame.world.terrain.loading;

import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.MeshData;
import seaSaltedEngine.render.resourceManagement.main.MainRequest;
import seaSaltedEngine.tools.MeshBuilder;
import survivalGame.world.terrain.TerrainChunk;

public class MeshGenerationRequest extends MainRequest {

	private TerrainChunk chunk;
	
	public MeshGenerationRequest(TerrainChunk chunk) {
		this.chunk = chunk;
	}
	
	@Override
	public void execute() {
		chunk.setTerrainMesh(new Mesh(new MeshData(MeshBuilder.createModel(chunk.getTerrainData().getVertices(), chunk.getTerrainData().getIndices()))));
		chunk.setMeshDataReady(false);
	}

}
