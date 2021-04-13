package seaSaltedEngine.guis.core;

import seaSaltedEngine.Engine;
import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.MeshData;
import seaSaltedEngine.render.model.Vao;

public class UiQuadModel {

	public static Mesh getQuadMesh() {
		float[] positions = 
			{-1,1, -1, -1, 1, 1, 1, -1};
		Vao vao = Engine.getRenderer().getLoader().loadToVAO(positions, 2);
		
		return new Mesh(new MeshData(vao));
	}
	
}
