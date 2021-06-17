package seaSaltedEngine.guis.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seaSaltedEngine.Engine;
import seaSaltedEngine.guis.text.TextMaster;

public class UiMaster {

	//Array
	private static List<UiComponent> components = new ArrayList<UiComponent>();
	
	//CCM
	private static List<UiComponent> toRemove = new ArrayList<UiComponent>();
	private static List<UiComponent> toAdd = new ArrayList<UiComponent>();
	
	public static void update() {
		components.removeAll(toRemove); toRemove.clear();
		components.addAll(toAdd); toAdd.clear();
		Engine.getCamera().setCancelUpdate(false);
		List<UiComponent> updateList = components;
		for(Iterator<UiComponent> iterator = updateList.iterator(); iterator.hasNext();) {
			iterator.next().updateComponent();
		}
		TextMaster.render();
	}
	
	public static void renderUi(UiComponent component) {
		Engine.getUiRenderer().renderGui(component);
	}
	
	public static void add(UiComponent component) {
		toAdd.add(component);
	}
	
	public static void remove(UiComponent component) {
		toRemove.add(component);
		
		for(UiComponent component2 : component.getChildren())
			remove(component2);
	}

	public static List<UiComponent> getComponents() {
		return components;
	}
	
}
