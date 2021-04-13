package survivalGame.world.terrain;

import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.MeshData;
import seaSaltedEngine.tools.MeshBuilder;
import seaSaltedEngine.tools.Triangulator;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.resources.Models;
import survivalGame.world.dualContouring.DualContouring;
import survivalGame.world.dualContouring.OctreeBuilder;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.terrain.mesh.TerrainMeshData;

public class TerrainChunk {

	private TerrainTransform transform;
	private int chunkSize = 32;
	
	private Mesh terrainMesh;
	private TerrainMeshData terrainData;
	
	private OctreeNode voxelOctree;
	private boolean meshDataReady;
	
	public TerrainChunk(Vector3f position) {
		this.transform = new TerrainTransform(position,(int) position.x/chunkSize,(int) position.z/chunkSize);
		this.terrainMesh = Models.getModelFromID(1);
		this.terrainData = new TerrainMeshData();
	}
	
	public void generate() {
		this.voxelOctree = OctreeBuilder.BuildOctree(transform.getPosition(), chunkSize, null);
		DualContouring.GenerateMeshFromOctree(getVoxelOctree(), terrainData.getVertices(), terrainData.getIndices());
	}
	
	public void update() {
		checkMesh();
	}
	
	private void checkMesh() {
		if(meshDataReady) {
			terrainMesh = new Mesh(new MeshData(MeshBuilder.createModel(terrainData.getVertices(), Triangulator.triangulateIndices(terrainData.getIndices()))));
		}
	}

	public Mesh getTerrainMesh() {
		return terrainMesh;
	}

	public TerrainTransform getTransform() {
		return transform;
	}

	public OctreeNode getVoxelOctree() {
		return voxelOctree;
	}

	public int getChunkSize() {
		return chunkSize;
	}

	public TerrainMeshData getTerrainData() {
		return terrainData;
	}

	public boolean isMeshDataReady() {
		return meshDataReady;
	}

	public void setTransform(TerrainTransform transform) {
		this.transform = transform;
	}

	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}

	public void setTerrainMesh(Mesh terrainMesh) {
		this.terrainMesh = terrainMesh;
	}

	public void setTerrainData(TerrainMeshData terrainData) {
		this.terrainData = terrainData;
	}

	public void setVoxelOctree(OctreeNode voxelOctree) {
		this.voxelOctree = voxelOctree;
	}

	public void setMeshDataReady(boolean meshDataReady) {
		this.meshDataReady = meshDataReady;
	}
	
}
