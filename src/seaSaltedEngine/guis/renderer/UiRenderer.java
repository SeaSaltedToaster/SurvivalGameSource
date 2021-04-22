package seaSaltedEngine.guis.renderer;

import org.lwjgl.opengl.GL11;

import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.core.UiQuadModel;
import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Matrix4f;

public class UiRenderer {

	private UiShader shader;
	private Mesh quad;
	
	public UiRenderer() {
		shader = new UiShader();
		quad = UiQuadModel.getQuadMesh();
	}
	
	public void renderGui(UiComponent component) {
		begin();
		loadShaderVariables(component);
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		end();
	}
	
	private void loadShaderVariables(UiComponent component) {
		shader.getTransformationMatrix().loadMatrix(getTransformation(component));
		if(component.getOverrideColor() != null)
			shader.getUiOverrideColor().loadVec4(component.getOverrideColor());
		if((Integer)component.getGuiTexture() != null)
			shader.getGuiTexture().loadTexUnit(component.getGuiTexture());
		shader.getAlpha().loadFloat(component.getAlpha());
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	
	private Matrix4f getTransformation(UiComponent component) {
		Matrix4f transform = MathUtils.createTransformationMatrix(component.getPosition(), component.getScale());
		return transform;
	}
	
	private void begin() {
		shader.start();
		quad.getMeshVao().bind(0);
	}
	
	private void end() {
		quad.getMeshVao().unbind(0);
		shader.stop();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);	
		GL11.glDisable(GL11.GL_BLEND);
	}
	
}
