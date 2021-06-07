package survivalGame.main.states;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.audio.management.AudioMaster;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.collision.PhysicsManager;
import survivalGame.GameManager;
import survivalGame.main.GameState;
import survivalGame.resources.modding.loading.ModLoader;
import survivalGame.world.GameWorld;
import survivalGame.world.generation.WorldGenerator;
import survivalGame.world.skybox.SkyboxRenderer;
import survivalGame.world.terrain.renderer.TerrainRenderer;
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
		AudioMaster.getListenerData(Engine.getCamera().getPosition());
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_F2))
			ScreenshotUtils.screenshot();
	}

}
