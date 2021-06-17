package survivalGame.inventory;

import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.guis.core.UiMaster;

public class MouseSlot extends ContainerSlot {

	public MouseSlot() {
		super(null);
		this.setBlocksCam(false);
	}
	
	@Override
	public void onClick() {
		//Nothing for now
	}
	
	@Override
	public void updateSelf() {
		UiMaster.remove(this); UiMaster.add(this);
		this.setPosition(Mouse.getMousePosition());
		this.getIcon().setPosition(Mouse.getMousePosition());
		this.getIcon().setGuiTexture(this.getItem().getItem().getIcon());
	}

}
