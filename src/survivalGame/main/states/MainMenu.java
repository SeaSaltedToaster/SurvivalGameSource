package survivalGame.main.states;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.transitions.Transition;
import seaSaltedEngine.guis.transitions.drivers.SlideDriver;
import survivalGame.GameManager;
import survivalGame.guis.MenuButton;
import survivalGame.main.GameState;
import survivalGame.world.GameWorld;

public class MainMenu extends GameState {

	private Transition APPEAR = new Transition().alphaDriver(new SlideDriver(0f,1f,25f));
	
	@Override
	public void init() {
		Mouse.setMouseVisible(true);
		
		UiComponent menu = new UiComponent(1);
		menu.setScale(1, 1);
		menu.setColor(UiColors.GRAY);
		menu.getAnimator().doTransition(APPEAR);
		
		MenuButton button = new MenuButton(1);
		button.setAlpha(0.9f);
		menu.addComponent(button);
		
		GameWorld.initialize();
		GameManager.initGame();
	}

	@Override
	public void deinit() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		Engine.render(GameWorld.getMainWorldEntityBatch());
		Engine.renderUi();
	}

}
