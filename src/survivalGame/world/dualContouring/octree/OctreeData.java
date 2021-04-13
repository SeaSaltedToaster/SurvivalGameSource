package survivalGame.world.dualContouring.octree;

public class OctreeData {

	private OctreeNode[][][] octree;
	
	public OctreeData() {
		octree = new OctreeNode[65][65][65];
	}

	public OctreeNode[][][] getOctree() {
		return octree;
	}

	public void setOctree(OctreeNode[][][] octree) {
		this.octree = octree;
	}
	
}
