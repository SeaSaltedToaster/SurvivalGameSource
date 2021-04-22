package survivalGame.world.terrain.mesh;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.objects.Vertex;

public class TerrainMeshData {
	
	private List<Vertex> vertices;
	private List<Integer> indices;
	private Float[][][] chunkArray;

	public TerrainMeshData() {
		this.vertices = new ArrayList<Vertex>();
		this.indices = new ArrayList<Integer>();
		
		this.chunkArray = new Float[32][32][32];
	}
	
	public void setVoxelAt(int x, int y, int z, float m) {
		this.chunkArray[x][y][z] = m;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public List<Integer> getIndices() {
		return indices;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public void setIndices(List<Integer> indices) {
		this.indices = indices;
	}

	public Float[][][] getChunkArray() {
		return chunkArray;
	}

	public void setChunkArray(Float[][][] chunkArray) {
		this.chunkArray = chunkArray;
	}
	
}
