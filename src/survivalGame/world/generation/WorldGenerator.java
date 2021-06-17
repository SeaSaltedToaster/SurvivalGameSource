package survivalGame.world.generation;

import java.util.HashMap;
import java.util.Map.Entry;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Axis;
import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import seaSaltedEngine.tools.math.Vector2f;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.generator.load.TerrainLoadRequest;

public class WorldGenerator {
	
	private static HashMap<TerrainChunk, Boolean> worldChunks;
	
	//TODO Eventually take in a seed or world name for saving
	public static void generateWorld() {
		worldChunks = new HashMap<TerrainChunk, Boolean>();
		generateTerrain(Engine.getCamera().getPosition());
	}
	
	public static void updateWorld() {
		
	}
	
	public static void generateTerrain(Vector3f position) {
		int currentChunkCoordX = (int) position.x / 64;
		int currentChunkCoordZ = (int) position.z / 64;
		//Basic Temporary Settings
		int terrainSize = 64;
		int chunkDistance = 4;
		int distance = (chunkDistance * terrainSize) / 2;
		//Loop and create chunks every 64 tiles
		int index = 1;
		for(int x = (distance/terrainSize); x > -(distance/terrainSize); x--) {
			for(int z = (distance/terrainSize); z > -(distance/terrainSize); z--) {
				Vector2f viewedChunkCoord = new Vector2f(currentChunkCoordX+x, currentChunkCoordZ+z);
				//Create
				if(exists((int)viewedChunkCoord.x,(int)viewedChunkCoord.y)) continue;
				TerrainChunk chunk = new TerrainChunk(new Vector3f(viewedChunkCoord.x*terrainSize,0,viewedChunkCoord.y*terrainSize),(int)viewedChunkCoord.x,(int)viewedChunkCoord.y, index);
				dispatchRequest(chunk);
				
				//Broadcast
				index++;
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
		
		//Send request (true = is new chunk)
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
		for(Entry<TerrainChunk, Boolean> entry : worldChunks.entrySet()) {
			if(entry.getKey().getIndexX() == chunk.getIndexX() && entry.getKey().getIndexZ() == chunk.getIndexZ())
				entry.setValue(state);
		}
	}

	public static HashMap<TerrainChunk, Boolean> getWorldChunks() {
		return worldChunks;
	}

}
