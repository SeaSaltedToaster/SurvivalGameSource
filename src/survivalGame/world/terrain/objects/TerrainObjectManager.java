package survivalGame.world.terrain.objects;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.tools.math.Vector3f;

public class TerrainObjectManager {
	
	private List<Vector3f> placableAreas;
	
	public TerrainObjectManager() {
		init();
	}
	
	public void init() {
		placableAreas = new ArrayList<Vector3f>();
	}
	
	public void addArea(Vector3f pos) {
		placableAreas.add(pos);
	}

	public List<Vector3f> getPlacableAreas() {
		return placableAreas;
	}

	public void setPlacableAreas(List<Vector3f> placableAreas) {
		this.placableAreas = placableAreas;
	}

}
