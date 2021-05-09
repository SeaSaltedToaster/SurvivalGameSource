package survivalGame.world.dualContouring.octree;

import seaSaltedEngine.tools.math.Vector3f;

public class OctreeNode {

	private Vector3f position;
	private float size;
	
	private OctreeNodeType nodeType;
	private OctreeInfo nodeInfo;
	private OctreeNode[] children;
	
	public OctreeNode(Vector3f position, float size, OctreeNodeType nodeType) {
		this.position = position;
		this.size = size;
		this.nodeType = nodeType;
		this.children = new OctreeNode[8];
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getSize() {
		return size;
	}

	public OctreeNodeType getNodeType() {
		return nodeType;
	}

	public OctreeInfo getNodeInfo() {
		return nodeInfo;
	}

	public OctreeNode[] getChildren() {
		return children;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public void setNodeType(OctreeNodeType nodeType) {
		this.nodeType = nodeType;
	}

	public void setNodeInfo(OctreeInfo nodeInfo) {
		this.nodeInfo = nodeInfo;
	}

	public void setChildren(OctreeNode[] children) {
		this.children = children;
	}
	
	
	
}
