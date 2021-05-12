package seaSaltedEngine.render.model.texture;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

public class TextureLoader {
	
	private static List<Integer> textureCache;
	
	public static void init() {
		setTextureCache(new ArrayList<Integer>());
	}
	
	public static int loadTexture(String fileName) {
		Texture tex = new Texture("/res/"+fileName+".png");
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
		int textureID = tex.getID();
		textureCache.add(textureID);
		return textureID;
	}
	
	public static void deleteTextures() {
		for(Integer i : textureCache) {
			GL11.glDeleteTextures(i);
		}
	}

	public static List<Integer> getTextureCache() {
		return textureCache;
	}

	public static void setTextureCache(List<Integer> textureCache) {
		TextureLoader.textureCache = textureCache;
	}

}
