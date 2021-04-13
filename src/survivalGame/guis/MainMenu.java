package survivalGame.guis;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.transitions.Transition;
import seaSaltedEngine.guis.transitions.drivers.SlideDriver;
import seaSaltedEngine.tools.math.Vector2f;

public class MainMenu extends UiComponent {

	private Transition APPEAR = new Transition().alphaDriver(new SlideDriver(0,1,50f));
	
	public MainMenu() {
		super(1);
		
		this.setColor(UiColors.GRAY);
		this.setScale(new Vector2f(1,1));
		this.setPosition(new Vector2f(0,0));
	}
	
	public void init() {
		this.setColor(UiColors.GRAY);
		this.setScale(new Vector2f(1,1));
		this.setPosition(new Vector2f(0,0));
//		this.increaseAlpha(1);
		
//		this.getAnimator().doTransition(APPEAR);
	}

}
