package seaSaltedEngine.guis.core;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.guis.transitions.UiAnimator;
import seaSaltedEngine.render.model.texture.Texture;
import seaSaltedEngine.tools.math.Vector2f;
import seaSaltedEngine.tools.math.Vector4f;

public class UiComponent {

	private List<UiComponent> children;
	private UiComponent parentComponent;
	
	private boolean isActive;
	private boolean isHovering;
	private UiAnimator animator;
	
	private Vector2f position;
	private Vector2f scale;
	private UiMeta meta;
	
	public UiComponent(int level) {
		this.children = new ArrayList<UiComponent>();
		this.isActive = true;
		this.animator = new UiAnimator(this);
		
		this.position = new Vector2f(0,0);
		this.scale = new Vector2f(0.15f,0.25f);
		this.meta = new UiMeta(level, 1, null, new Vector4f(0,0,0,0), false);
		UiMaster.add(this);
	}
	
	public void addComponent(UiComponent component) {
		component.setParentComponent(this);
		component.setActive(isActive());
		children.add(component);
	}
	
	public void removeComponent(UiComponent component) {
		component.setParentComponent(null);
		children.remove(component);
	}
	
	public void updateComponent() {
		animator.update(this);
		updateClick();
		updateSelf();
		renderUi();
		updateChildren();
	}
	
	public void updateSelf() {
		//Update method that will be overridden
	}
	
	private void updateChildren() {
		for(UiComponent component : children) {
			component.updateComponent();
		}
	}
	
	private void renderUi() {
		UiMaster.renderUi(this);
	}
	
	private void updateClick() {
		Vector2f location = getPosition();
        Vector2f scale = getScale();
        double mouseCoordinatesX = Mouse.getMouseCoordsX();
        double mouseCoordinatesY = Mouse.getMouseCoordsY();
        if (location.y + scale.y > -mouseCoordinatesY && location.y - scale.y < -mouseCoordinatesY && location.x + scale.x > mouseCoordinatesX && location.x - scale.x < mouseCoordinatesX) {
        	whileHover();
        	Engine.getCamera().setCancelUpdate(true);
        	if(!isHovering)
        		onHover();
        	isHovering = true;
        	if(Engine.getInputHandler().getMouseInstance().getButtonEvent().isLeftDown())
        		onClick();
        } else {
        	if(isHovering)
        		stopHover();
        	isHovering = false;
        }
	}
	
	protected void stopHover() {
		
	}
	
	protected void onHover() {
		
	}
	
	protected void whileHover() {
		
	}
	
	protected void onClick() {
		
	}
	
	public void show() {
		this.isActive = true;
	}
	
	public void hide() {
		this.isActive = false;
	}
	
	public void increasePosition(float dx, float dy) {
		this.position.x += dx;
		this.position.y += dy;
	}
	
	public void setPosition(float dx, float dy) {
		this.position.x = dx;
		this.position.y = dy;
	}
	
	public void increaseScale(float dx, float dy) {
		this.scale.x += dx;
		this.scale.y += dy;
	}
	
	public void setScale(float dx, float dy) {
		this.scale.x = dx;
		this.scale.y = dy;
	}
	
	public void increaseAlpha(float dx) {
		this.meta.setAlpha(meta.getAlpha()+dx);
	}

	public List<UiComponent> getChildren() {
		return children;
	}

	public UiComponent getParentComponent() {
		return parentComponent;
	}

	public void setParentComponent(UiComponent parentComponent) {
		this.parentComponent = parentComponent;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getLevel() {
		return meta.getLevel();
	}

	public void setLevel(int level) {
		this.meta.setLevel(level);
	}

	public Texture getGuiTexture() {
		return meta.getGuiTexture();
	}

	public Vector4f getOverrideColor() {
		return meta.getOverrideColor();
	}

	public void setGuiTexture(Texture textureID) {
		this.meta.setGuiTexture(textureID);
	}

	public void setColor(Vector4f overrideColor) {
		this.meta.setOverrideColor(overrideColor);
	}

	public Vector2f getPosition() {
		return position;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

	public UiAnimator getAnimator() {
		return animator;
	}

	public float getAlpha() {
		return meta.getAlpha();
	}

	public void setAlpha(float ammount) {
		this.meta.setAlpha(ammount);
	}
	
	public boolean hasTexture() {
		return meta.isHasTexture();
	}
	
	public void setHasTexture(boolean state) {
		this.meta.setHasTexture(state);
	}

	public boolean isHovering() {
		return isHovering;
	}

	public UiMeta getMeta() {
		return meta;
	}
	
}
