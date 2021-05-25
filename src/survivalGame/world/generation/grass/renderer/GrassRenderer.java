package survivalGame.world.generation.grass.renderer;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.render.batch.IBatch;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Matrix4f;
import survivalGame.entity.world.EntityGrass;
import survivalGame.world.generation.grass.component.GrassModelComponent;

public class GrassRenderer {
	
	private GrassShader shader;
	
	public GrassRenderer() {
		shader = new GrassShader();
	}
	
	public void render(IBatch batch) {
		beginRendering();
		List<Entity> entityList = batch.getEntities();
		for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
		    Entity entity = iterator.next();
		    if(!entity.hasComponent("Model_Grass")) continue;
		    loadGrassVariables(entity);
			renderModel(entity);
		}
		endRendering();
	}
	
	private void loadGrassVariables(Entity entity) {
		EntityGrass gEntity = (EntityGrass) entity;
		shader.getTransformationMatrix().loadMatrix(getTransformation(entity.getTransform()));
		shader.getGrassColor().loadVec3(gEntity.getGrassColor().toVector());
	}

	private void beginRendering() {
		shader.start();
		shader.getViewMatrix().loadMatrix(MathUtils.createViewMatrix(Engine.getCamera()));
		shader.getProjectionMatrix().loadMatrix(Engine.getRenderer().getProjectionMatrix());
		shader.getTime().loadFloat((float)GLFW.glfwGetTime());
		OpenGL.setDepthTest(true);
	}
	
	private void endRendering() {
		shader.stop();
		OpenGL.disableCull();
	}
	
	private void renderModel(Entity entity) {
		GrassModelComponent component = (GrassModelComponent) entity.getComponent("Model_Grass");
		component.getMesh().getMeshVao().render();
	}
 	
	private Matrix4f getTransformation(Transform transform) {
		Matrix4f transformation = MathUtils.createTransformationMatrix(transform.getPosition(), transform.getRx(), transform.getRy(), transform.getRz(), 1);
		return transformation;
	}

}
