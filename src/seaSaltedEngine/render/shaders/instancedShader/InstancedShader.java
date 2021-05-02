package seaSaltedEngine.render.shaders.instancedShader;

import seaSaltedEngine.render.shaders.Shader;
import seaSaltedEngine.render.shaders.objects.UniformMat4Array;
import seaSaltedEngine.render.shaders.objects.UniformMatrix4f;

public class InstancedShader extends Shader {

	protected UniformMatrix4f viewMatrix = new UniformMatrix4f("viewMatrix");
	protected UniformMatrix4f projectionMatrix = new UniformMatrix4f("projectionMatrix");
	protected UniformMat4Array transformations = new UniformMat4Array("transformationMatrix", 10000);
	
	public InstancedShader() {
		super("/seaSaltedEngine/render/shaders/instancedShader/vert.glsl", "/seaSaltedEngine/render/shaders/instancedShader/frag.glsl", "in_position", "in_vertexColor", "in_normal");
		super.storeAllUniformLocations(viewMatrix, transformations, projectionMatrix); 
	}

	public UniformMatrix4f getViewMatrix() {
		return viewMatrix;
	}

	public UniformMat4Array getTransformationMatrix() {
		return transformations;
	}

	public UniformMatrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
}
