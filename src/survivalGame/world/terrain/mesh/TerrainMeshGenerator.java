package survivalGame.world.terrain.mesh;

import java.util.List;

import seaSaltedEngine.basic.objects.Vertex;
import survivalGame.world.dualContouring.octree.OctreeNode;
import static survivalGame.world.dualContouring.octree.OctreeNodeType.*;

public class TerrainMeshGenerator {
	
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

}
