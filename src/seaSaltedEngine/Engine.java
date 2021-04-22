package seaSaltedEngine;

import seaSaltedEngine.basic.camera.Camera;
import seaSaltedEngine.basic.camera.FirstPersonCamera;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.guis.core.UiMaster;
import seaSaltedEngine.guis.renderer.UiRenderer;
import seaSaltedEngine.render.MasterRenderer;
import seaSaltedEngine.render.batch.IBatch;
import seaSaltedEngine.render.display.Window;
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
		Engine.renderer = new MasterRenderer();
		Engine.camera = new FirstPersonCamera();
		Engine.uiRenderer = new UiRenderer();
		
		GlRequestProcessor.init();
	}
	
	
	public static void update() {
		camera.update();
		inputHandler.pollInput();
		windowInstance.update(); 
		MainRequestProcessor.dealWithTopRequests();
	} 
	
	public static void render(IBatch batch) { 
		renderer.render(batch);
	}
	
	public static void renderUi() {
		UiMaster.update();
	}
	
	public static void end() {
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
