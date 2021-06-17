package survivalGame.guis.statusUis;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.render.model.texture.Texture;
import seaSaltedEngine.tools.math.Vector2f;
import survivalGame.resources.TextureRepository;

public class UiBar extends UiComponent {

	private UiComponent fillUi;
	private float unitFill;
	
	private float x, y;
	
	public float maxFill = 100;
	public float currentFill = maxFill;
	
	public UiBar(float x, float y) {
		super(1);
		this.x = x; this.y = y;
		initBack(x,y);
		initBar();
		this.unitFill = fillUi.getScale().x / maxFill;
	}

	private void initBar() {
		this.fillUi = new UiComponent(1);
		this.fillUi.setColor(UiColors.RED);
		this.fillUi.setPosition(new Vector2f(x,y));
		this.fillUi.setScale(getScale().subtract(new Vector2f(0.015f, 0.015f)));
		this.addComponent(fillUi);
	}

	private void initBack(float x, float y) {
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.INVENTORY));
		this.setPosition(x, y);
		this.setScale(0.125f, 0.04f);
	}
	
	public void increase(float x) {
		if(exceedsLimit(x)) return;
		recalculateScales(x);
		currentFill += x;
	}

	private void recalculateScales(float x) {
		fillUi.increaseScale( (unitFill*x), 0f);
		fillUi.increasePosition( -(unitFill*x), 0f);
	}
	
	private boolean exceedsLimit(float x) {
		if(fillUi.getScale().x - (unitFill * x) <= 0f || currentFill + x <= 0f) return true;
		if(fillUi.getScale().x + (unitFill * x) > maxFill * unitFill || currentFill + x > maxFill) return true;
		return false;
	}

	public UiComponent getFillUi() {
		return fillUi;
	}

	public float getUnitFill() {
		return unitFill;
	}

	public float getMaxFill() {
		return maxFill;
	}

	public float getCurrentFill() {
		return currentFill;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
 
	public void setBarPosition(float x, float y) {
		setX(x); setY(y);
	}
	
}
