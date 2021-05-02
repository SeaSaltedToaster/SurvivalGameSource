package survivalGame.world.terrain.mesh;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.objects.Triangle;
import seaSaltedEngine.basic.objects.Vertex;

public class TerrainMeshData {
	
	private List<Vertex> vertices;
	private List<Triangle> indices;
	private Float[][][] chunkArray;

	public TerrainMeshData() {
		this.vertices = new ArrayList<Vertex>();
		this.indices = new ArrayList<Triangle>();
		this.chunkArray = new Float[64][64][64];
	}
	
	public void setVoxelAt(int x, int y, int z, float m) {
		this.chunkArray[x][y][z] = m;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public List<Triangle> getIndices() {
		return indices;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public void setIndices(List<Triangle> indices) {
		this.indices = indices;
	}

	public Float[][][] getChunkArray() {
		return chunkArray;
	}

	public void setChunkArray(Float[][][] chunkArray) {
		this.chunkArray = chunkArray;
	}
	
}
