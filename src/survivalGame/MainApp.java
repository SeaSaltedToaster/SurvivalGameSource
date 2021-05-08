package survivalGame;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.EngineConfigs;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.logger.LoggerType;
import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.render.display.Window;
import survivalGame.main.GameState;
import survivalGame.main.states.MainGame;
import testing.ScreenshotUtils;

public class MainApp {

	private static GameState gameState;
	
	public static void main(String[] args) {
		EngineConfigs configs = getConfigs();
		Engine.init(configs);
		
		Logger.Log("Engine Load time: "+GLFW.glfwGetTime());
		
		gameState = new MainGame();
		gameState.init();
		
		Logger.Log("Game Load time: "+GLFW.glfwGetTime());
		
		while(!Window.shouldClose()) {
			gameState.update();
			gameState.render();
			
			if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_F2))
				ScreenshotUtils.screenshot();
			
			Engine.update();
		}
		gameState.deinit();
	}
	
	private static EngineConfigs getConfigs() {
		EngineConfigs configs = new EngineConfigs();
		configs.windowName = "Survival Game";
		configs.loggerType = LoggerType.BOTH;
		configs.defaultColor = new Color(1f,1f,1f);
		configs.fpsCap = 100;
		return configs;
	}
	
	public static void loadGameState(GameState state) {
		gameState.deinit();
		state.init();
		gameState = state;
	}
	
}
