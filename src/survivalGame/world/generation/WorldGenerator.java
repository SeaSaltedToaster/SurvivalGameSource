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
		worldChunks = new HashMap<TerrainChunk, Boolean>();
		generateTerrain(0,0);
	}
	
	public static void updateWorld() {
		
	}
	
	private static void generateTerrain(int dx, int dz) {
		//Basic Temporary Settings
		float distance = 5;
		float terrainSize = 64;
		//Loop and create chunks every 64 tiles
		int index = 1;
		for(int x = dx; x < distance; x++) {
			for(int z = dz; z < distance; z++) {
				//Create
				if(exists(x,z)) continue;
				TerrainChunk chunk = new TerrainChunk(new Vector3f(x*terrainSize,0,z*terrainSize),x,z);
				dispatchRequest(chunk);
				
				//Broadcast
				Logger.Log("Chunk generated... ("+index+"/"+(int)(distance*distance)+")"); index++;
			}
		}
	}
	
	private static boolean exists(int x, int z) {
		for(Entry<TerrainChunk, Boolean> entry : getWorldChunks().entrySet()) {
			TerrainChunk chunk = entry.getKey();
			if(chunk.getIndexX() == x && chunk.getIndexZ() == z) 
				return true;
		}
		return false;
	}
	
	private static void dispatchRequest(TerrainChunk chunk) {
		//Add to list (false = no render)
		worldChunks.put(chunk, false);
		
		//Send request
		TerrainLoadRequest request = new TerrainLoadRequest(chunk, true);
		GlRequestProcessor.sendRequest(request);
	}
	
	public static void shutdown() {
		
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

	public static HashMap<TerrainChunk, Boolean> getWorldChunks() {
		return worldChunks;
	}

}
