package seaSaltedEngine;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.basic.camera.Camera;
import seaSaltedEngine.basic.camera.FirstPersonCamera;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.statistics.Debugger;
import seaSaltedEngine.guis.core.UiMaster;
import seaSaltedEngine.guis.renderer.UiRenderer;
import seaSaltedEngine.render.MasterRenderer;
import seaSaltedEngine.render.batch.IBatch;
import seaSaltedEngine.render.display.Window;
import seaSaltedEngine.render.model.texture.TextureLoader;
import seaSaltedEngine.render.resourceManagement.GlRequestProcessor;
import seaSaltedEngine.render.resourceManagement.main.MainRequestProcessor;

public class Engine {

	private static EngineConfigs configs;
	private static Logger logger;
	
	private static Window windowInstance;
	private static InputHandler inputHandler;
	
	private static MasterRenderer renderer; 
	private static UiRenderer uiRenderer;
	private static Camera camera;
	
	public static void init(EngineConfigs configs) {
		Engine.configs = configs;
		Engine.windowInstance = Window.newInstance(configs.windowName);
		Engine.inputHandler = new InputHandler(windowInstance);
		Engine.logger = new Logger(configs.loggerType);
		Engine.camera = new FirstPersonCamera();
		Engine.renderer = new MasterRenderer();
		Engine.uiRenderer = new UiRenderer();
		
		TextureLoader.init();
		GlRequestProcessor.init();
		Debugger.init();
		
		Logger.Log("Engine Load time: "+GLFW.glfwGetTime());
	}
	
	public static void prepare() {
		renderer.clearOpenGL();
	}
	
	public static void update() {
		camera.update();
		inputHandler.pollInput();
		MainRequestProcessor.dealWithTopRequests();
		Debugger.clear();
		windowInstance.update(); 
	} 
	
	public static void render(IBatch batch) { 
		renderer.render(batch);
	}
	
	public static void renderUi() {
		UiMaster.update();
	}
	
	public static void end() {
		TextureLoader.deleteTextures();
		GlRequestProcessor.end();
	}


	public static EngineConfigs getConfigs() {
		return configs;
	}


	public static Window getWindowInstance() {
		return windowInstance;
	}


	public static Logger getLogger() {
		return logger;
	}


	public static InputHandler getInputHandler() {
		return inputHandler;
	}


	public static MasterRenderer getRenderer() {
		return renderer;
	}


	public static Camera getCamera() {
		return camera;
	}


	public static UiRenderer getUiRenderer() {
		return uiRenderer;
	}
	
}
