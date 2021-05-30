package survivalGame.world.generation.voxel.caves;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import seaSaltedEngine.tools.math.Vector3f;

public class TerrainCaveGenerator {

	private static int size;
	private static Random random = new Random();
    private static int[][][] map;
	
    private static int fillPercentage = 66;
    private static int filtering = 12; //TODO Depend of Cave Biome?
    
	public static int[][][] GenerateMap(int mapSize)
    {
		size = mapSize;
        map = new int[size][size*2][size];

        // initialize map
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                for (int z = 0; z < size; z++)
                {
                    if (x == 0 || x == size - 1)        // edges to solid
                    {
                        map[x][y][z] = 1;
                    }
                    else if (y == 0 || y == size - 1)   // edges to solid
                    {
                        map[x][y][z] = 1;
                    }
                    else if (z == 0 || z == size - 1)   // edges to solid
                    {
                        map[x][y][z] = 1;
                    }
                    else
                    {
                        map[x][y][z] = random.nextInt(100) < fillPercentage ? 1 : 0;   // random fill (1 or 0)
                    }
                }
            }
        }

        // noise filter map
        int[][][] filterMap = new int[size][size][size];

        // create map copy
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                for (int z = 0; z < size; z++)
                {
                    filterMap[x][y][z] = map[x][y][z];
                }
            }
        }

        // filter noise
        for (int i = 0; i < filtering; i++)
        {
            for (int x = 1; x < size - 1; x++)
            {
                for (int y = 1; y < size - 1; y++)
                {
                    for (int z = 1; z < size - 1; z++)
                    {
                        int count = 0;

                        for (int xn = -1; xn < 2; xn++)
                        {
                            for (int yn = -1; yn < 2; yn++)
                            {
                                for (int zn = -1; zn < 2; zn++)
                                {
                                    if ((xn == 0 && yn == 0) && zn == 0)
                                    {
                                        continue;
                                    }
                                    else
                                    {
                                        count += map[x + xn][y + yn][z + zn] == 1 ? 1 : 0;
                                    }
                                }
                            }
                        }

                        filterMap[x][y][z] = count > 15 ? 1 : 0;
                    }
                }
            }

            for (int x = 0; x < size; x++)
            {
                for (int y = 0; y < size; y++)
                {
                    for (int z = 0; z < size; z++)
                    {
                        map[x][y][z] = filterMap[x][y][z];
                    }
                }
            }
        }

        int cur = 2;    // current room
        int max = 2;    // largest room
        int volume = 0; // largest room volume

        // fill rooms
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                for (int z = 0; z < size; z++)
                {
                    if (map[x][y][z] == 0)
                    {
                        int v = FloodFill(x, y, z, cur);

                        if (v > volume)
                        {
                            volume = v;
                            max = cur;
                        }

                        cur++;
                    }
                }
            }
        }

        // removes all but largest room
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                for (int z = 0; z < size; z++)
                {
                    if (map[x][y][z] != 1 && map[x][y][z] != max)
                    {
                        map[x][y][z] = 1;
                    }
                    else if (map[x][y][z] == max)
                    {
                        map[x][y][z] = 0;
                    }
                }
            }
        }
        return map;
    }
	
	private static int FloodFill(int x, int y, int z, int fill)
    {
        // volume of room
        int volume = 1;

        // uses BFS flood fill algorithm to fill each room in cave with the room index.

        List<Vector3f> list = new ArrayList<Vector3f>();

        list.add(new Vector3f(x, y, z));

        while (list.size() != 0)
        {
        	Vector3f node = list.get(list.size()-1);

            list.remove(list.size() - 1);

            int a = (int) node.x;
            int b = (int) node.y;
            int c = (int) node.z;

            map[a][b][c] = fill;

            for (int nx = -1; nx < 2; nx++)
            {
                for (int ny = -1; ny < 2; ny++)
                {
                    for (int nz = -1; nz < 2; nz++)
                    {
                        if (Math.abs(nx) + Math.abs(ny) + Math.abs(nz) != 1)
                        {
                            continue;
                        }

                        if (map[a + nx][b + ny][c + nz] == 0)
                        {
                            list.add(new Vector3f(a + nx, b + ny, c + nz));
                            volume++;
                        }
                    }
                }
            }
        }

        // returns the volume of the room filled
        return volume;
    }
	
}
