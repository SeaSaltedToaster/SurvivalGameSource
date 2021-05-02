package survivalGame.world.terrain.mesh.tools;

import seaSaltedEngine.basic.objects.Triangle;
import seaSaltedEngine.basic.objects.Vertex;

public class ComplexTriangle extends Triangle {

	private Vertex a,b,c;
	
	public ComplexTriangle(int v1, int v2, int v3) {
		super(v1, v2, v3);
	}

	public Vertex getA() {
		return a;
	}

	public Vertex getB() {
		return b;
	}

	public Vertex getC() {
		return c;
	}

	public void setA(Vertex a) {
		this.a = a;
	}

	public void setB(Vertex b) {
		this.b = b;
	}

	public void setC(Vertex c) {
		this.c = c;
	}

}
