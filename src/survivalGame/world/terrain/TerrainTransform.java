package survivalGame.world.terrain;

import seaSaltedEngine.tools.math.Vector3f;

public class TerrainTransform {
	
	private Vector3f position;
	private float indexX, indexY;
	
	public TerrainTransform(Vector3f position, int indexX, int indexY) {
		this.position = position;
		this.indexX = indexX;
		this.indexY = indexY;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getIndexX() {
		return indexX;
	}

	public float getIndexY() {
		return indexY;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setIndexX(float indexX) {
		this.indexX = indexX;
	}

	public void setIndexY(float indexY) {
		this.indexY = indexY;
	}

}
