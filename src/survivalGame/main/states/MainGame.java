package survivalGame.main.states;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.collision.PhysicsManager;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.GameManager;
import survivalGame.entity.EntityMapleTree;
import survivalGame.entity.EntityPickaxeTest;
import survivalGame.entity.core.EntityIdentifier;
import survivalGame.entity.core.EntityType;
import survivalGame.main.GameState;
import survivalGame.networking.client.ClientsideManager;
import survivalGame.networking.server.Server;
import survivalGame.resources.Models;
import survivalGame.resources.modding.loading.ModLoader;
import survivalGame.world.GameWorld;
import survivalGame.world.skybox.SkyboxRenderer;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.WorldGenerator;
import survivalGame.world.terrain.render.TerrainRenderer;

public class MainGame extends GameState {
	
	private TerrainRenderer chunkRenderer;
	private PhysicsManager physics;
	
	@Override
	public void init() {
		//Start Game
		GameManager.initGame();
//		ModLoader.loadMods();
		SkyboxRenderer.init();
		Mouse.setMouseVisible(false);
		WorldGenerator.generateWorld();
		
		for(int i = 0; i > 100; i++) {
			Entity entity = new EntityPickaxeTest(new Transform(new Vector3f(i,0,0),0,0,0));
			GameWorld.getMainWorldEntityBatch().getEntities().add(entity);
		}
		
		//Init Variables in class
		chunkRenderer = new TerrainRenderer();
		physics = new PhysicsManager();
		
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
		chunkRenderer.renderChunks();
		SkyboxRenderer.renderSkybox();
		Engine.renderUi();
		physics.updateTest();
	}

}
