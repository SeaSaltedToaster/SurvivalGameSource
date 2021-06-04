package seaSaltedEngine.guis.core;

import seaSaltedEngine.render.model.texture.Texture;

public class UiMeta {
	
	private int level;
	private Texture guiTexture;
	private boolean hasTexture;
	private float alpha;
	
	public UiMeta(int level, float alpha, Texture guiTexture, boolean hasTexture) {
		this.level = level;
		this.alpha = alpha;
		this.guiTexture = guiTexture;
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
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	public void setGuiTexture(Texture guiTexture) {
		this.guiTexture = guiTexture;
	}

	public boolean isHasTexture() {
		return hasTexture;
	}

	public void setHasTexture(boolean hasTexture) {
		this.hasTexture = hasTexture;
	}

}
