package seaSaltedEngine.render.renderers;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.basic.statistics.Debugger;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.Component;
import seaSaltedEngine.entity.component.ModelComponent;
import seaSaltedEngine.render.batch.IBatch;
import seaSaltedEngine.render.resourceManagement.frustumCulling.FrustumCuller;
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
		//Start and load const
		if(batch == null || batch.getEntities().size() < 1) return;
		beginRendering();
		
		//Create transformation array
		Matrix4f[] transformations = new Matrix4f[batch.getEntities().size()];
		List<Entity> entityList = batch.getEntities();
		for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
			Entity entity = iterator.next();
		    if(entity == null || altersRender(entity)) continue;
			int i = batch.getEntities().indexOf(entity);
			transformations[i] = getTransformation(entity.getTransform());
		}
		shader.getTransformationMatrix().loadMatrixArray(transformations);
		
		//Render and report to debugger TODO render all if model is same
		renderModel(batch.getEntities().get(0), batch.getEntities().size());
		Debugger.report("Draw_Call");
		
		endRendering();
	}
	
	private boolean altersRender(Entity entity) {
		if(entity == null || entity.getComponents().size() < 1) return false;
		for(Component component : entity.getComponents()) {
			if(component.changesRenderer())
		    	return true;
			if(component.getComponentType().equalsIgnoreCase("FrustumCull"))
				return FrustumCuller.checkRender(entity);
		}
		return false;
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
		ModelComponent component = (ModelComponent) entity.getComponent("Model");
		component.getMesh().getMeshVao().bind(0,1,2);
		GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, component.getMesh().getMeshVao().getIndexCount(), GL11.GL_UNSIGNED_INT, 0, count);
		component.getMesh().getMeshVao().unbind(0,1,2);
	}
 	
	private Matrix4f getTransformation(Transform transform) {
		Matrix4f transformation = MathUtils.createTransformationMatrix(transform.getPosition(), transform.getRx(), transform.getRy(), transform.getRz(), 1);
		return transformation;
	}
	
}
