package seaSaltedEngine.guis.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.statistics.Debugger;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.core.UiQuadModel;
import seaSaltedEngine.render.display.WindowManager;
import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Matrix4f;
import seaSaltedEngine.tools.math.Vector2f;

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
		Debugger.report("Draw_Call");
		end(component);
	}
	
	private void begin() {
		shader.start();
		quad.getMeshVao().bind(0);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private void end(UiComponent component) {
		quad.getMeshVao().unbind(0);
		shader.stop();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);	
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	private void loadShaderVariables(UiComponent component) {
		loadTexture(component);
		testScissor(component);
		shader.getTransformationMatrix().loadMatrix(getTransformation(component));
		shader.getUiOverrideColor().loadVec4(component.getOverrideColor());
		shader.getAlpha().loadFloat(component.getAlpha());
		shader.getWidth().loadFloat(component.getScale().x);
		shader.getHeight().loadFloat(component.getScale().y);
	}
	
	private void loadTexture(UiComponent component) {
		if(component.hasTexture() && component.getGuiTexture() != null) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			component.getGuiTexture().bind();
		}
		shader.getHasTexture().loadBoolean(component.hasTexture());
	}
	
	private void testScissor(UiComponent component) {
		if(component.getParentComponent() == null) return;
		long windowID = Engine.getWindowInstance().getWindowID();
		Vector2f displaySize = new Vector2f((float)WindowManager.getWindowSizeX(windowID), (float)WindowManager.getWindowSizeY(windowID));
		if(component.getParentComponent().isScissor()) {
			UiComponent parent = component.getParentComponent();
			int x = Math.round(parent.getPosition().x * displaySize.x);
			GL11.glScissor((int) (x - (parent.getScale().x/2)) ,(int) Math.round( (parent.getPosition().y + parent.getScale().y) *displaySize.y) ,
					(int) Math.round(parent.getScale().x*displaySize.x) ,(int) Math.round(parent.getScale().y*displaySize.y) );
		}
	}
	
	private Matrix4f getTransformation(UiComponent component) {
		Matrix4f transform = MathUtils.createTransformationMatrix(component.getPosition(), component.getScale());
		return transform;
	}
	
}
