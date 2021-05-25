package survivalGame.world.terrain;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import seaSaltedEngine.tools.MeshBuilder;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.entity.world.EntityGrass;
import survivalGame.world.GameWorld;
import survivalGame.world.dualContouring.DualContouring;
import survivalGame.world.dualContouring.OctreeBuilder;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.generation.WorldGenerator;
import survivalGame.world.generation.grass.GrassRenderManager;
import survivalGame.world.generation.voxel.TerrainMapGenerator;
import survivalGame.world.terrain.data.TerrainMesh;
import survivalGame.world.terrain.generator.load.TerrainLoadRequest;

public class TerrainChunk {

	private Vector3f position;
	private int indexX, indexZ;
	
	private TerrainMesh mesh;
	private float[][][] terrainMap;
	private Color[][][] colorMap;
	
	public List<Entity> terrainEntities;
	
	public TerrainChunk(Vector3f position, int indexX, int indexZ) {
		this.position = position;
		this.indexX = indexX;
		this.indexZ = indexZ;
		this.mesh = new TerrainMesh();
		this.colorMap = new Color[65][65][65];
		this.terrainEntities = new ArrayList<Entity>();
	}

	public void generate(boolean newChunk) {
		if(newChunk)
			terrainMap = TerrainMapGenerator.generateTerrainMap(64, this);
		OctreeNode node = OctreeBuilder.BuildOctree(this, getPosition(), 64);
		DualContouring.GenerateMeshFromOctree(node, mesh.getVertices(), mesh.getTriangles(), this);
	}
	
	public void generateMesh() {
		mesh.getTerrainMesh().getMeshData().setMeshVao(MeshBuilder.createModel(mesh.getVertices(), mesh.getTriangles()));
		WorldGenerator.setLoadStatus(this, true);
		GrassRenderManager.getGrass().addAll(terrainEntities);
	}
	
	public void regenerate() {
		TerrainLoadRequest request = new TerrainLoadRequest(this);
		GlRequestProcessor.sendRequest(request);
	}
	
	public void addObject(Entity object, float minDistance) {
		for(Entity entity : terrainEntities) {
			if(Math.abs(object.getTransform().getPosition().subtract(entity.getTransform().getPosition()).length()) < minDistance) 
				return;
		}
		if(this.terrainEntities.size() > 250)
			return;
		this.terrainEntities.add(object);
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

	public Color[][][] getColorMap() {
		return colorMap;
	}

	public List<Entity> getTerrainEntities() {
		return terrainEntities;
	}
	
}