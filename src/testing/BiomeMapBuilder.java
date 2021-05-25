package testing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import seaSaltedEngine.basic.objects.Color;

public class BiomeMapBuilder {

	public static void main(String[] args) {
		int size = 1000;
		Color[][] BiomeMap = new Color[size][size];
		
		Random random = new Random((long) Math.random());
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				BiomeMap[x][y] = Biome.values()[random.nextInt(Biome.values().length)].getColor();
			}
		}
		
		for(int i = 0; i < 100; i++) {
			for(int x = 1; x < size - 1; x++) {
				for(int y = 1; y < size - 1; y++) {	
					if(BiomeMap[x][y] != null) {
						
						if(random.nextInt(100) > 75)
							BiomeMap[x+1][y+1] = BiomeMap[x][y];
						if(random.nextInt(100) > 75)
							BiomeMap[x-1][y+1] = BiomeMap[x][y];
						if(random.nextInt(100) > 75)
							BiomeMap[x+1][y-1] = BiomeMap[x][y];
						if(random.nextInt(100) > 75)
							BiomeMap[x-1][y-1] = BiomeMap[x][y];
						if(random.nextInt(100) > 75)
							BiomeMap[x+1][y] = BiomeMap[x][y];
						if(random.nextInt(100) > 75)
							BiomeMap[x][y+1] = BiomeMap[x][y];
						if(random.nextInt(100) > 75)
							BiomeMap[x][y-1] = BiomeMap[x][y];
						if(random.nextInt(100) > 75)
							BiomeMap[x-1][y] = BiomeMap[x][y];
					}
				}
			}
		}
		
		BufferedImage bufferedImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
 
        for(int x = 0; x < size - 1; x++) {
			for(int y = 0; y < size - 1; y++) {
				Color color = BiomeMap[x][y];
				g2d.setColor(new java.awt.Color(color.getR(),color.getG(),color.getB()));
		        g2d.fillRect(x, y, size, size);
			}
		}
        g2d.dispose();
        
        File file = new File("biomes.png");
        try {
			ImageIO.write(bufferedImage, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

enum Biome {
	
	PLAINS(new Color(0.2f,1.0f,0.2f)), DESERT(new Color(0.9f,0.7f,0.2f)), MONTAINS(new Color(0.5f,0.5f,0.5f)), SWAMP(new Color(0.2f,0.7f,0.2f)), SNOW(new Color(1f,1f,1f));
	
	Color color;
	Biome(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
}
