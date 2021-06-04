package survivalGame.guis.settings.components;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import seaSaltedEngine.tools.math.Vector3f;

public class KeybindHUD extends UiComponent {

	private Text warning;
	private UiComponent background;
	
	public KeybindHUD() {
		super(0);
		this.background = new UiComponent(1);
		this.background.setScale(0.2f, 0.2f);
		this.background.setColor(UiColors.BLACK);
		this.background.setAlpha(0.8f);
		this.addComponent(background);
		
		this.warning = new Text("Press any key", 2f, Fonts.ARIAL, 1f, true);
		this.warning.setPosition(0f, 0.75f);
		this.warning.setColour(new Vector3f(0.5f,0.5f,0.5f));
		this.addComponent(warning);
		
		this.setActive(false);
	}

	public Text getWarning() {
		return warning;
	}

	public UiComponent getBackground() {
		return background;
	}

}
