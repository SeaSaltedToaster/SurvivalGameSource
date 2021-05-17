package seaSaltedEngine.guis.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

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
		end(component);
	}
	
	private void begin() {
		shader.start();
		quad.getMeshVao().bind(0);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private void end(UiComponent component) {
		quad.getMeshVao().unbind(0);
		shader.stop();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);	
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	private void loadShaderVariables(UiComponent component) {
		if(component.hasTexture()) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			component.getGuiTexture().bind();
//			shader.getGuiTexture().loadTexUnit(component.getGuiTexture().getID());
		}
		shader.getTransformationMatrix().loadMatrix(getTransformation(component));
		shader.getUiOverrideColor().loadVec4(component.getOverrideColor());
		shader.getHasTexture().loadBoolean(component.hasTexture());
		shader.getAlpha().loadFloat(component.getAlpha());
		shader.getWidth().loadFloat(component.getScale().x);
		shader.getHeight().loadFloat(component.getScale().y);
	}
	
	private Matrix4f getTransformation(UiComponent component) {
		Matrix4f transform = MathUtils.createTransformationMatrix(component.getPosition(), component.getScale());
		return transform;
	}
	
}
