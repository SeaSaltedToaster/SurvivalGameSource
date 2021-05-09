package survivalGame.world.terrain.generator;

import java.util.Random;

public class TerrainMapGenerator {
	
	private static OpenSimplexNoise noise = new OpenSimplexNoise(new Random().nextInt());
	
	public static float[][][] generateTerrainMap(int size) {
		float[][][] terrainMap = new float[size][size][size];
		for (int x = 0; x < size; x++) {
            for (int z = 0; z < size; z++) {
                for (int y = 0; y < size; y++) {

                	float voxel = (float) ((float) 10+noise.eval(x, z));
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
