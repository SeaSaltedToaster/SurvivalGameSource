package survivalGame.world.dualContouring;

import java.util.List;

import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.basic.objects.Vertex;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.dualContouring.qef.Dc;
import survivalGame.world.terrain.TerrainChunk;

public class DualContouring {
	
	public static final int MATERIAL_AIR = 0;
    public static final int MATERIAL_SOLID = 1;
    
    public static void GenerateMeshFromOctree(OctreeNode node, List<Vertex> vertexBuffer, List<Integer> indexBuffer, TerrainChunk chunk) {
        GenerateVertexIndices(node, vertexBuffer, chunk);
        Dc.ContourCellProc(node, indexBuffer);
    }

    private static void GenerateVertexIndices(OctreeNode node, List<Vertex> vertexBuffer, TerrainChunk chunk) {
    	if(node == null) return;
        
        switch(node.Type) {
        	case Node_Leaf:
        		node.drawInfo.index = vertexBuffer.size();
                Vertex vertex = new Vertex(node.drawInfo.position.getVec3f(), node.drawInfo.averageNormal, new Color(0,1,0));
                adjustEdgeVertex(vertex, node);
                vertexBuffer.add(vertex);
                break;
        	case Node_Internal:
                for (int i = 0; i < 8; i++) {
                    GenerateVertexIndices(node.children[i], vertexBuffer, chunk);
                }
        		break;
		default:
			break;
        }
    }
    
    private static void adjustEdgeVertex(Vertex vertex, OctreeNode node) {
    	
    }

    public static void DestroyOctree(OctreeNode node) {
        for (int i = 0; i < 8; i++) {
            DestroyOctree(node.children[i]);
        }
        node.drawInfo = null;
    }
	
	

}
