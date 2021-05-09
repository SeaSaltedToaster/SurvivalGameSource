package survivalGame.world.terrain.data;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.MeshData;
import survivalGame.world.terrain.render.TerrainTriangle;
import survivalGame.world.terrain.render.TerrainVertex;

public class TerrainMesh {

	private Mesh terrainMesh;
	private List<TerrainVertex> vertices;
	private List<TerrainTriangle> triangles;
	
	public TerrainMesh() {
		vertices = new ArrayList<TerrainVertex>();
		triangles = new ArrayList<TerrainTriangle>();
		terrainMesh = new Mesh(new MeshData(null));
	}

	public Mesh getTerrainMesh() {
		return terrainMesh;
	}

	public void setTerrainMesh(Mesh terrainMesh) {
		this.terrainMesh = terrainMesh;
	}

	public List<TerrainVertex> getVertices() {
		return vertices;
	}

	public List<TerrainTriangle> getTriangles() {
		return triangles;
	}
	
}
