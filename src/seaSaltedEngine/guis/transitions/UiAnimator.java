package seaSaltedEngine.guis.transitions;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.guis.core.UiComponent;

public class UiAnimator {
	
	private List<Transition> activeTransitions = new ArrayList<Transition>();
	private List<Transition> toRemove = new ArrayList<Transition>();
	
	private UiComponent component;
	
	public UiAnimator(UiComponent component) {
		this.component = component;
	}
	
	public void update(UiComponent component) {
		for(Transition transition : activeTransitions) {
			if(transition.isDone) {
				remove(transition); continue; }
			transition.update(component);
		}
		activeTransitions.removeAll(toRemove);
		toRemove.clear();
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
