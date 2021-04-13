package survivalGame.world.dualContouring;

import java.util.List;

import seaSaltedEngine.basic.objects.Vertex;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.dualContouring.qef.Dc;
import static survivalGame.world.dualContouring.octree.OctreeNodeType.*;

public class DualContouring {
	
	public static final int MATERIAL_AIR = 0;
    public static final int MATERIAL_SOLID = 1;

    static final Vector3f[] CHILD_MIN_OFFSETS = {
            // needs to match the vertMap from Dual Contouring impl
            new Vector3f( 0, 0, 0 ),
            new Vector3f( 0, 0, 1 ),
            new Vector3f( 0, 1, 0 ),
            new Vector3f( 0, 1, 1 ),
            new Vector3f( 1, 0, 0 ),
            new Vector3f( 1, 0, 1 ),
            new Vector3f( 1, 1, 0 ),
            new Vector3f( 1, 1, 1 ),
    };

    public static final int[][] edgevmap = {
        {0,4},{1,5},{2,6},{3,7},	// x-axis
        {0,2},{1,3},{4,6},{5,7},	// y-axis
        {0,1},{2,3},{4,5},{6,7}		// z-axis
    };

    private static void GenerateVertexIndices(OctreeNode node, List<Vertex> vertexBuffer) {
        if (node == null) {
            return;
        }

        if (node.Type != Node_Leaf) {
            for (int i = 0; i < 8; i++) {
                GenerateVertexIndices(node.children[i], vertexBuffer);
            }
        }

        if (node.Type != Node_Internal) {
            node.drawInfo.index = vertexBuffer.size();
            vertexBuffer.add(new Vertex(node.drawInfo.position.getVec3f(), node.drawInfo.averageNormal));
        }
    }

    public static void GenerateMeshFromOctree(OctreeNode node, List<Vertex> vertexBuffer, List<Integer> indexBuffer) {
        if (node == null) {
            return;
        }

        vertexBuffer.clear();
        indexBuffer.clear();

        GenerateVertexIndices(node, vertexBuffer);
        Dc.ContourCellProc(node, indexBuffer);
    }

    public static void DestroyOctree(OctreeNode node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < 8; i++) {
            DestroyOctree(node.children[i]);
        }

        if (node.drawInfo != null) {
            node.drawInfo = null;
        }
    }
	
	

}
