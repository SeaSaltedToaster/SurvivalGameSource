package survivalGame.main.states;

import java.util.UUID;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.audio.management.AudioMaster;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.collision.PhysicsManager;
import survivalGame.entity.EntityPlayer;
import survivalGame.guis.GameMenus;
import survivalGame.guis.chat.UiChat;
import survivalGame.main.GameState;
import survivalGame.network.Client;
import survivalGame.network.ClientNetSetup;
import survivalGame.resources.modding.loading.ModLoader;
import survivalGame.world.GameWorld;
import survivalGame.world.generation.WorldGenerator;
import survivalGame.world.skybox.SkyboxRenderer;
import survivalGame.world.terrain.renderer.TerrainRenderer;
import testing.ScreenshotUtils;

public class MainGame extends GameState {
	
	private static EntityPlayer playerEntitiy;
	private static Client netClient;
	
	@Override
	public void init() {
		//Start Game
		Mouse.setMouseVisible(false);
		ModLoader.loadMods();
		SkyboxRenderer.init();
		PhysicsManager.init();
		WorldGenerator.generateWorld();
		GameMenus.setInMenu(false);
		
		String username = "SeaSaltedToaster";
		MainGame.netClient = ClientNetSetup.registerClientData(username, UUID.randomUUID());
		UiChat.sendChatMessage("User client finished setup", "Server");
		
		MainGame.playerEntitiy = new EntityPlayer(Transform.Default, username);
		GameWorld.getMainWorldEntityBatch().getEntities().add(playerEntitiy);
	}

	@Override
	public void deinit() {
		AudioMaster.cleanUp();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		//Render Objects
		Engine.prepare();
		Engine.render(GameWorld.getMainWorldEntityBatch());
		TerrainRenderer.renderChunks();
		SkyboxRenderer.renderSkybox();
		Engine.renderUi();
		WorldGenerator.generateTerrain(Engine.getCamera().getPosition());
		AudioMaster.getListenerData(Engine.getCamera().getPosition());
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_F2))
			ScreenshotUtils.screenshot();
	}

	public static EntityPlayer getPlayerEntitiy() {
		return playerEntitiy;
	}

	public static Client getNetClient() {
		return netClient;
	}

}
