package seaSaltedEngine.guis.core;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.camera.FirstPersonCamera;

public class UiMaster {

	private static List<UiComponent> components = new ArrayList<UiComponent>();
	
	public static void update() {
		Engine.getCamera().setCancelUpdate(false);
		for(UiComponent component : components) {
			component.updateComponent();
			component.updateSelf();
			if(component.isActive()) {
				Engine.getUiRenderer().renderGui(component);
				for(UiComponent componentChild : component.getChildren()) {
					Engine.getUiRenderer().renderGui(componentChild);
				}
			}
		}
	}
	
	public static void add(UiComponent component) {
		components.add(component);
	}
	
}
