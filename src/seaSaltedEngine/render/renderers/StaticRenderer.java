package seaSaltedEngine.render.renderers;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.basic.statistics.Debugger;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.Component;
import seaSaltedEngine.entity.component.ModelComponent;
import seaSaltedEngine.render.batch.IBatch;
import seaSaltedEngine.render.resourceManagement.frustumCulling.FrustumCuller;
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
		List<Entity> entityList = batch.getEntities();
		for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
		    Entity entity = iterator.next();
		    entity.update();
			if(entity == null || altersRender(entity)) continue;
		    loadComponents(entity);
			renderModel(entity);
			Debugger.report("Draw_Call");
		}
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
	
	private void loadComponents(Entity entity) {
		shader.getTransformationMatrix().loadMatrix(getTransformation(entity.getTransform()));
		if(entity.hasComponent("Sway")) shader.getSway().loadBoolean(true);
		shader.getTime().loadFloat((float)GLFW.glfwGetTime());
	}

	private void beginRendering() {
		shader.start();
		shader.getViewMatrix().loadMatrix(MathUtils.createViewMatrix(Engine.getCamera()));
		shader.getProjectionMatrix().loadMatrix(Engine.getRenderer().getProjectionMatrix());
		shader.getLightPosition().loadVec3(Engine.getCamera().getPosition());
		shader.getLightAttenuation().loadVec3(1, 1, 1);
		OpenGL.enableCull();
		OpenGL.setDepthTest(true);
	}
	
	private void endRendering() {
		shader.stop();
		OpenGL.disableCull();
	}
	
	private void renderModel(Entity entity) {
		if(!entity.hasComponent("Model")) return;
		ModelComponent component = (ModelComponent) entity.getComponent("Model");
		component.getMesh().getMeshVao().render();
	}
 	
	private Matrix4f getTransformation(Transform transform) {
		Matrix4f transformation = MathUtils.createTransformationMatrix(transform.getPosition(), transform.getRx(), transform.getRy(), transform.getRz(), transform.getScale());
		return transformation;
	}

}
