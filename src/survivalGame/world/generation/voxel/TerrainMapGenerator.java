package survivalGame.world.generation.voxel;

import java.util.Random;

import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.generator.OpenSimplexNoise;

public class TerrainMapGenerator {
	
	private static OpenSimplexNoise noise = new OpenSimplexNoise(new Random().nextInt());
	private static float SURFACE_VALUE = 10;
	
	public static float[][][] generateTerrainMap(int size, TerrainChunk chunk) {
		float[][][] terrainMap = new float[size+1][size+1][size+1];
		for (int x = 0; x < size+1; x++) {
            for (int z = 0; z < size+1; z++) {
                for (int y = 0; y < size+1; y++) {

                	float voxel = (float) ((float) SURFACE_VALUE + noise.eval((x/2)+chunk.getIndexX()*32, (z/2)+chunk.getIndexZ()*32));
                	float point = 0;
                	
                    if (y <= voxel - 16)
                        point = 0f;
                    else if (y > voxel + 16)
                        point = 1f;
                    else if (y > voxel)
                        point = (float)y - voxel;
                	
                    terrainMap[x][y][z] = point * 2 - 1;
                    
                }
            }
		}
		return terrainMap;
	}
	
}
