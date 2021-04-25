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
		}
	}
	
	public static void renderUi(UiComponent component) {
		Engine.getUiRenderer().renderGui(component);
	}
	
	public static void add(UiComponent component) {
		components.add(component);
	}
	
}
