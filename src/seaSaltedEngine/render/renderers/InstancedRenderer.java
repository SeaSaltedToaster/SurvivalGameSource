package seaSaltedEngine.render.renderers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.ComponentType;
import seaSaltedEngine.entity.component.ModelComponent;
import seaSaltedEngine.render.batch.IBatch;
import seaSaltedEngine.render.shaders.instancedShader.InstancedShader;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Matrix4f;

public class InstancedRenderer {

	private InstancedShader shader;
	
	public InstancedRenderer() {
		shader = new InstancedShader();
	}
	
	public void render(IBatch batch) {
		beginRendering();
		
		Matrix4f[] transformations = new Matrix4f[batch.getEntities().size()];
		for(Entity entity : batch.getEntities()) {
			int i = batch.getEntities().indexOf(entity);
			transformations[i] = getTransformation(entity.getTransform());
		}
		shader.getTransformationMatrix().loadMatrixArray(transformations);
		renderModel(batch.getEntities().get(0), batch.getEntities().size());

		endRendering();
	}

	private void beginRendering() {
		shader.start();
		shader.getViewMatrix().loadMatrix(MathUtils.createViewMatrix(Engine.getCamera()));
		shader.getProjectionMatrix().loadMatrix(Engine.getRenderer().getProjectionMatrix());
		
		OpenGL.enableCull();
		OpenGL.setDepthTest(true);
	}
	
	private void endRendering() {
		shader.stop();
		
		OpenGL.disableCull();
	}
	
	private void renderModel(Entity entity, int count) {
		ModelComponent component = (ModelComponent) entity.getComponent(ComponentType.MODEL);
		component.getMesh().getMeshVao().bind(0,1,2);
		GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, component.getMesh().getMeshVao().getIndexCount(), GL11.GL_UNSIGNED_INT, 0, count);
		component.getMesh().getMeshVao().unbind(0,1,2);
	}
 	
	private Matrix4f getTransformation(Transform transform) {
		Matrix4f transformation = MathUtils.createTransformationMatrix(transform.getPosition(), transform.getRx(), transform.getRy(), transform.getRz(), 1);
		return transformation;
	}
	
}
