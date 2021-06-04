package survivalGame.guis.settings.components;

import seaSaltedEngine.Engine;
import seaSaltedEngine.guis.core.UiComponent;
import survivalGame.guis.settings.tabs.ControlsTab;

public class DeselectButton extends UiComponent {

	private KeybindSelectListener listener;
	private KeybindHUD hud;
	
	public DeselectButton(KeybindSelectListener listener) {
		super(1);
		this.listener = listener;
		this.hud = new KeybindHUD();
		this.setScale(0.04f, 0.075f);
	}
	
	@Override
	public void onClick() {
		if(!Engine.getInputHandler().getKeyboardInstance().event.getListeners().contains(listener) && !ControlsTab.isSelecting) {
			Engine.getInputHandler().getKeyboardInstance().event.addListener(listener);
			hud.setActive(true);
			ControlsTab.isSelecting = true;
		}
	}

	public KeybindSelectListener getListener() {
		return listener;
	}

	public KeybindHUD getHud() {
		return hud;
	}

}
