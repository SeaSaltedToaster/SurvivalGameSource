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
		loadModelToCache(WavefrontLoader.loadObjModel("assets/dragons/dragon_1"), 1, true);
		loadModelToCache(WavefrontLoader.loadObjModel("01_WoodenPickaxe"), 2, true);
		loadModelToCache(WavefrontLoader.loadObjModel("assets/trees/tree"), 3, true);
		loadModelToCache(WavefrontLoader.loadObjModel("assets/grass"), 4, true);
		loadModelToCache(WavefrontLoader.loadObjModel("assets/ores/blue_crystal"), 5, true);
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
