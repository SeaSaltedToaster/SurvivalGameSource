package survivalGame.main.states;

import seaSaltedEngine.Engine;
import seaSaltedEngine.guis.core.UiMaster;
import survivalGame.guis.splashScreen.SplashBackground;
import survivalGame.main.GameState;

public class SplashScreen extends GameState {

	@Override
	public void init() {
		UiMaster.add(new SplashBackground());
	}

	@Override
	public void deinit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		Engine.prepare();
		Engine.renderUi();
	}

}
