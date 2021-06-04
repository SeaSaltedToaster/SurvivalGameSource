package seaSaltedEngine.guis.constraint;

import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.tools.math.Vector2f;

public abstract class UiConstraint {
	
	public abstract void update(UiComponent component, Vector2f position, Vector2f scale);

}
