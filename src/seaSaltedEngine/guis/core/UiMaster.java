package seaSaltedEngine.guis.core;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.Engine;

public class UiMaster {

	private static List<UiComponent> components = new ArrayList<UiComponent>();
	
	public static void update() {
		for(UiComponent component : components) {
			component.updateComponent();
			component.updateSelf();
			Engine.getUiRenderer().renderGui(component);
		}
	}
	
	public static void add(UiComponent component) {
		components.add(component);
	}
	
}
