package seaSaltedEngine.tools;

import java.util.List;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.basic.objects.Triangle;
import seaSaltedEngine.basic.objects.Vertex;
import seaSaltedEngine.render.model.Vao;

public class MeshBuilder {

	public static Vao createModel(List<Vertex> vertices, List<Triangle> triangles) {
		float[] position = buildVertices(vertices);
		float[] normals = extractNormals(vertices);
		
		int[] indices = sortIndices(triangles);
		float[] colors = getColors(vertices);
		
		return Engine.getRenderer().getLoader().loadToVAO(position, colors, normals, indices);
	}
	
	public static int[] sortIndices(List<Triangle> triangles) {
		int[] indices = new int[triangles.size() * 3 + 1];
		int vertexPointer = 0;
		for(int i = 0; i < triangles.size(); i++, vertexPointer+=3) {
			Triangle triangle = triangles.get(i);
			indices[vertexPointer] = triangle.getV1(); 
			indices[vertexPointer+1] = triangle.getV2();
			indices[vertexPointer+2] = triangle.getV3();
		}
		return indices;
	}

	private static float[] extractNormals(List<Vertex> vertices) {
		float[] normals = new float[vertices.size() * 3 + 1];
		int vertexPointer = 0;
		for(int i = 0; i < vertices.size(); i++, vertexPointer+=3) {
			Vertex vertex = vertices.get(i);
			normals[vertexPointer] = vertex.getNormal().x; 
			normals[vertexPointer+1] = vertex.getNormal().y;
			normals[vertexPointer+2] = vertex.getNormal().z;
		}
		return normals;
	}

	public static float[] buildVertices(List<Vertex> vertices) {
		float[] positions = new float[vertices.size() * 3 + 1];
		int vertexPointer = 0;
		for(int i = 0; i < vertices.size(); i++, vertexPointer+=3) {
			Vertex vertex = vertices.get(i);
			positions[vertexPointer] = vertex.getPosition().x; 
			positions[vertexPointer+1] = vertex.getPosition().y;
			positions[vertexPointer+2] = vertex.getPosition().z;
		}
		return positions;
	}
	
	private static float[] getColors(List<Vertex> vertices) {
		float[] indices = new float[vertices.size() * 3 + 1];
		int vertexPointer = 0;
		for(int i = 0; i < vertices.size(); i++, vertexPointer+=3) {
			Color color = vertices.get(i).getVertexColor();
			indices[vertexPointer] = color.getR();
			indices[vertexPointer+1] = color.getG();
			indices[vertexPointer+2] = color.getB();
		}
		return indices;
	}
	
}
