package survivalGame.world.skybox;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.loaders.WavefrontLoader;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Vector3f;

public class SkyboxRenderer {
	
	private static SkyboxShader shader;
	private static Mesh cubeMesh;
	
	private static final float SIZE = 1000f;
	
	public static void init() {
		shader = new SkyboxShader();
		cubeMesh = WavefrontLoader.loadObjModel("assets/skybox");
		shader.start();
		shader.getProjectionMatrix().loadMatrix(Engine.getRenderer().getProjectionMatrix());
		shader.stop();
	}
	
	public static void renderSkybox() {
		OpenGL.enableFrontCull();
		beginRendering();
		prepareInstance();
		cubeMesh.getMeshVao().render();
		finishRendering();
		OpenGL.disableFrontCull();
	}
	
	private static void beginRendering() {
		shader.start();
		shader.getSkyboxSize().loadFloat(SIZE);
		shader.getViewMatrix().loadMatrix(MathUtils.createViewMatrix(Engine.getCamera()));
	}
	
	private static void finishRendering() {
		shader.stop();
	}
	
	private static void prepareInstance() {
		shader.getTransformationMatrix().loadMatrix(MathUtils.createTransformationMatrix(new Vector3f(0,0,0), 0, 0, 0, 1000));
	}

}
