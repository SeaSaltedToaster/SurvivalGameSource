package survivalGame.world.terrain.blocks;

import java.util.Random;

import survivalGame.world.TerrainGenerator;
import survivalGame.world.dualContouring.OpenSimplexNoise;

public class VoxelTerrainGenerator {
	
	protected OpenSimplexNoise noise;
	public static float SURFACE_LEVEL = 10;
	protected float[][][] blockMap;
	
	public VoxelTerrainGenerator() {
		int size = TerrainGenerator.TERRAIN_SIZE+1;
		blockMap = new float[size][size][size];
	}
	
	public void generate() {
		noise = new OpenSimplexNoise(new Random().nextInt());
		for (int x = 0; x < 64 + 1; x++) {
            for (int z = 0; z < 64 + 1; z++) {
                for (int y = 0; y < 64 + 1; y++) {

                	float voxel = (float) (SURFACE_LEVEL + noise.eval(x, z));
                	float point = 0;
                    
                    if (y <= voxel - 16)
                        point = 0f;
                    else if (y > voxel + 16)
                        point = 1f;
                    else if (y > voxel)
                        point = (float)y - voxel;
                    else
                        point = voxel - (float)y;
                    
                    this.blockMap[x][y][z] = point;
                }
            }
        }
	}

	public OpenSimplexNoise getNoise() {
		return noise;
	}

	public static float getSURFACE_LEVEL() {
		return SURFACE_LEVEL;
	}

	public float[][][] getBlockMap() {
		return blockMap;
	}

}
