package survivalGame.main.states;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.Mouse;
import survivalGame.GameManager;
import survivalGame.main.GameState;
import survivalGame.networking.client.ClientsideManager;
import survivalGame.networking.server.Server;
import survivalGame.resources.modding.loading.ModLoader;
import survivalGame.world.GameWorld;
import survivalGame.world.TerrainGenerator;
import survivalGame.world.skybox.SkyboxRenderer;

public class MainGame extends GameState {

	@Override
	public void init() {
		//Start Game
		GameManager.initGame();
//		ModLoader.loadMods();
		SkyboxRenderer.init();
		Mouse.setMouseVisible(false);
		
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
		TerrainGenerator.getManager().renderChunks();
		SkyboxRenderer.renderSkybox();
		Engine.renderUi();
	}

}
