package seaSaltedEngine.render.model;

public class Mesh {

	private MeshData meshData;
	
	public Mesh(MeshData meshData) {
		this.meshData = meshData;
	}

	public MeshData getMeshData() {
		return meshData;
	}

	public void setMeshData(MeshData meshData) {
		this.meshData = meshData;
	}
	
	public Vao getMeshVao() {
		return meshData.getMeshVao();
	}
	
}
