package survivalGame.world.terrain;

import java.util.ArrayList;
import java.util.List;

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
import survivalGame.world.terrain.voxel.Voxel;

public class TerrainChunk {

	private Vector3f position;
	private int indexX, indexZ, chunkID;
	
	private Voxel[][][] voxelMap;
	private TerrainMesh mesh;
	
	public List<Entity> terrainEntities;
	private OctreeNode node;
	
	private int size = 65;

	public TerrainChunk(Vector3f position, int indexX, int indexZ, int chunkID) {
		this.position = position;
		this.indexX = indexX;
		this.indexZ = indexZ;
		this.chunkID = chunkID;
	}

	public void generate(boolean newChunk) {
		if(newChunk) {
			mesh = new TerrainMesh();
			terrainEntities = new ArrayList<Entity>();
			voxelMap = new Voxel[size+1][size+1][size+1];
			voxelMap = TerrainMapGenerator.generateTerrainMap(size+1, this);
			node = OctreeBuilder.BuildOctree(this, getPosition(), size);
		}
		node = OctreeBuilder.ConstructOctreeNodes(node, this);
		mesh.getVertices().clear(); mesh.getTriangles().clear();
		DualContouring.GenerateMeshFromOctree(node, mesh.getVertices(), mesh.getTriangles(), this);
		mesh.convertMeshData(); 
	}
	
	public void generateMesh(boolean isNewChunk) {
		mesh.getTerrainMesh().getMeshData().setMeshVao(mesh.generate().getMeshVao());
		WorldGenerator.setLoadStatus(this, true);
		if(isNewChunk) addWorldEntities();
	}
	
	public void regenerate() {
		TerrainLoadRequest request = new TerrainLoadRequest(this, false);
		request.setRequestId(chunkID);
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
	}
	
	public void increaseVoxelAt(Vector3f pos, float radius) {
		for(int x = (int) (pos.x - radius) - (indexX * size); x < pos.x - (indexX * size) + radius; x++) {
			for(int y = (int) (pos.y - radius); y < pos.y + radius; y++) {
				for(int z = (int) (pos.z - radius) - (indexZ * size); z < pos.z - (indexZ * size) + radius; z++) {
					if(x > 64 || y > 64 || z > 64 || x < 0 || y < 0 || z < 0) break;
						voxelMap[x][y][z].setVoxelDensity(voxelMap[x][y][z].getVoxelDensity()-1);
				}
			}
		}
		regenerate();
	}
	
	public void decreaseVoxelAt(Vector3f pos, float radius) {
		for(int x = (int) (pos.x - radius) - (indexX * size); x < pos.x - (indexX * size) + radius; x++) {
			for(int y = (int) (pos.y - radius); y < pos.y + radius; y++) {
				for(int z = (int) (pos.z - radius) - (indexZ * size); z < pos.z - (indexZ * size) + radius; z++) {
					if(x > 64 || y > 64 || z > 64 || x < 0 || y < 0 || z < 0) break;
						voxelMap[x][y][z].setVoxelDensity(voxelMap[x][y][z].getVoxelDensity()+1);
				}
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

	public Voxel[][][] getTerrainMap() {
		return voxelMap;
	}

	public List<Entity> getTerrainEntities() {
		return terrainEntities;
	}
	
}