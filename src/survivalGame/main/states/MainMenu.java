package survivalGame.main.states;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Transform;
import survivalGame.GameManager;
import survivalGame.entity.EntityPickaxeTest;
import survivalGame.main.GameState;
import survivalGame.world.GameWorld;
import testing.TestMenu;

public class MainMenu extends GameState {

	@Override
	public void init() {
		MainMenu menu = new MainMenu();
//		menu.init();
		
		GameWorld.initialize();
		GameManager.initGame();
		GameWorld.getMainWorldEntityBatch().add(new EntityPickaxeTest(Transform.Default));
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
	}

}
