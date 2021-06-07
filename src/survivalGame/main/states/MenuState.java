package survivalGame.main.states;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.audio.management.AudioMaster;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.basic.input.keybinding.Controls;
import seaSaltedEngine.guis.text.TextMaster;
import survivalGame.guis.GameMenus;
import survivalGame.main.GameState;
import survivalGame.resources.Sounds;
import survivalGame.world.GameWorld;
import testing.ScreenshotUtils;

public class MenuState extends GameState {
	
	@Override
	public void init() {
		Mouse.setMouseVisible(true);
		
		TextMaster.init();
		AudioMaster.init();
		Sounds.init();
		Controls.initControls();
		GameWorld.initialize();
		GameMenus.init();
	}

	@Override
	public void deinit() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		Engine.prepare();
		Engine.render(GameWorld.getMainWorldEntityBatch());
		Engine.renderUi();
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_F2))
			ScreenshotUtils.screenshot();
	}

}
