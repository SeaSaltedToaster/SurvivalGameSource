package testing;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.guis.Listener;
import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.transitions.Transition;
import seaSaltedEngine.guis.transitions.drivers.SlideDriver;
import seaSaltedEngine.tools.math.Vector2f;

public class TestMenu extends UiComponent implements Listener {

	private float TIME = 500f;
	
	private Transition SLIDE_IN = new Transition().alphaDriver(new SlideDriver(0,1,TIME));
	private Transition SLIDE_OUT = new Transition().alphaDriver(new SlideDriver(1,0,TIME));
	
	public TestMenu() {
		super(1);
	}
	
	public void init() {
		this.setColor(UiColors.RED);
		this.setScale(new Vector2f(0.25f,0.9f));
		this.setPosition(new Vector2f(0,0));
		this.increaseAlpha(-1);
		Engine.getInputHandler().getKeyboardInstance().getEvent().addListener(this);
	}
	
	public void doPopUp() {
		this.getAnimator().doTransition(SLIDE_IN);
	}
	
	public void reverse() {
		this.getAnimator().doTransition(SLIDE_OUT);
	}

	@Override
	public void notify(String update) {
		if(update == null)
			return;
		if(update.contains("null"))
			return;
		String reduced = update.substring(0, 3).replace(";", "").replace(":", "");
		String finalString = GLFW.glfwGetKeyName(Integer.parseInt(reduced), 0);
		
		if(finalString.contains("t"))
			doPopUp();
		if(finalString.contains("y"))
			reverse();
		
		Logger.Log(this.getPosition().toString());
	}

	@Override
	public void cancel() {
		//Cancel Listening
	}

}
