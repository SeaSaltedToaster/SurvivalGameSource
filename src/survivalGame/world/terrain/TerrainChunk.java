package survivalGame.world.terrain;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.objects.Triangle;
import seaSaltedEngine.basic.objects.Vertex;
import seaSaltedEngine.collision.PhysicsManager;
import seaSaltedEngine.collision.mesh.CollisionMesh;
import seaSaltedEngine.collision.mesh.CollisionMeshBuilder;
import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import seaSaltedEngine.tools.MeshBuilder;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.dualContouring.DualContouring;
import survivalGame.world.dualContouring.OctreeBuilder;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.generation.WorldGenerator;
import survivalGame.world.generation.voxel.TerrainMapGenerator;
import survivalGame.world.terrain.data.TerrainMesh;
import survivalGame.world.terrain.generator.load.TerrainLoadRequest;

public class TerrainChunk {

	private Vector3f position;
	private int indexX, indexZ;
	
	private TerrainMesh mesh;
	private float[][][] terrainMap;
	
	public TerrainChunk(Vector3f position, int indexX, int indexZ) {
		this.position = position;
		this.indexX = indexX;
		this.indexZ = indexZ;
		this.mesh = new TerrainMesh();
	}

	public void generate(boolean newChunk) {
		if(newChunk)
			terrainMap = TerrainMapGenerator.generateTerrainMap(64, this);
		OctreeNode node = OctreeBuilder.BuildOctree(this, getPosition(), 64);
		List<Vertex> vertexBuffer = new ArrayList<Vertex>();
		List<Triangle> triBuffer = new ArrayList<Triangle>();
		DualContouring.GenerateMeshFromOctree(node, vertexBuffer, triBuffer, this);
		mesh.setTriangles(triBuffer); mesh.setVertices(vertexBuffer);
	}
	
	public void generateMesh() {
		mesh.getTerrainMesh().getMeshData().setMeshVao(MeshBuilder.createModel(mesh.getVertices(), mesh.getTriangles()));
		CollisionMesh obj = CollisionMeshBuilder.buildCollisionMesh(mesh.getVertices(), mesh.getTriangles(), position);
		PhysicsManager.getDynamicsWorld().addRigidBody(obj.getRigidBody());
		WorldGenerator.setLoadStatus(this, true);
	}
	
	public void regenerate() {
		TerrainLoadRequest request = new TerrainLoadRequest(this);
		GlRequestProcessor.sendRequest(request);
	}
	
	public TerrainMesh getMesh() {
		return mesh;
	}

	public Vector3f getPosition() {
		return position;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public int getIndexX() {
		return indexX;
	}

	public int getIndexZ() {
		return indexZ;
	}

	public float[][][] getTerrainMap() {
		return terrainMap;
	}
	
}