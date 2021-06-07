package survivalGame.guis.settings.components;

import seaSaltedEngine.Engine;
import seaSaltedEngine.guis.Listener;
import survivalGame.guis.settings.tabs.ControlsTab;

public class KeybindSelectListener implements Listener {

	private KeybindSelector selector;
	
	public KeybindSelectListener(KeybindSelector selector) {
		this.selector = selector;
	}
	
	@Override
	public void notify(String update) {
		if(update.substring(5, update.length()).equalsIgnoreCase("0")) return;
		String reduced = update.split(":;:")[0].replace(";", "").replace(":", "");
		int key = Integer.parseInt(reduced);
		selector.setKeybind(selector.getControl(), key);
		selector.getButton().getHud().setActive(false);
		ControlsTab.isSelecting = false;
		Engine.getInputHandler().getKeyboardInstance().event.removeListener(this);
	}

	@Override
	public void cancel() {
		
	}

}
