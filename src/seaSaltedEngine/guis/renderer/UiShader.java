package seaSaltedEngine.guis.renderer;

import seaSaltedEngine.render.shaders.Shader;
import seaSaltedEngine.render.shaders.objects.UniformFloat;
import seaSaltedEngine.render.shaders.objects.UniformMatrix4f;
import seaSaltedEngine.render.shaders.objects.UniformSampler;
import seaSaltedEngine.render.shaders.objects.UniformVec4;

public class UiShader extends Shader {

	protected UniformMatrix4f transformationMatrix = new UniformMatrix4f("transformationMatrix");
	protected UniformVec4 uiOverrideColor = new UniformVec4("uiOverrideColor");
	protected UniformSampler guiTexture = new UniformSampler("guiTexture");
	protected UniformFloat alpha = new UniformFloat("alpha");
	
	public UiShader() {
		super("/seaSaltedEngine/guis/renderer/vert.glsl", "/seaSaltedEngine/guis/renderer/frag.glsl", "in_position");
		super.storeAllUniformLocations(uiOverrideColor, transformationMatrix, guiTexture, alpha); 
	}
	public UniformMatrix4f getTransformationMatrix() {
		return transformationMatrix;
	}

	public UniformVec4 getUiOverrideColor() {
		return uiOverrideColor;
	}

	public UniformSampler getGuiTexture() {
		return guiTexture;
	}
	public UniformFloat getAlpha() {
		return alpha;
	}
	
}
