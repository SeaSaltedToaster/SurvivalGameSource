package survivalGame.world.dualContouring;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.basic.objects.Triangle;
import seaSaltedEngine.basic.objects.Vertex;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.dualContouring.qef.Dc;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.mesh.tools.ComplexTriangle;

public class DualContouring {
	
	public static final int MATERIAL_AIR = 0;
    public static final int MATERIAL_SOLID = 1;
    
    public static void GenerateMeshFromOctree(OctreeNode node, List<Vertex> vertexBuffer, List<Triangle> triangleBuffer, TerrainChunk chunk) {
        generateVertexIndices(node, vertexBuffer, chunk);
        Dc.ContourCellProc(node, triangleBuffer);
        calculateVertexNormals(registerTriangles(vertexBuffer, triangleBuffer), vertexBuffer);
    }

    private static void generateVertexIndices(OctreeNode node, List<Vertex> vertexBuffer, TerrainChunk chunk) {
    	if(node == null) return;
        
        switch(node.Type) {
        	case Node_Leaf:
        		node.drawInfo.index = vertexBuffer.size();
                Vertex vertex = new Vertex(node.drawInfo.position.getVec3f(), new Vector3f(0,1,0), new Color(0,1,0));
                adjustEdgeVertex(vertex, node);
                vertexBuffer.add(vertex);
                break;
        	case Node_Internal:
                for (int i = 0; i < 8; i++) {
                    generateVertexIndices(node.children[i], vertexBuffer, chunk);
                }
        		break;
		default:
			break;
        }
    }
    
    private static void calculateVertexNormals(List<ComplexTriangle> triangleBuffer, List<Vertex> vertexBuffer) {
    	for(int i = 0; i < triangleBuffer.size(); i++) {
    		ComplexTriangle triangle = triangleBuffer.get(i);
    		Vector3f direction = Vector3f.cross((triangle.getB().getPosition().subtract(triangle.getA().getPosition())), (triangle.getC().getPosition().subtract(triangle.getA().getPosition())), null);
//    		direction.normalize();
    		System.out.println(direction);
    		setNormal(triangle, vertexBuffer, direction);
    	}
    }
    
    private static void setNormal(ComplexTriangle triangle, List<Vertex> vertexBuffer, Vector3f normal) {
    	vertexBuffer.get(vertexBuffer.indexOf(triangle.getA())).setNormal(normal);
    	vertexBuffer.get(vertexBuffer.indexOf(triangle.getB())).setNormal(normal);
    	vertexBuffer.get(vertexBuffer.indexOf(triangle.getC())).setNormal(normal);
    }
    
    private static List<ComplexTriangle> registerTriangles(List<Vertex> vertexBuffer, List<Triangle> triangleBuffer) {
    	List<ComplexTriangle> triangles = new ArrayList<ComplexTriangle>();
    	for(Triangle triangle : triangleBuffer) {
    		ComplexTriangle compTriangle = new ComplexTriangle(triangle.v1, triangle.v2, triangle.v3);
    		compTriangle.setA(vertexBuffer.get(triangle.v1));
    		compTriangle.setB(vertexBuffer.get(triangle.v2));
    		compTriangle.setC(vertexBuffer.get(triangle.v3));
    		triangles.add(compTriangle);
    	}
    	return triangles;
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
