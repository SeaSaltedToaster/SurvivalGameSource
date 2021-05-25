package survivalGame.world.generation;

import java.util.HashMap;
import java.util.Map.Entry;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.objects.Axis;
import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.generator.load.TerrainLoadRequest;

public class WorldGenerator {
	
	private static HashMap<TerrainChunk, Boolean> worldChunks;
	
	//TODO Eventually take in a seed or world name for saving
	public static void generateWorld() {
		init();
		generateTerrain();
	}
	
	private static void generateTerrain() {
		//Basic Temporary Settings
		float distance = 5;
		float terrainSize = 64;
		//Loop and create chunks every 64 tiles
		int index = 1;
		for(int x = 0; x < distance; x++) {
			for(int z = 0; z < distance; z++) {
				//Create
				TerrainChunk chunk = new TerrainChunk(new Vector3f(x*terrainSize,0,z*terrainSize),x,z);
				dispatchRequest(chunk);
				
				//Broadcast
				Logger.Log("Chunk generated... ("+index+"/"+(int)(distance*distance)+")"); index++;
			}
		}
	}
	
	private static void dispatchRequest(TerrainChunk chunk) {
		//Add to list (false = no render)
		worldChunks.put(chunk, false);
		
		//Send request
		TerrainLoadRequest request = new TerrainLoadRequest(chunk);
		GlRequestProcessor.sendRequest(request);
	}
	
	public static TerrainChunk getNearbyChunk(TerrainChunk chunk, Axis axis) {
		switch(axis) {
			case x:
				for(Entry<TerrainChunk, Boolean> entry : WorldGenerator.getWorldChunks().entrySet()) {
					if(entry.getKey().getIndexX() == chunk.getIndexX()+1)
						return entry.getKey();
				}	
			case z:
				for(Entry<TerrainChunk, Boolean> entry : WorldGenerator.getWorldChunks().entrySet()) {
					if(entry.getKey().getIndexZ() == chunk.getIndexZ()+1)
						return entry.getKey();
				}
			case nx:
				for(Entry<TerrainChunk, Boolean> entry : WorldGenerator.getWorldChunks().entrySet()) {
					if(entry.getKey().getIndexX() == chunk.getIndexX()-1)
						return entry.getKey();
				}
			case nz:
				for(Entry<TerrainChunk, Boolean> entry : WorldGenerator.getWorldChunks().entrySet()) {
					if(entry.getKey().getIndexZ() == chunk.getIndexZ()-1)
						return entry.getKey();
				}
		case ny:
			break;
		case y:
			break;
		default:
			break;
		}
		return null;
	}
	
	public static void setLoadStatus(TerrainChunk chunk, boolean state) {
		for(Entry<TerrainChunk, Boolean> entry : WorldGenerator.getWorldChunks().entrySet()) {
			if(entry.getKey().equals(chunk))
				entry.setValue(state);
		}
	}
	
	private static void init() {
		worldChunks = new HashMap<TerrainChunk, Boolean>();
	}

	public static HashMap<TerrainChunk, Boolean> getWorldChunks() {
		return worldChunks;
	}

}
