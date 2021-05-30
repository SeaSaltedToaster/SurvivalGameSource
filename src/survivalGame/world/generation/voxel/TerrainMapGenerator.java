package survivalGame.world.generation.voxel;

import java.util.Random;

import seaSaltedEngine.basic.objects.Color;
import survivalGame.world.generation.biomes.BiomeMapGenerator;
import survivalGame.world.generation.voxel.caves.TerrainCaveGenerator;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.generator.OpenSimplexNoise;

public class TerrainMapGenerator {
	
	private static OpenSimplexNoise noise = new OpenSimplexNoise(new Random().nextInt());
	private static Color[][] biomeMap = BiomeMapGenerator.generateMap(1024); //1024 is the width of 1 region
	
	private static float SURFACE_VALUE = 40;
	
	public static float[][][] generateTerrainMap(int size, TerrainChunk chunk) {
		
		float[][][] terrainMap = initializeGenerator(size);
		int[][][] caveMap = TerrainCaveGenerator.GenerateMap(size+1);
		
		for (int x = 0; x < size+1; x++) {
            for (int z = 0; z < size+1; z++) {
                for (int y = 0; y < size*2+1; y++) {

                	float voxel = (float) ((float) SURFACE_VALUE + (noise.eval( ((x)+chunk.getIndexX()*64) / 4, ((z)+chunk.getIndexZ()*64) / 4) ) * getRandomNoiseMultiplier(x,z));
                	float point = 0;
                	
                	chunk.getColorMap()[x][y][z] = biomeMap[Math.abs(x+chunk.getIndexX()*64)][Math.abs(z+chunk.getIndexZ()*64)];
                	
                	if(caveMap[x][y][z] == 0 && y < SURFACE_VALUE) {
            			voxel = (float) (caveMap[x][y][z] + 1) * 10;
            		}

                	if(y < SURFACE_VALUE - (Math.random()*3) - 5) {
                		chunk.getColorMap()[x][y][z] = new Color(0.82f, 0.55f, 0.08f);
            		}
                	if(y < SURFACE_VALUE - (Math.random()*3) - 15) {
            			chunk.getColorMap()[x][y][z] = new Color(0.4f, 0.4f, 0.4f);
            		}
                	if(y > SURFACE_VALUE + 20) {
                		chunk.getColorMap()[x][y][z] = new Color(0.4f, 0.4f, 0.4f);
                	}
                	if(y > SURFACE_VALUE + 25) {
                		chunk.getColorMap()[x][y][z] = new Color(1f, 1f, 1f);
                	}
                	
                    if (y <= voxel - SURFACE_VALUE)
                        point = -1f;
                    else if (y >= voxel + SURFACE_VALUE)
                        point = 1f;
                    else if (y > voxel)
                        point = y - voxel;
                	
                    terrainMap[x][y][z] = point * 2 -1;
                    
                }
            }
		}
		return terrainMap;
	}
	
	private static float getRandomNoiseMultiplier(float x, float z) {
		return (float) Math.max(Math.min(noise.eval(x, z), 1), 3);
	}
	
	private static float[][][] initializeGenerator(int size) {
		float[][][] terrainMap = new float[size+1][size*2+1][size+1];
		return terrainMap;
	}
	
}
