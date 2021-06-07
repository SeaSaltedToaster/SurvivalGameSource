package survivalGame.guis.mainMenu;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import seaSaltedEngine.render.display.Window;

public class QuitButton extends UiComponent {

	private Text buttonText;
	
	public QuitButton() {
		super(2);
		this.setScale(0.2f, 0.1f);
		this.setPosition(-0.7f, -0.45f);
		this.setColor(UiColors.BLACK);
		this.setAlpha(0.6f);
		createText();
	}
	
	private void createText() {
		buttonText = new Text("Quit", 2.25f, Fonts.ARIAL, 1f, true);
		buttonText.setPosition(-0.35f, 1.375f);
		buttonText.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(buttonText);
	}
	
	@Override
	public void onClick() {
		Window.setShouldClose(true);
	}

}
