package survivalGame.world.skybox;

import seaSaltedEngine.render.shaders.Shader;
import seaSaltedEngine.render.shaders.objects.UniformMatrix4f;

public class SkyboxShader extends Shader {
	
	private static final String VERTEX_FILE = "/survivalGame/world/skybox/vert.glsl";
	private static final String FRAGMENT_FILE = "/survivalGame/world/skybox/frag.glsl";
	
	protected UniformMatrix4f viewMatrix = new UniformMatrix4f("viewMatrix");
	protected UniformMatrix4f transformationMatrix = new UniformMatrix4f("transformationMatrix");
	protected UniformMatrix4f projectionMatrix = new UniformMatrix4f("projectionMatrix");

	public SkyboxShader() {
		super(VERTEX_FILE, FRAGMENT_FILE, "in_position");
		super.storeAllUniformLocations(transformationMatrix, projectionMatrix, viewMatrix);
	}

	public static String getVertexFile() {
		return VERTEX_FILE;
	}

	public static String getFragmentFile() {
		return FRAGMENT_FILE;
	}

	public UniformMatrix4f getTransformationMatrix() {
		return transformationMatrix;
	}

	public UniformMatrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public UniformMatrix4f getViewMatrix() {
		return viewMatrix;
	}

}
