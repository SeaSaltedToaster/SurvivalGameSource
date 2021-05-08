package survivalGame.guis.components;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.core.UiMaster;
import seaSaltedEngine.tools.math.Vector2f;

public class SliderUi extends UiComponent {
	
	private UiComponent sliderBar;
	private UiComponent sliderTab;
	
	private Vector2f sliderPosition;
	
	public SliderUi(Vector2f position) {
		super(1);
		sliderPosition = position;
		setAlpha(0);
		initLine();
		initSlider();
		UiMaster.add(this);
	}
	
	@Override
	public void updateSelf() {
		if(Engine.getInputHandler().getMouseInstance().getButtonEvent().isLeftDown() && sliderTab.isHovering()) {
			sliderTab.setPosition(Mouse.getMousePosition().x, sliderPosition.y);
			sliderBar.setPosition(sliderPosition);
		}
	}
	
	private void initLine() {
		sliderBar = new UiComponent(2);
		sliderBar.setPosition(sliderPosition);
		sliderBar.setScale(0.25f, 0.025f);
		addComponent(sliderBar);
	}
	
	private void initSlider() {
		sliderTab = new UiComponent(3);
		sliderTab.setColor(UiColors.WHITE);
		sliderTab.setPosition(sliderPosition);
		sliderTab.setScale(0.01f, 0.075f);
		addComponent(sliderTab);
	}

}
