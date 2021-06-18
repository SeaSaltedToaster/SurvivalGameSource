package survivalGame;

import seaSaltedEngine.Engine;
import seaSaltedEngine.EngineConfigs;
import seaSaltedEngine.basic.logger.LoggerType;
import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.render.display.Window;
import survivalGame.main.GameState;
import survivalGame.main.states.MenuState;

public class MainApp {

	private static GameState gameState;
	
	public static void main(String[] args) {
		EngineConfigs configs = getConfigs();
		Engine.init(configs);
		
		gameState = new MenuState();
		gameState.init();
		
		while(!Window.shouldClose()) {
			gameState.update();
			gameState.render();
			
			Engine.update();
		}
		
		gameState.deinit();
		Engine.end();
	}
	
	private static EngineConfigs getConfigs() {
		EngineConfigs configs = new EngineConfigs();
		configs.windowName = "Survival Game v0.3.1";
		configs.loggerType = LoggerType.BOTH;
		configs.defaultColor = new Color(1f,1f,1f);
		configs.fpsCap = 512;
		configs.resourceFolder = "/res/";
		return configs;
	}
	
	public static void loadGameState(GameState state) {
		gameState.deinit();
		state.init();
		gameState = state;
	}
	
}