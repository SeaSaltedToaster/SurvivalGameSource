package survivalGame.world.dualContouring.octree;

import seaSaltedEngine.tools.math.Vector3f;

public class OctreeNode {
    public OctreeNodeType Type;
    public Vector3f min;
    public int size;
    public OctreeNode[] children;
    public OctreeDrawInfo drawInfo;

    public OctreeNode(Vector3f position, int size, OctreeNodeType type) {
        this.Type = type;
        this.min = position;
        this.size = size;
        this.drawInfo = new OctreeDrawInfo();
        this.children = new OctreeNode[8];
    }
}
