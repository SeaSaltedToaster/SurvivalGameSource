package survivalGame.world.dualContouring;

import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.dualContouring.octree.OctreeNodeType;
import survivalGame.world.terrain.TerrainChunk;
import static survivalGame.world.dualContouring.DcArrays.*;

public class OctreeBuilder {
	
	public static OctreeNode BuildOctree(TerrainChunk parentChunk, Vector3f position, int octreeSize) {
        OctreeNode root = new OctreeNode(position, octreeSize, OctreeNodeType.Node_Internal);
        return root;
    }
	
	public static OctreeNode ConstructOctreeNodes(OctreeNode node, TerrainChunk chunk) {
		//If the voxel is size 1, a voxel leaf is made
        if (node.getSize() == 1)
            return OctreeLeafBuilder.ConstructLeaf(node, chunk);
        
        //If the tree isn't at size 1 (voxel size where a vertex goes)
        for (int i = 0; i < 8; i++) {
        	Vector3f childMin = node.getPosition().add(CHILD_MIN_OFFSETS[i].mul(node.getSize() / 2));
        	OctreeNode child = new OctreeNode(childMin, (node.getSize() / 2), OctreeNodeType.Node_Internal);
        	node.getChildren()[i] = ConstructOctreeNodes(child, chunk);
        }
        return node;
    }

}
