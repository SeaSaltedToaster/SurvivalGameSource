package seaSaltedEngine.render.shaders.staticShader;

import seaSaltedEngine.render.shaders.Shader;
import seaSaltedEngine.render.shaders.objects.UniformBoolean;
import seaSaltedEngine.render.shaders.objects.UniformFloat;
import seaSaltedEngine.render.shaders.objects.UniformMatrix4f;
import seaSaltedEngine.render.shaders.objects.UniformVec3;

public class StaticShader extends Shader {

	protected UniformMatrix4f viewMatrix = new UniformMatrix4f("viewMatrix");
	protected UniformMatrix4f transformationMatrix = new UniformMatrix4f("transformationMatrix");
	protected UniformMatrix4f projectionMatrix = new UniformMatrix4f("projectionMatrix");
	
	protected UniformVec3 lightPosition = new UniformVec3("lightPosition");
	protected UniformVec3 lightAttenuation = new UniformVec3("lightAttenuation");
	
	protected UniformBoolean sway = new UniformBoolean("sway");
	protected UniformFloat time = new UniformFloat("time");
	
	public StaticShader() {
		super("/res/assets/shaders/vert.glsl", "/res/assets/shaders/frag.glsl", "in_position", "in_vertexColor", "in_normal");
		super.storeAllUniformLocations(viewMatrix, transformationMatrix, projectionMatrix, lightPosition, lightAttenuation, sway, time); 
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

	public UniformVec3 getLightPosition() {
		return lightPosition;
	}

	public UniformVec3 getLightAttenuation() {
		return lightAttenuation;
	}

	public UniformBoolean getSway() {
		return sway;
	}
	
	public UniformFloat getTime() {
		return time;
	}
	
}
