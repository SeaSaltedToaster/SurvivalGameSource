package survivalGame.world.dualContouring.octree;

import seaSaltedEngine.tools.math.Vector3f;

public class OctreeInfo {

	private int indice;
	private int cornerCount;
	
	private Vector3f normal;
	
	public OctreeInfo(int indice, int cornerCount, Vector3f normal) {
		this.indice = indice;
		this.cornerCount = cornerCount;
		this.normal = normal;
	}

	public int getIndice() {
		return indice;
	}

	public int getCornerCount() {
		return cornerCount;
	}

	public Vector3f getNormal() {
		return normal;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public void setCornerCount(int cornerCount) {
		this.cornerCount = cornerCount;
	}

	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}
	
}
