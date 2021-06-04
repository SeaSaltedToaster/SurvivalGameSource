package survivalGame.guis.pauseMenu;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import seaSaltedEngine.render.display.Window;

public class QuitButton extends UiComponent {

	private Text buttonText;
	
	public QuitButton() {
		super(1);
		this.setScale(0.25f, 0.1f);
		this.setPosition(0f, -0.3f);
		this.setAlpha(0);
		createText();
	}
	
	private void createText() {
		buttonText = new Text("Quit", 2.25f, Fonts.ARIAL, 1f, true);
		buttonText.setPosition(0.0f, 1.2f);
		buttonText.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(buttonText);
	}
	
	@Override
	public void onClick() {
		Window.setShouldClose(true);
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
