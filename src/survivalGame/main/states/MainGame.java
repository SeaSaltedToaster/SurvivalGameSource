package survivalGame.main.states;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.collision.PhysicsManager;
import survivalGame.GameManager;
import survivalGame.main.GameState;
import survivalGame.networking.client.ClientsideManager;
import survivalGame.networking.server.Server;
import survivalGame.resources.modding.loading.ModLoader;
import survivalGame.world.GameWorld;
import survivalGame.world.skybox.SkyboxRenderer;
import survivalGame.world.terrain.WorldGenerator;
import survivalGame.world.terrain.render.TerrainRenderer;
import testing.ScreenshotUtils;

public class MainGame extends GameState {
	
	@Override
	public void init() {
		//Start Game
		Mouse.setMouseVisible(false);
		GameManager.initGame();
		ModLoader.loadMods();
		SkyboxRenderer.init();
		PhysicsManager.init();
		WorldGenerator.generateWorld();
		
		//Server Process
		Server.open(25565);
		ClientsideManager.addHost("TestUser1");
	}

	@Override
	public void deinit() {
		//Ending Session
		Server.stopServer();
	}

	@Override
	public void update() {
		//Game Related Things
		Server.updateServer();
	}

	@Override
	public void render() {
		//Render Objects
		Engine.prepare();
		Engine.render(GameWorld.getMainWorldEntityBatch());
		TerrainRenderer.renderChunks();
		SkyboxRenderer.renderSkybox();
		Engine.renderUi();
		PhysicsManager.updateTest();
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_F2))
			ScreenshotUtils.screenshot();
	}

}
