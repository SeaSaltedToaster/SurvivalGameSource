package survivalGame.guis.components;

import seaSaltedEngine.guis.core.UiComponent;

public class ScrollPane extends UiComponent {

	public ScrollPane(float x, float y, float sx, float sy) {
		super(1);
		this.setPosition(x, y);
		this.setScale(sx, sy);
		this.setScissor(true);
	}
	
	@Override
	public void updateSelf() {
		
	}

}
