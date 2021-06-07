package survivalGame.guis.components;

import seaSaltedEngine.guis.core.UiComponent;

public class DropdownBox {

	//Value table and values
	private String[] items;
	
	//UIs
	private UiComponent box;
	private UiComponent arrow;
	
	//Constants
	private int MAX_VALUES = 10;
	
	public DropdownBox() {
		items = new String[MAX_VALUES];
		initComponents();
	}
	
	private void initComponents() {
		this.box = new UiComponent(1);
		this.arrow = new UiComponent(1);
	}

	public void addItem(String item) {
		items[items.length+1] = item;
	}
}
