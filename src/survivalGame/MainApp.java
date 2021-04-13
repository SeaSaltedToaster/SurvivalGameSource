package survivalGame;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.EngineConfigs;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.logger.LoggerType;
import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.render.display.Window;
import survivalGame.main.GameState;
import survivalGame.main.states.MainGame;
import survivalGame.main.states.MainMenu;

public class MainApp {
	
	public static int total;

	public static void main(String[] args) {
		EngineConfigs configs = getConfigs();
		Engine.init(configs);
		
		Logger.Log("Engine Load time: "+GLFW.glfwGetTime());
		Mouse.setMouseVisible(false);
		
		GameState gameState = new MainGame();
		gameState.init();
		
		Logger.Log("Game Load time: "+GLFW.glfwGetTime());
		
		while(!Window.shouldClose()) {
			Engine.update();
			
			Logger.Log("Total:" + total);
			
			gameState.update();
			gameState.render();
		}
		gameState.deinit();
	}
	
	private static EngineConfigs getConfigs() {
		EngineConfigs configs = new EngineConfigs();
		configs.windowName = "Survival Game";
		configs.loggerType = LoggerType.BOTH;
		configs.defaultColor = new Color(1,1,1);
		configs.fpsCap = 60;
		return configs;
	}
	
}
