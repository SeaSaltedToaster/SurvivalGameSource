package survivalGame;

import seaSaltedEngine.Engine;
import seaSaltedEngine.EngineConfigs;
import seaSaltedEngine.basic.logger.LoggerType;
import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.render.display.Window;
import survivalGame.main.GameState;
import survivalGame.main.states.MainGame;

public class MainApp {

	private static GameState gameState;
	
	public static void main(String[] args) {
		
		EngineConfigs configs = getConfigs();
		Engine.init(configs);
		
		gameState = new MainGame();
		gameState.init();
		
		while(!Window.shouldClose()) {
			gameState.update();
			gameState.render();
			
			Engine.update();
		}
		gameState.deinit();
	}
	
	private static EngineConfigs getConfigs() {
		EngineConfigs configs = new EngineConfigs();
		configs.windowName = "Survival Game";
		configs.loggerType = LoggerType.BOTH;
		configs.defaultColor = new Color(1f,1f,1f);
		configs.fpsCap = 256;
		return configs;
	}
	
	public static void loadGameState(GameState state) {
		gameState.deinit();
		state.init();
		gameState = state;
	}
	
}
