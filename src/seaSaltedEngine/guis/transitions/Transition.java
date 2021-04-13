package seaSaltedEngine.guis.transitions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.guis.core.UiComponent;

public class Transition {

	private HashMap<TransitionDriver, TransitionType> drivers;
	
	private double timeStarted;
	public boolean isDone = false; 
	
	public Transition() {
		this.drivers = new HashMap<TransitionDriver, TransitionType>();
	}
	
	public void start() {
		timeStarted = GLFW.glfwGetTime();
	}
	
	public void update(UiComponent component) {
		Iterator<Entry<TransitionDriver, TransitionType>> iterator = drivers.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<TransitionDriver, TransitionType> entry = iterator.next();
			updateDriver(entry.getKey(),entry.getValue(), component);
		}
	}
	
	private void updateDriver(TransitionDriver driver, TransitionType type, UiComponent component) {
		if(driver.hasCompletedOnePeriod()) {
			component.getAnimator().remove(this); isDone = true; return;
		}
		switch(type) {
			case AXIS_X:
				updateXDriver(driver, component);
				break;
			case AXIS_Y:
				updateYDriver(driver, component);
				break;
			case ALPHA:
				updateAlphaDriver(driver, component);
				break;
			case SCALE_X:
				updateXScaleDriver(driver, component);
				break;
			case SCALE_Y:
				updateYScaleDriver(driver, component);
				break;
		}
	}
	
	private void updateXDriver(TransitionDriver driver, UiComponent component) {
		float ammount = driver.update(getTime());
		component.setPosition(ammount, component.getPosition().y);	
	}
	
	private void updateYDriver(TransitionDriver driver, UiComponent component) {
		float ammount = driver.update(getTime());
		component.setPosition(component.getPosition().x, ammount);	
	}
	
	private void updateXScaleDriver(TransitionDriver driver, UiComponent component) {
		float ammount = driver.update(getTime());
		component.setScale(ammount, component.getScale().y);	
	}
	
	private void updateYScaleDriver(TransitionDriver driver, UiComponent component) {
		float ammount = driver.update(getTime());
		component.setScale(component.getScale().x, ammount);	
	}
	
	private void updateAlphaDriver(TransitionDriver driver, UiComponent component) {
		float ammount = driver.update(getTime());
		component.setAlpha(ammount);
	}
	
	private float getTime() {
		return (float) (GLFW.glfwGetTime() - timeStarted);
	}
	
	public Transition xDriver(TransitionDriver driver) {
		drivers.put(driver, TransitionType.AXIS_X);
		return this;
	}
	
	public Transition yDriver(TransitionDriver driver) {
		drivers.put(driver, TransitionType.AXIS_Y);
		return this;
	}
	
	public Transition xScaleDriver(TransitionDriver driver) {
		drivers.put(driver, TransitionType.SCALE_X);
		return this;
	}
	
	public Transition yScaleDriver(TransitionDriver driver) {
		drivers.put(driver, TransitionType.SCALE_Y);
		return this;
	}
	
	public Transition alphaDriver(TransitionDriver driver) {
		drivers.put(driver, TransitionType.ALPHA);
		return this;
	}
	
}
