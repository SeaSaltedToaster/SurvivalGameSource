package survivalGame.guis.pauseMenu;

import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import survivalGame.guis.GameMenus;
import survivalGame.resources.Sounds;

public class ResumeButton extends UiComponent {

	private Text buttonText;
	
	public ResumeButton() {
		super(1);
		this.setScale(0.25f, 0.1f);
		this.setPosition(0f, 0.3f);
		this.setAlpha(0);
		createText();
	}
	
	private void createText() {
		buttonText = new Text("Resume", 2.25f, Fonts.ARIAL, 1f, true);
		buttonText.setPosition(0.0f, 0.6f);
		buttonText.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(buttonText);
	}
	
	@Override
	public void onClick() {
		this.setActive(false);
		this.getParentComponent().setActive(false);
		Mouse.setMouseVisible(false);
		GameMenus.setInMenu(false);
		PauseMenu.getSource().Play(Sounds.BOUNCE);
	}
	
	@Override
	public void onHover() {
		this.buttonText.setFontSize(2.75f);
		this.buttonText.reset();
	}
	
	@Override
	public void stopHover() {
		this.buttonText.setFontSize(2.25f);
		this.buttonText.reset();
	}

}
