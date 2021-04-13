package survivalGame.resources;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.loaders.WavefrontLoader;

public class Models {
	
	private static Map<Mesh, Entry<Integer, Boolean>> modelCache = new HashMap<Mesh, Entry<Integer, Boolean>>();
	
	public static void loadModelCache() {
		loadModelToCache(WavefrontLoader.loadObjModel("01_WoodenPickaxe"), 1, true);
		loadModelToCache(WavefrontLoader.loadObjModel("WoodenWall"), 2, true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void loadModelToCache(Mesh mesh, Integer id, boolean isLoaded) {
		modelCache.put(mesh, new SimpleEntry(id,mesh));
	}
	
	public static Mesh getModelFromID(int id) {
		for(Entry<Mesh, Entry<Integer, Boolean>> entry: modelCache.entrySet()) {
		      if(entry.getValue().getKey() == id) {
		        return entry.getKey();
		      }
		}
		return null;
	}

}
