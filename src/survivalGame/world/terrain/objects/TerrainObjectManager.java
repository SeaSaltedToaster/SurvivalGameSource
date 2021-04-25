package survivalGame.world.terrain.objects;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.tools.math.Vector3f;

public class TerrainObjectManager {
	
	private List<Vector3f> placableAreas;
	private List<Entity> chunkEntities;
	
	public TerrainObjectManager() {
		this.placableAreas = new ArrayList<Vector3f>();
		this.chunkEntities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity entity) {
		this.chunkEntities.add(entity);
	}
	
	public void addArea(Vector3f pos) {
		this.placableAreas.add(pos);
	}

	public List<Vector3f> getPlacableAreas() {
		return placableAreas;
	}

	public void setPlacableAreas(List<Vector3f> placableAreas) {
		this.placableAreas = placableAreas;
	}

	public List<Entity> getChunkEntities() {
		return chunkEntities;
	}

	public void setChunkEntities(List<Entity> chunkEntities) {
		this.chunkEntities = chunkEntities;
	}

}
