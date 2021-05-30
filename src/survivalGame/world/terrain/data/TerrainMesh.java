package survivalGame.world.terrain.data;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Triangle;
import seaSaltedEngine.basic.objects.Vertex;
import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.MeshData;
import seaSaltedEngine.tools.MeshBuilder;

public class TerrainMesh {

	//Terrain Mesh
	private Mesh terrainMesh;
	
	//Original Mesh Data
	private List<Vertex> vertices;
	private List<Triangle> triangles;
	
	//Converted Mesh Data
	private float[] positions, normals, colors;
	private int[] indices;
	
	public TerrainMesh() {
		vertices = new ArrayList<Vertex>();
		triangles = new ArrayList<Triangle>();
		terrainMesh = new Mesh(new MeshData(null));
	}
	
	public void convertMeshData() {
		positions = MeshBuilder.buildVertices(vertices);
		normals = MeshBuilder.extractNormals(vertices);
		colors = MeshBuilder.getColors(vertices);
		indices = MeshBuilder.sortIndices(triangles);
	}
	
	public Mesh generate() {
		return new Mesh(new MeshData(Engine.getRenderer().getLoader().loadToVAO(positions, colors, normals, indices)));
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

	public float[] getPositions() {
		return positions;
	}

	public float[] getNormals() {
		return normals;
	}

	public float[] getColors() {
		return colors;
	}

	public int[] getIndices() {
		return indices;
	}

	public void setPositions(float[] positions) {
		this.positions = positions;
	}

	public void setNormals(float[] normals) {
		this.normals = normals;
	}

	public void setColors(float[] colors) {
		this.colors = colors;
	}

	public void setIndices(int[] indices) {
		this.indices = indices;
	}
	
}
