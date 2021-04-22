package survivalGame.main.states;

import seaSaltedEngine.Engine;
import survivalGame.GameManager;
import survivalGame.main.GameState;
import survivalGame.networking.server.Server;
import survivalGame.world.GameWorld;
import survivalGame.world.TerrainGenerator;
import survivalGame.world.skybox.SkyboxRenderer;

public class MainGame extends GameState {

	@Override
	public void init() {
		//Start Game
		GameManager.initGame();
		SkyboxRenderer.init();
		
		//Server Process
//		Server.open(25565);
//		ClientsideManager.addHost(JOptionPane.showInputDialog("Please Enter a name"));
	}

	@Override
	public void deinit() {
		//Ending Session
		Server.stopServer();
	}

	@Override
	public void update() {
		//Game Related Things
		GameWorld.update();
		Server.updateServer();
	}

	@Override
	public void render() {
		//Render Objects
		Engine.render(GameWorld.getMainWorldEntityBatch());
		TerrainGenerator.getManager().renderChunks();
		SkyboxRenderer.renderSkybox();
		Engine.renderUi();
	}

}
