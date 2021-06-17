package survivalGame.inventory.item.components;

import survivalGame.inventory.item.Item;

public abstract class ItemComponent {

	protected Item item;
	
	public ItemComponent(Item item) {
		this.item = item;
	}
	
	public abstract ItemComponents getType();
	
}