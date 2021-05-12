package survivalGame.world.terrain.data;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.objects.Triangle;
import seaSaltedEngine.basic.objects.Vertex;
import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.MeshData;

public class TerrainMesh {

	private Mesh terrainMesh;
	private List<Vertex> vertices;
	private List<Triangle> triangles;
	
	public TerrainMesh() {
		vertices = new ArrayList<Vertex>();
		triangles = new ArrayList<Triangle>();
		terrainMesh = new Mesh(new MeshData(null));
	}

	public Mesh getTerrainMesh() {
		return terrainMesh;
	}

	public void setTerrainMesh(Mesh terrainMesh) {
		this.terrainMesh = terrainMesh;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public List<Triangle> getTriangles() {
		return triangles;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public void setTriangles(List<Triangle> triangles) {
		this.triangles = triangles;
	}
	
}
