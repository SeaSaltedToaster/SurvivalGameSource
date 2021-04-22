package survivalGame.world.terrain;

import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.resourceManagement.main.MainRequestProcessor;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.resources.Models;
import survivalGame.world.TerrainGenerator;
import survivalGame.world.dualContouring.DualContouring;
import survivalGame.world.dualContouring.OctreeBuilder;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.terrain.loading.MeshGenerationRequest;
import survivalGame.world.terrain.mesh.TerrainMeshData;
import survivalGame.world.terrain.objects.TerrainObjectManager;

public class TerrainChunk {

	private TerrainTransform transform;
	private int chunkSize;
	
	private Mesh terrainMesh;
	private TerrainMeshData terrainData;
	private TerrainObjectManager manager;
	
	private OctreeNode voxelOctree;
	private boolean meshDataReady;
	
	public TerrainChunk(Vector3f position) {
		this.chunkSize = TerrainGenerator.size;
		this.transform = new TerrainTransform(position,(int) position.x/chunkSize,(int) position.z/chunkSize);
		this.terrainMesh = Models.getModelFromID(1);
		this.terrainData = new TerrainMeshData();
		this.manager = new TerrainObjectManager();
	}
	
	public void generate() {
		this.voxelOctree = OctreeBuilder.BuildOctree(transform.getPosition(), chunkSize, null);
		DualContouring.GenerateMeshFromOctree(getVoxelOctree(), terrainData.getVertices(), terrainData.getIndices(), this);
		
	}
	
	public void update() {
		checkMesh();
	}
	
	private void checkMesh() {
		if(meshDataReady) {
//			MeshGenerationRequest request = new MeshGenerationRequest(this);
//			MainRequestProcessor.sendRequest(request);
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

	public TerrainObjectManager getManager() {
		return manager;
	}

	public void setManager(TerrainObjectManager manager) {
		this.manager = manager;
	}
	
}
