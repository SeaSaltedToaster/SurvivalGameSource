package seaSaltedEngine.guis.constraint;

import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.tools.math.Vector2f;

public class CenterLayout extends UiConstraint {

	public CenterLayout() {
		
	}

	@Override
	public void update(UiComponent component, Vector2f position, Vector2f scale) {
		Vector2f parentPosition = component.getParentComponent().getPosition();
		float dx = parentPosition.x;
		float dy = parentPosition.y;
		component.setPosition(dx, dy);
	}

}
