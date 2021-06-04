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
	private float sliderValue;
	
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
			sliderValue =  (sliderTab.getPosition().x + sliderBar.getScale().x);
		}
		checkBounds();
	}
	
	private void checkBounds() {
		
	}
	
	private void initLine() {
		sliderBar = new UiComponent(2);
		sliderBar.setPosition(sliderPosition);
		sliderBar.setScale(0.3f, 0.025f);
		addComponent(sliderBar);
	}
	
	private void initSlider() {
		sliderTab = new UiComponent(3);
		sliderTab.setColor(UiColors.WHITE);
		sliderTab.setPosition(new Vector2f(sliderPosition.x, sliderPosition.y));
		sliderTab.setScale(0.025f, 0.07f);
		sliderBar.addComponent(sliderTab);
	}

	public UiComponent getSliderBar() {
		return sliderBar;
	}

	public UiComponent getSliderTab() {
		return sliderTab;
	}

	public Vector2f getSliderPosition() {
		return sliderPosition;
	}

	public void setSliderBar(UiComponent sliderBar) {
		this.sliderBar = sliderBar;
	}

	public void setSliderTab(UiComponent sliderTab) {
		this.sliderTab = sliderTab;
	}

	public void setSliderPosition(Vector2f sliderPosition) {
		this.sliderPosition = sliderPosition;
	}

	public float getSliderValue() {
		return sliderValue;
	}

}
