package survivalGame.world.terrain.render;

import seaSaltedEngine.basic.objects.Vertex;
import seaSaltedEngine.tools.math.Vector3f;

public class TerrainVertex extends Vertex {

	public TerrainVertex(Vector3f position, Vector3f normal, byte[] color) {
		super(position, normal);
	}

}
