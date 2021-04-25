package survivalGame.world.terrain.blocks;

import survivalGame.world.dualContouring.OpenSimplexNoise;

public class VoxelTerrainGenerator {
	
	protected OpenSimplexNoise noise;
	public static float SURFACE_LEVEL = 10;
	protected float[][][] blockMap;
	
	public VoxelTerrainGenerator() {
		blockMap = new float[64+1][64+1][64+1];
	}
	
	public void generate() {
		noise = new OpenSimplexNoise(121);
		this.blockMap = new float[64+1][64+1][64+1];
		for (int x = 0; x < 64 + 1; x++) {
            for (int z = 0; z < 64 + 1; z++) {
                for (int y = 0; y < 64 + 1; y++) {

                	float thisHeight = (float) (SURFACE_LEVEL+(noise.eval(x, z)));
                	float point = 0;
                    
                    if (y <= thisHeight - 16)
                        point = 0f;
                    else if (y > thisHeight + 16)
                        point = 1f;
                    else if (y > thisHeight)
                        point = (float)y - thisHeight;
                    else
                        point = thisHeight - (float)y;
                    
                    this.blockMap[x][y][z] = point;
                }
            }
        }
	}

}
