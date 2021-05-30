package seaSaltedEngine.render;

import seaSaltedEngine.Engine;
import seaSaltedEngine.EngineConstants;
import seaSaltedEngine.render.batch.IBatch;
import seaSaltedEngine.render.model.loaders.ModelLoader;
import seaSaltedEngine.render.renderers.StaticRenderer;
import seaSaltedEngine.render.resourceManagement.frustumCulling.FrustumCuller;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Matrix4f;

public class MasterRenderer {

	private StaticRenderer renderer;
	private ModelLoader loader;
	
	private Matrix4f projectionMatrix;
	private FrustumCuller culler;
	
	public MasterRenderer() {
		this.renderer = new StaticRenderer();
		this.loader = new ModelLoader();
		this.projectionMatrix = MathUtils.createProjectionMatrix(EngineConstants.FOV, EngineConstants.NEAR_PLANE, EngineConstants.FAR_PLANE);
		this.culler = new FrustumCuller();
	}
	 
	public void render(IBatch batch) {
		if(batch == null) return;
		culler.update();
;		renderer.render(batch); 
	}
	
	public void clearOpenGL() { 
		OpenGL.clearColor();
		OpenGL.clearDepth();
		OpenGL.clearColor(Engine.getConfigs().getDefaultColor().toVector(), 1);
	}
	
	public void clean() {
		loader.cleanUp();
	}

	public ModelLoader getLoader() {
		return loader;
	}

	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public StaticRenderer getRenderer() {
		return renderer;
	}

	public FrustumCuller getCuller() {
		return culler;
	}
	
}
