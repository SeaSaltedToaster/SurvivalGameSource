package survivalGame.resources.keybinding;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.input.Keys;

public class Controls {

	private static List<Control> keybindings = new ArrayList<Control>();
	
	public static void initControls() {
		keybindings.add(new Control("Move Forward", Keys.W));
		keybindings.add(new Control("Move Backward", Keys.S));
		keybindings.add(new Control("Strafe Left", Keys.A));
		keybindings.add(new Control("Strafe Right", Keys.D));
		keybindings.add(new Control("Pause / Exit", Keys.ESC));
		keybindings.add(new Control("Test 1", Keys.G));
		keybindings.add(new Control("Test 2", Keys.G));
		keybindings.add(new Control("Test 3", Keys.G));
		keybindings.add(new Control("Test 4", Keys.G));
	}
	
	public static void addControl(Control control) {
		keybindings.add(control);
	}

	public static Control getControl(String name) {
		for(Control control : keybindings) {
			if(control.getName().equalsIgnoreCase(name))
				return control;
		}
		return null;
	}

	public static List<Control> getKeybindings() {
		return keybindings;
	}
	
}
