package survivalGame.world.terrain;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import seaSaltedEngine.tools.math.Vector3f;
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
	private int size= 64;
	
	public List<Entity> terrainEntities;
	private Color[][][] colorMap;
	private float[][][] terrainMap;
	
	public TerrainChunk(Vector3f position, int indexX, int indexZ) {
		this.position = position;
		this.indexX = indexX;
		this.indexZ = indexZ;
		this.mesh = new TerrainMesh();
		this.colorMap = new Color[size+3][size*2+1][size+3];
		this.terrainEntities = new ArrayList<Entity>();
	}

	public void generate(boolean newChunk) {
		if(newChunk)
			terrainMap = TerrainMapGenerator.generateTerrainMap(size, this);
		OctreeNode node = OctreeBuilder.BuildOctree(this, getPosition(), size+2);
		DualContouring.GenerateMeshFromOctree(node, mesh.getVertices(), mesh.getTriangles(), this);
		mesh.convertMeshData(); 
	}
	
	public void generateMesh() {
		mesh.getTerrainMesh().getMeshData().setMeshVao(mesh.generate().getMeshVao());
		WorldGenerator.setLoadStatus(this, true);
		addWorldEntities();
	}
	
	public void regenerate() {
		TerrainLoadRequest request = new TerrainLoadRequest(this, false);
		GlRequestProcessor.sendRequest(request);
	}
	
	private void addWorldEntities() {
		for(Entity entity : terrainEntities) {
			if(entity.hasComponent("Model_Grass")) {
				GrassRenderManager.addEntity(entity);
				continue;
			}
			GameWorld.getMainWorldEntityBatch().add(entity);
		}
		terrainEntities.clear();
		Logger.Log("Entity Count: "+ terrainEntities.size());
	}
	
	public void setVoxelAt() {
		for(int x = 32; x < 42; x++) {
			for(int y = 50; y < 60; y++) {
				terrainMap[x][y][1] = 5;
			}
		}
		regenerate();
	}
	
	public void addObject(Entity object, float minDistance) {
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