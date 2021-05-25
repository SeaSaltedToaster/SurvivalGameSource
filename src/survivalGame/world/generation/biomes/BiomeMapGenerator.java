package survivalGame.world.generation.biomes;

import java.util.Random;

import seaSaltedEngine.basic.objects.Color;

public class BiomeMapGenerator {

	public static Random random = new Random();
	
	public static Color[][] generateMap(int size) {
		
		Color[][] BiomeMap = new Color[size][size];
		
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				BiomeMap[x][y] = Biome.PLAINS.getColor();
			}
		}
		
//		for(int i = 0; i < 25; i++) {
//			for(int x = 1; x < size - 1; x++) {
//				for(int y = 1; y < size - 1; y++) {	
//					if(BiomeMap[x][y] != null) {
//						
//						if(random.nextInt(100) > 50)
//							BiomeMap[x+1][y+1] = BiomeMap[x][y];
//						if(random.nextInt(100) > 50)
//							BiomeMap[x-1][y+1] = BiomeMap[x][y];
//						if(random.nextInt(100) > 50)
//							BiomeMap[x+1][y-1] = BiomeMap[x][y];
//						if(random.nextInt(100) > 50)
//							BiomeMap[x-1][y-1] = BiomeMap[x][y];
//						if(random.nextInt(100) > 50)
//							BiomeMap[x+1][y] = BiomeMap[x][y];
//						if(random.nextInt(100) > 50)
//							BiomeMap[x][y+1] = BiomeMap[x][y];
//						if(random.nextInt(100) > 50)
//							BiomeMap[x][y-1] = BiomeMap[x][y];
//						if(random.nextInt(100) > 50)
//							BiomeMap[x-1][y] = BiomeMap[x][y];
//					}
//				}
//			}
//		}
		
		return BiomeMap;
	}
	
}

enum Biome {
	
	PLAINS(new Color(0.2f,0.6f,0.2f)), DESERT(new Color(2f,2f,2f)); 
	
	Color color;
	Biome(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
}