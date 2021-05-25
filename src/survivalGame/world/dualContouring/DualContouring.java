package survivalGame.world.dualContouring;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.objects.Axis;
import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.basic.objects.Triangle;
import seaSaltedEngine.basic.objects.Vertex;
import seaSaltedEngine.tools.math.Vector2f;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.dualContouring.tools.ComplexTriangle;
import survivalGame.world.generation.WorldGenerator;
import survivalGame.world.terrain.TerrainChunk;

public class DualContouring {
	
	public static void GenerateMeshFromOctree(OctreeNode node, List<Vertex> vertexBuffer, List<Triangle> triangleBuffer, TerrainChunk chunk) {
        generateVertexIndices(node, vertexBuffer, chunk);
        Dc.ContourCellProc(node, triangleBuffer);
        calculateVertexNormals(registerTriangles(vertexBuffer, triangleBuffer), vertexBuffer);
    }

    private static void generateVertexIndices(OctreeNode node, List<Vertex> vertexBuffer, TerrainChunk chunk) {
    	if(node == null) return;
        
        switch(node.getNodeType()) {
        	case Node_Leaf:
        		Vector3f pos = node.getPosition();
        		node.getNodeInfo().setIndice(vertexBuffer.size());
        		float subX = Math.abs(chunk.getIndexX() * 64);
        		float subZ = Math.abs(chunk.getIndexZ() * 64);
                Vertex vertex = new Vertex(pos, new Vector3f(0,1,0), chunk.getColorMap()[(int) (pos.x - subX)][(int) pos.y][(int) (pos.z - subZ)]);
                vertexBuffer.add(vertex);
//                debugEdges(vertex, chunk);
//                adjustEdgeVertex(vertex, chunk);
                break;
        	case Node_Internal:
                for (int i = 0; i < 8; i++) {
                    generateVertexIndices(node.getChildren()[i], vertexBuffer, chunk);
                }
        		break;
		default:
			break;
        }
    }
   
    private static void adjustEdgeVertex(Vertex vertex, TerrainChunk chunk) {
    	for(Axis axis : Axis.values()) {
    		if(WorldGenerator.getNearbyChunk(chunk, axis) != null) {
    			for(Vertex vertexConnect : WorldGenerator.getNearbyChunk(chunk, axis).getMesh().getVertices()) {
    				if(vertexConnect.getPosition().subtract(vertex.getPosition()).lengthSquared() < 2) {
    					vertex.setPosition(vertexConnect.getPosition());
    					continue;
    				}
    			}
    		}
    	}
    }
    
    public static void debugEdges(Vertex vertex, TerrainChunk chunk) {
    	for(int i = 0; i < 64; i++) {
    		if(chunk.getPosition().toVector2f().add(new Vector2f(i,0)).subtract(vertex.getPosition().toVector2f()).lengthSquared() < 2)
    			vertex.setVertexColor(new Color(1,0,0));
    		if(chunk.getPosition().toVector2f().add(new Vector2f(0,i)).subtract(vertex.getPosition().toVector2f()).lengthSquared() < 2)
    			vertex.setVertexColor(new Color(1,0,0));
    		if(chunk.getPosition().toVector2f().add(new Vector2f(i,64)).subtract(vertex.getPosition().toVector2f()).lengthSquared() < 2)
    			vertex.setVertexColor(new Color(1,0,0));
    		if(chunk.getPosition().toVector2f().add(new Vector2f(64,i)).subtract(vertex.getPosition().toVector2f()).lengthSquared() < 2)
    			vertex.setVertexColor(new Color(1,0,0));
    	}
    }
    
    private static void calculateVertexNormals(List<ComplexTriangle> triangleBuffer, List<Vertex> vertexBuffer) {
    	for(int i = 0; i < triangleBuffer.size(); i++) {
    		ComplexTriangle triangle = triangleBuffer.get(i);
    		Vector3f direction = new Vector3f(0);
    		Vector3f.cross((triangle.getB().getPosition().subtract(triangle.getA().getPosition())), (triangle.getC().getPosition().subtract(triangle.getA().getPosition())), direction);
    		setNormal(triangle, vertexBuffer, direction.normalize());
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

    public static void DestroyOctree(OctreeNode node) {
        for (int i = 0; i < 8; i++) {
            DestroyOctree(node.getChildren()[i]);
        }
        node.setNodeInfo(null);
    }

}
