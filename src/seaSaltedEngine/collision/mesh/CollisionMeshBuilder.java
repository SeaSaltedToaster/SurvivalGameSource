package seaSaltedEngine.collision.mesh;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import com.bulletphysics.collision.shapes.BvhTriangleMeshShape;
import com.bulletphysics.collision.shapes.IndexedMesh;
import com.bulletphysics.collision.shapes.TriangleIndexVertexArray;

import seaSaltedEngine.basic.objects.Triangle;
import seaSaltedEngine.basic.objects.Vertex;
import seaSaltedEngine.tools.MeshBuilder;
import seaSaltedEngine.tools.math.Vector3f;

public class CollisionMeshBuilder {

	public static CollisionMesh buildCollisionMesh(List<Vertex> vertices, List<Triangle> indices, Vector3f pos) {
		IndexedMesh indexedMesh = getIndexedMesh(vertices, indices);
        BvhTriangleMeshShape meshShape = getMeshShape(indexedMesh);
        
        return new CollisionMesh(meshShape, pos);
	}
	
	private static IndexedMesh getIndexedMesh(List<Vertex> vertices, List<Triangle> indices) {
		IndexedMesh indexedMesh = new IndexedMesh();
        indexedMesh.numTriangles = indices.size();
        indexedMesh.triangleIndexBase = ByteBuffer.allocateDirect((indices.size()*3)*Integer.BYTES*2).order(ByteOrder.nativeOrder());
        indexedMesh.triangleIndexBase.rewind();
        indexedMesh.triangleIndexBase.asIntBuffer().put(MeshBuilder.sortIndices(indices));
        indexedMesh.triangleIndexStride = 3 * Integer.BYTES;
        indexedMesh.numVertices = vertices.size();
        indexedMesh.vertexBase = ByteBuffer.allocateDirect(vertices.size()*3*Float.BYTES*2).order(ByteOrder.nativeOrder());
        indexedMesh.vertexBase.rewind();
        indexedMesh.vertexBase.asFloatBuffer().put(MeshBuilder.buildVertices(vertices));
        indexedMesh.vertexStride = 3 * Float.BYTES;
        return indexedMesh;
	}
	
	private static BvhTriangleMeshShape getMeshShape(IndexedMesh indexedMesh) {
		TriangleIndexVertexArray vertArray = new TriangleIndexVertexArray();
        vertArray.addIndexedMesh(indexedMesh);

        boolean useQuantizedAabbCompression = false;
        BvhTriangleMeshShape meshShape = new BvhTriangleMeshShape(vertArray, useQuantizedAabbCompression);
        meshShape.setLocalScaling(new javax.vecmath.Vector3f(0.5f, 0.5f, 0.5f));
        return meshShape;
	}
	
}
