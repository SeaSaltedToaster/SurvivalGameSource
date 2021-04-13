package seaSaltedEngine.guis.transitions;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.guis.core.UiComponent;

public class UiAnimator {
	
	private List<Transition> activeTransitions = new ArrayList<Transition>();
	private List<Transition> toRemove = new ArrayList<Transition>();
	
	private UiComponent component;
	
	public UiAnimator(UiComponent component) {
		this.component = component;
	}
	
	public void update(UiComponent component) {
		activeTransitions.removeAll(toRemove);
		for(Transition transition : activeTransitions) {
			transition.update(component);
		}
	}
	
	public void doTransition(Transition transition) {
		transition.start();
		activeTransitions.add(transition);
		for(UiComponent component : component.getChildren()) {
			component.getAnimator().doTransition(transition);
		}
	}
	
	public void remove(Transition transition) {
		this.toRemove.add(transition);
	}
	
}
