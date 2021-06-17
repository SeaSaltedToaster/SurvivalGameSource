package survivalGame.world.skybox;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.statistics.Debugger;
import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.loaders.OldWavefrontLoader;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Vector3f;

public class SkyboxRenderer {
	
	private static SkyboxShader shader;
	private static Mesh cubeMesh;
	
	private static final float SIZE = 10000f;
	
	public static void init() {
		shader = new SkyboxShader();
		shader.start();
		shader.getProjectionMatrix().loadMatrix(Engine.getRenderer().getProjectionMatrix());
		shader.stop();
		cubeMesh = OldWavefrontLoader.loadObjModel("assets/skybox");
	}
	
	public static void renderSkybox() {
		OpenGL.enableFrontCull();
		beginRendering();
		prepareInstance();
		cubeMesh.getMeshVao().render();
		Debugger.report("Draw_Call");
		finishRendering();
		OpenGL.disableFrontCull();
	}
	
	private static void beginRendering() {
		shader.start();
		shader.getSkyboxSize().loadFloat(SIZE);
		shader.getViewMatrix().loadMatrix(MathUtils.createViewMatrix(Engine.getCamera()));
		shader.getTime().loadFloat((float) GLFW.glfwGetTime());
	}
	
	private static void finishRendering() {
		shader.stop();
	}
	
	private static void prepareInstance() {
		Vector3f cameraPosition = Engine.getCamera().getPosition();
		shader.getTransformationMatrix().loadMatrix(MathUtils.createTransformationMatrix(new Vector3f(cameraPosition.x, 0, cameraPosition.z), 0, 0, 0, SIZE));
	}

}
