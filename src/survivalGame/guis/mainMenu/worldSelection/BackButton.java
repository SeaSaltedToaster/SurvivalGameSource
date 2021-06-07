package survivalGame.guis.mainMenu.worldSelection;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.guis.GameMenus;
import survivalGame.resources.TextureRepository;

public class BackButton extends UiComponent {

	public BackButton() {
		super(2);
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.BACK));
	}
	
	@Override
	public void onClick() {
		GameMenus.getWorldSelectionMenu().setSelected(false);
		GameMenus.getWorldSelectionMenu().getSelected().setColor(UiColors.BLACK);
		GameMenus.getWorldSelectionMenu().setActive(false);
		GameMenus.getMainMenu().setActive(true);
	}

}
