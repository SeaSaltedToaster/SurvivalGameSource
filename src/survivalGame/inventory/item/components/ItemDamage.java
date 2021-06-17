package survivalGame.inventory.item.components;

import survivalGame.inventory.item.Item;

public class ItemDamage extends ItemComponent{

	double damage = 10;
	
	public ItemDamage(Item item) {
		super(item);
	}

	@Override
	public ItemComponents getType() {
		return ItemComponents.Damage;
	}

	public double getDamage() {
		return  damage;
	}

	public void setDamage(double d) {
		this.damage =  d;
	}

}
