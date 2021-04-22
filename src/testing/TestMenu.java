package testing;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.guis.Listener;
import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.transitions.Transition;
import seaSaltedEngine.guis.transitions.drivers.SlideDriver;
import seaSaltedEngine.tools.math.Vector2f;

public class TestMenu extends UiComponent implements Listener {

	private float TIME = 100f;
	
	private Transition SLIDE_IN;
	private Transition SLIDE_OUT;
	
	public TestMenu() {
		super(1);
	}
	
	public void init() {
		this.setColor(UiColors.GRAY);
		this.setScale(new Vector2f(0.2f,0.75f));
		this.setPosition(new Vector2f(0,0));
		this.increaseAlpha(-1);
		Engine.getInputHandler().getKeyboardInstance().getEvent().addListener(this);
		
		TestButton comp = new TestButton();
		comp.setColor(UiColors.BLACK);
		
		this.addComponent(comp);
	}
	
	@Override 
	public void updateSelf() {
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_TAB)) { 
			doPopUp();
		} else if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			reverse();
		}
	}
	
	public void doPopUp() {
		if(this.getAlpha() > 0)
			return;
		this.SLIDE_IN = new Transition().alphaDriver(new SlideDriver(0,1,TIME)).xDriver(new SlideDriver(-1.5f,-0.75f,TIME));
		this.getAnimator().doTransition(SLIDE_IN);
	}
	
	public void reverse() {
		if(this.getAlpha() < 1)
			return;
		this.SLIDE_OUT = new Transition().alphaDriver(new SlideDriver(1,0,TIME)).xDriver(new SlideDriver(-0.75f,-1.5f,TIME));
		this.getAnimator().doTransition(SLIDE_OUT);
	}

	@Override
	public void notify(String update) {
		
	}

	@Override
	public void cancel() {
		//Cancel Listening
	}

}
