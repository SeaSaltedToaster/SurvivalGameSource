package survivalGame.guis.mainMenu;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import survivalGame.guis.GameMenus;

public class SettingsButton extends UiComponent {

	private Text buttonText;
	
	public SettingsButton() {
		super(1);
		this.setScale(0.2f, 0.1f);
		this.setPosition(-0.7f, -0.15f);
		this.setColor(UiColors.BLACK);
		this.setAlpha(0.6f);
		createText();
	}
	
	private void createText() {
		buttonText = new Text("Settings", 2.25f, Fonts.ARIAL, 1f, true);
		buttonText.setPosition(-0.35f, 1.075f);
		buttonText.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(buttonText);
	}
	
	@Override
	public void onClick() {
		GameMenus.getMainMenu().setActive(false);
		GameMenus.getSettingsMenu().open("MainMenu");
	}

}
