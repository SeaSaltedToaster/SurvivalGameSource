package survivalGame.world.terrain.renderer;

import seaSaltedEngine.render.shaders.Shader;
import seaSaltedEngine.render.shaders.objects.UniformFloat;
import seaSaltedEngine.render.shaders.objects.UniformMatrix4f;
import seaSaltedEngine.render.shaders.objects.UniformVec3;

public class TerrainShader extends Shader {
	
	private static final String VERTEX_FILE = "/survivalGame/world/terrain/renderer/vert.glsl";
	private static final String FRAGMENT_FILE = "/survivalGame/world/terrain/renderer/frag.glsl";
	
	protected UniformMatrix4f viewMatrix = new UniformMatrix4f("viewMatrix");
	protected UniformMatrix4f transformationMatrix = new UniformMatrix4f("transformationMatrix");
	protected UniformMatrix4f projectionMatrix = new UniformMatrix4f("projectionMatrix");
	protected UniformVec3 lightPosition = new UniformVec3("lightPosition");
	protected UniformVec3 lightAttenuation = new UniformVec3("lightAttenuation");
	protected UniformFloat lightDistance = new UniformFloat("lightDistance");
	
	private int location_color;
	
	public TerrainShader() {
		super(VERTEX_FILE, FRAGMENT_FILE, "in_position", "in_vertexColor", "in_normal");
		super.storeAllUniformLocations(viewMatrix, transformationMatrix, projectionMatrix, lightPosition, lightAttenuation, lightDistance); 
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

	public UniformVec3 getLightPosition() {
		return lightPosition;
	}

	public UniformVec3 getLightAttenuation() {
		return lightAttenuation;
	}

	public UniformFloat getLightDistance() {
		return lightDistance;
	}

}
