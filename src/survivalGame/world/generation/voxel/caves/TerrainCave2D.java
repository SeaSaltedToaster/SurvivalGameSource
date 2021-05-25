package survivalGame.world.generation.voxel.caves;

import java.util.Random;

public class TerrainCave2D {

	private static int[][] map;
	
	public static int[][] GenerateMap() {
		map = new int[65][65];
		RandomFillMap();

		for (int i = 0; i < 5; i ++) {
			SmoothMap();
		}
		return map;
	}


	static void RandomFillMap() {
		
		Random pseudoRandom = new Random();
		float fillChance = 50;
		
		for (int x = 0; x < 64; x ++) {
			for (int y = 0; y < 64; y ++) {
				if (x == 0 || x == 64-1 || y == 0 || y == 64 -1) {
					map[x][y] = 1;
				}
				else {
					map[x][y] = (pseudoRandom.nextInt(100) < fillChance)? 1: 0;
				}
			}
		}
	}

	static void SmoothMap() {
		for (int x = 0; x < 64; x ++) {
			for (int y = 0; y < 64; y ++) {
				int neighbourWallTiles = GetSurroundingWallCount(x,y);

				if (neighbourWallTiles > 4)
					map[x][y] = 1;
				else if (neighbourWallTiles < 4)
					map[x][y] = 0;

			}
		}
	}

	static int GetSurroundingWallCount(int gridX, int gridY) {
		int wallCount = 0;
		for (int neighbourX = gridX - 1; neighbourX <= gridX + 1; neighbourX ++) {
			for (int neighbourY = gridY - 1; neighbourY <= gridY + 1; neighbourY ++) {
				if (neighbourX >= 0 && neighbourX < 64 && neighbourY >= 0 && neighbourY < 64) {
					if (neighbourX != gridX || neighbourY != gridY) {
						wallCount += map[neighbourX][neighbourY];
					}
				}
				else {
					wallCount ++;
				}
			}
		}

		return wallCount;
	}
	
}
