package survivalGame.guis.mainMenu.worldSelection;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import survivalGame.guis.GameMenus;

public class LoadButton extends UiComponent {

	public LoadButton() {
		super(3);
		this.setScale(0.15f, 0.075f);
		this.setPosition(-0.2f, -0.9f);
		this.setColor(UiColors.WHITE.lerp(UiColors.BLACK, 0.75f));
		this.setActive(false);
		
		Text text = new Text("Load Game", 1.5f, Fonts.ARIAL, 1f, true);
		text.setPosition(-0.1f, 1.85f);
		text.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(text);
	}
	
	@Override
	public void onClick() {
		GameMenus.getWorldSelectionMenu().setSelected(false);
		GameMenus.getWorldSelectionMenu().loadGame();
	}

}
