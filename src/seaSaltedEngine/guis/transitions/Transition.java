package seaSaltedEngine.guis.transitions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import seaSaltedEngine.guis.core.UiAspect;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.render.display.Window;

public class Transition {

	private HashMap<TransitionDriver, UiAspect> drivers;
	private HashMap<TransitionDriver, UiAspect> backup;
	
	private double currentTime;
	public boolean isDone = false; 
	
	public Transition() {
		this.drivers = new HashMap<TransitionDriver, UiAspect>();
		this.backup = new HashMap<TransitionDriver, UiAspect>();
	}
	
	@SuppressWarnings("unchecked")
	public void start() {
		backup = (HashMap<TransitionDriver, UiAspect>) drivers.clone();
	}

	@SuppressWarnings("unchecked")
	public void reset() {
		drivers = (HashMap<TransitionDriver, UiAspect>) backup.clone();
	}
	
	public void update(UiComponent component) {
		Iterator<Entry<TransitionDriver, UiAspect>> iterator = drivers.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<TransitionDriver, UiAspect> entry = iterator.next();
			updateDriver(entry.getKey(),entry.getValue(), component, iterator);
		}
	}
	
	private void updateDriver(TransitionDriver driver, UiAspect type, UiComponent component, Iterator<Entry<TransitionDriver, UiAspect>> entry) {
		if(drivers.size() == 0) {		}
		if(driver.hasCompletedOnePeriod()) {
			component.getAnimator().remove(this);
			entry.remove();
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
		this.currentTime += Window.getDelta();
		return (float) (currentTime);
	}
	
	public Transition xDriver(TransitionDriver driver) {
		drivers.put(driver, UiAspect.AXIS_X);
		return this;
	}
	
	public Transition yDriver(TransitionDriver driver) {
		drivers.put(driver, UiAspect.AXIS_Y);
		return this;
	}
	
	public Transition xScaleDriver(TransitionDriver driver) {
		drivers.put(driver, UiAspect.SCALE_X);
		return this;
	}
	
	public Transition yScaleDriver(TransitionDriver driver) {
		drivers.put(driver, UiAspect.SCALE_Y);
		return this;
	}
	
	public Transition alphaDriver(TransitionDriver driver) {
		drivers.put(driver, UiAspect.ALPHA);
		return this;
	}

	@Override
	protected Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
