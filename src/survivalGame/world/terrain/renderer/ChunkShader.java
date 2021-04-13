package survivalGame.world.terrain.renderer;

import seaSaltedEngine.render.shaders.Shader;
import seaSaltedEngine.render.shaders.objects.UniformMatrix4f;

public class ChunkShader extends Shader {
	
	private static final String VERTEX_FILE = "/survivalGame/world/terrain/renderer/vert.glsl";
	private static final String FRAGMENT_FILE = "/survivalGame/world/terrain/renderer/frag.glsl";
	
	protected UniformMatrix4f viewMatrix = new UniformMatrix4f("viewMatrix");
	protected UniformMatrix4f transformationMatrix = new UniformMatrix4f("transformationMatrix");
	protected UniformMatrix4f projectionMatrix = new UniformMatrix4f("projectionMatrix");
	
	private int location_color;
	
	public ChunkShader() {
		super(VERTEX_FILE, FRAGMENT_FILE, "in_position", "in_vertexColor", "in_normal");
		super.storeAllUniformLocations(viewMatrix, transformationMatrix, projectionMatrix); 
	}

	public static String getVertexFile() {
		return VERTEX_FILE;
	}

	public static String getFragmentFile() {
		return FRAGMENT_FILE;
	}

	public UniformMatrix4f getViewMatrix() {
		return viewMatrix;
	}

	public UniformMatrix4f getTransformationMatrix() {
		return transformationMatrix;
	}

	public UniformMatrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public int getLocation_color() {
		return location_color;
	}
	
}
