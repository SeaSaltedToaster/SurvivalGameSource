package survivalGame.world.generation.voxel;

import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.generation.noise.SimplexNoise;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.voxel.Materials;
import survivalGame.world.terrain.voxel.Material;
import survivalGame.world.terrain.voxel.Voxel;

public class TerrainMapGenerator {
	
	private static float SURFACE_VALUE = 20;
	
	public static Voxel[][][] generateTerrainMap(int size, TerrainChunk chunk) {
		
		Voxel[][][] terrainMap = initializeGenerator(size);
//		int[][][] caveMap = TerrainCaveGenerator.GenerateMap(size+2);
		
		for (int x = 0; x < size; x++) {
            for (int z = 0; z < size; z++) {
                for (int y = 0; y < size; y++) {
                	
                	//Density Calculations
                	float density = (SimplexNoise.Sample(new Vector3f(x + (chunk.getIndexX()*64),y,z + (chunk.getIndexZ()*64)).div(4)) + 5);
                	
//                	if(y < SURFACE_VALUE && x < 64 && z < 64) {
//                		if(caveMap[x][y][z] == 0)
//                			density += 10;
//            		}
                	
                	//Color Adjusting
                	Material voxelColor = Materials.GRASS;
                	
                	if(y < SURFACE_VALUE || density < 1f) {
                		voxelColor = Materials.DIRT;
            		} else if(y < SURFACE_VALUE - 20) {
            			voxelColor = Materials.STONE;
            		}

                	terrainMap[x][y][z] = new Voxel(voxelColor, (byte) density);
                	
                }
            }
		}
		return terrainMap;
	}

	private static Voxel[][][] initializeGenerator(int size) {
		Voxel[][][] terrainMap = new Voxel[size][size][size];
		return terrainMap;
	}
	
}
