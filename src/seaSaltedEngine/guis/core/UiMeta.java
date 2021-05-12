package seaSaltedEngine.guis.core;

import seaSaltedEngine.render.model.texture.Texture;
import seaSaltedEngine.tools.math.Vector4f;

public class UiMeta {
	
	private int level;
	private Texture guiTexture;
	private boolean hasTexture;
	private float alpha;
	private Vector4f overrideColor;
	
	public UiMeta(int level, float alpha, Texture guiTexture, Vector4f overrideColor, boolean hasTexture) {
		this.level = level;
		this.alpha = alpha;
		this.guiTexture = guiTexture;
		this.overrideColor = overrideColor;
		this.hasTexture = hasTexture;
	}
	
	public int getLevel() {
		return level;
	}
	
	public float getAlpha() {
		return alpha;
	}
	
	public Texture getGuiTexture() {
		return guiTexture;
	}
	
	public Vector4f getOverrideColor() {
		return overrideColor;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	public void setGuiTexture(Texture guiTexture) {
		this.guiTexture = guiTexture;
	}
	
	public void setOverrideColor(Vector4f overrideColor) {
		this.overrideColor = overrideColor;
	}

	public boolean isHasTexture() {
		return hasTexture;
	}

	public void setHasTexture(boolean hasTexture) {
		this.hasTexture = hasTexture;
	}

}
