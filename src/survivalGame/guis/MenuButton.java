package survivalGame.guis;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.transitions.Transition;
import seaSaltedEngine.guis.transitions.drivers.SlideDriver;

public class MenuButton extends UiComponent {
	
	private final float TIME = 100f;
	private Transition SLIDE_APPEAR = new Transition().alphaDriver(new SlideDriver(0,0.9f,TIME)).xDriver(new SlideDriver(-2f,-0.65f,TIME));
	private Transition SLIDE_DISAPPEAR = new Transition().alphaDriver(new SlideDriver(0.9f,0f,TIME)).xDriver(new SlideDriver(-0.65f,-2f,TIME));

	public MenuButton(int number) {
		super(1);
		
		this.setColor(UiColors.BLACK);
		this.setScale(0.2f, 0.12f);
		this.getAnimator().doTransition(SLIDE_APPEAR);
	}
	
	@Override
	public void onClick() {
		this.getAnimator().doTransition(SLIDE_DISAPPEAR);
	}

}
