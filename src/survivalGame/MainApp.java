package survivalGame;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.EngineConfigs;
import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.logger.LoggerType;
import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.ModelComponent;
import seaSaltedEngine.render.display.Window;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.main.GameState;
import survivalGame.main.states.MainGame;
import survivalGame.resources.Models;
import survivalGame.world.GameWorld;
import testing.TestMenu;

public class MainApp {

	public static void main(String[] args) {
		EngineConfigs configs = getConfigs();
		Engine.init(configs);
		
		Logger.Log("Engine Load time: "+GLFW.glfwGetTime());
		
		GameState gameState = new MainGame();
		gameState.init();
		
		TestMenu menu = new TestMenu();
		menu.init();
		
		Logger.Log("Game Load time: "+GLFW.glfwGetTime());
		
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
		configs.defaultColor = new Color(1,1,1);
		configs.fpsCap = 1000;
		return configs;
	}
	
	public static void addEntityAt(Vector3f position) {
		Transform transform = new Transform(position,0,0,0);
		Entity entity = new Entity(transform);
		entity.addComponent(new ModelComponent(Models.getModelFromID(1), 1));
		
		GameWorld.getMainWorldEntityBatch().getEntities().add(entity);
	}
	
}
