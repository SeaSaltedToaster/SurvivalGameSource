package seaSaltedEngine.render.renderers;

import java.util.Iterator;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.ComponentType;
import seaSaltedEngine.entity.component.ModelComponent;
import seaSaltedEngine.render.batch.IBatch;
import seaSaltedEngine.render.shaders.staticShader.StaticShader;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Matrix4f;

public class StaticRenderer {
	
	private StaticShader shader;
	
	public StaticRenderer() {
		shader = new StaticShader();
	}
	
	public void render(IBatch batch) {
		beginRendering();
		
		for (Iterator<Entity> iterator = batch.getEntities().iterator(); iterator.hasNext();) {
		    Entity entity = iterator.next();
		    shader.getTransformationMatrix().loadMatrix(getTransformation(entity.getTransform()));
			renderModel(entity);
		}

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
	
	private void renderModel(Entity entity) {
		ModelComponent component = (ModelComponent) entity.getComponent(ComponentType.MODEL);
		component.getMesh().getMeshVao().render();
	}
 	
	private Matrix4f getTransformation(Transform transform) {
		Matrix4f transformation = MathUtils.createTransformationMatrix(transform.getPosition(), transform.getRx(), transform.getRy(), transform.getRz(), 1);
		return transformation;
	}

}
