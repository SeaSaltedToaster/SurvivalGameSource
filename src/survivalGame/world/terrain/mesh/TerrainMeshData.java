package survivalGame.world.terrain.mesh;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.objects.Vertex;

public class TerrainMeshData {
	
	private List<Vertex> vertices;
	private List<Integer> indices;

	public TerrainMeshData() {
		this.vertices = new ArrayList<Vertex>();
		this.indices = new ArrayList<Integer>();
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
	
}
