package seaSaltedEngine.guis.constraint;

import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.tools.math.Vector2f;

public class ParentConstraint extends UiConstraint {
	
	private float percent;
	
	public ParentConstraint(float percent) {
		this.percent = percent;
	}

	@Override
	public void update(UiComponent component, Vector2f position, Vector2f scale) {
		Vector2f parentScale = component.getParentComponent().getScale();
		component.setScale(percent % parentScale.x, percent % parentScale.y);
	}

}
