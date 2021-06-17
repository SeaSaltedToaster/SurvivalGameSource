package survivalGame.inventory.item;

import java.util.ArrayList;
import java.util.List;

import survivalGame.items.ItemCarrot;
import survivalGame.items.ItemNull;

public class Items {

	//File items (Items initialized in an outside folder) 
	public static Item NULL = new ItemNull();
	public static Item CARROT = new ItemCarrot();
	
	public static List<Item> gameItems = new ArrayList<Item>();
	
	public static Item getItemFromString(String name) {
		for(Item item : gameItems) {
			if(item.getName().equalsIgnoreCase(name)) {
				System.out.println("Gave 1 "+name+" to Player");
				return item;
			}
			if(item.getStringId().equalsIgnoreCase(name)) {
				System.out.println("Gave 1 "+item.getName()+" to Player");
				return item;
			}
		}
		System.out.println("Invalid Item");
		return null;
	}
	
	public static Item findItemFromString(String name) {
		for(Item item : gameItems) {
			if(item.getName().equalsIgnoreCase(name)) {
				return item;
			}
			if(item.getStringId().equalsIgnoreCase(name)) {
				return item;
			}
		}
		System.out.println("Invalid Item");
		return null;
	}
	
	public static void updateItems() {
		for(Item item : gameItems) {
			item.update();
		}
	}
	
	public static void init() {
		gameItems.add(NULL);
		gameItems.add(CARROT);
	}
	
}
