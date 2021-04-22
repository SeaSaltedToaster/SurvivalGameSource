package survivalGame.world.skybox;

import org.lwjgl.opengl.GL11;
import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.MeshData;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.MathUtils;

public class SkyboxRenderer {
	
	private static SkyboxShader shader;
	private static Mesh cubeMesh;
	
	public static void init() {
		shader = new SkyboxShader();
		cubeMesh = new Mesh(new MeshData(Engine.getRenderer().getLoader().loadToVAO(VERTICES, 3)));
		shader.start();
		shader.getProjectionMatrix().loadMatrix(Engine.getRenderer().getProjectionMatrix());
		shader.stop();
	}
	
	public static void renderSkybox() {
		beginRendering();
		prepareInstance();
		OpenGL.disableCull();
		cubeMesh.getMeshVao().render();
		finishRendering();
	}
	
	private static void beginRendering() {
		GL11.glDepthMask(false);
		shader.start();
		shader.getViewMatrix().loadMatrix(MathUtils.createViewMatrix(Engine.getCamera()));
		shader.getProjectionMatrix().loadMatrix(Engine.getRenderer().getProjectionMatrix());
	}
	
	private static void finishRendering() {
		shader.stop();
		GL11.glDepthMask(true);
	}
	
	private static void prepareInstance() {
		shader.getTransformationMatrix().loadMatrix(MathUtils.createTransformationMatrix(Transform.Default.getPosition(), 0, 0, 0, 1));
	}
	
	private static final float SIZE = 1000f;
	private static final float[] VERTICES = {        
	    -SIZE,  SIZE, -SIZE,
	    -SIZE, -SIZE, -SIZE,
	    SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	     SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,

	    -SIZE, -SIZE,  SIZE,
	    -SIZE, -SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE,  SIZE,
	    -SIZE, -SIZE,  SIZE,

	     SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,

	    -SIZE, -SIZE,  SIZE,
	    -SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE, -SIZE,  SIZE,
	    -SIZE, -SIZE,  SIZE,

	    -SIZE,  SIZE, -SIZE,
	     SIZE,  SIZE, -SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	    -SIZE,  SIZE,  SIZE,
	    -SIZE,  SIZE, -SIZE,

	    -SIZE, -SIZE, -SIZE,
	    -SIZE, -SIZE,  SIZE,
	     SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	    -SIZE, -SIZE,  SIZE,
	     SIZE, -SIZE,  SIZE
	};

}
