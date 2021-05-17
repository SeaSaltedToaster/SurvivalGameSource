package survivalGame.world.generation.grass.renderer;

import seaSaltedEngine.render.shaders.Shader;
import seaSaltedEngine.render.shaders.objects.UniformMatrix4f;
import seaSaltedEngine.render.shaders.objects.UniformVec3;

public class GrassShader extends Shader {
	
	protected UniformMatrix4f viewMatrix = new UniformMatrix4f("viewMatrix");
	protected UniformMatrix4f transformationMatrix = new UniformMatrix4f("transformationMatrix");
	protected UniformMatrix4f projectionMatrix = new UniformMatrix4f("projectionMatrix");
	
	protected UniformVec3 grassColor = new UniformVec3("grassColor");
	
	public GrassShader() {
		super("/survivalGame/world/generation/grass/renderer/vert.glsl", "/survivalGame/world/generation/grass/renderer/frag.glsl", "in_position", "in_vertexColor", "in_normal");
		super.storeAllUniformLocations(viewMatrix, transformationMatrix, projectionMatrix, grassColor); 
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

	public UniformVec3 getGrassColor() {
		return grassColor;
	}

}
