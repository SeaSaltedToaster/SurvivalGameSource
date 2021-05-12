package seaSaltedEngine.render.model.texture;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Texture {
	
	private int id;
	private int width;
	private int height;
	
	public Texture(String filename) {
		BufferedImage bi;
		BufferedInputStream strem = new BufferedInputStream(Class.class.getResourceAsStream(filename));
		try {
			bi = ImageIO.read(strem);
			
			width = bi.getWidth();
			height = bi.getHeight();
			
			int[] pixels_raw = new int[width * height];
			pixels_raw = bi.getRGB(0, 0, width, height, null, 0, width);
			
			ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
			
			for(int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					int pixel = pixels_raw[i*width+j];
					 
					pixels.put((byte) ((pixel >> 16) & 0xFF));//RED			16
					pixels.put((byte) ((pixel >> 8) & 0xFF));//GREEN		8
					pixels.put((byte) ((pixel >> 0) & 0xFF));//BLUE		0
					pixels.put((byte) ((pixel >> 24) & 0xFF));//ALPHA		24
				}
			}
			
			pixels.flip();
			
			id = GL11.glGenTextures();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
			
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Texture(int tex) {
		this.id = tex;
	}

	public int getID() {
		return id;
	}
	
	public void bind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}
	
	public void unbind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
