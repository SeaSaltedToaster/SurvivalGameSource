package seaSaltedEngine.tools;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.objects.Triangle;

public class Triangulator {

	public static List<Triangle> triangulateIndices(List<Integer> indices) {
		List<Triangle> triangles = new ArrayList<Triangle>();
		int indiceIndex = 0;
		for(int i = 0; i < indices.size(); i+=3, indiceIndex+=3) {
			int vertex1 = indices.get(indiceIndex);
			int vertex2 = indices.get(indiceIndex+1);
			int vertex3 = indices.get(indiceIndex+2);
			Triangle triangle = new Triangle(vertex1, vertex2, vertex3);
			triangles.add(triangle);
		}
		return triangles;
	}
	
}
