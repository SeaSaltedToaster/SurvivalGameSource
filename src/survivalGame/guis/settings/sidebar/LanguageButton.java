package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.guis.settings.tabs.LanguageTab;
import survivalGame.resources.TextureRepository;

public class LanguageButton extends SidebarButton {

	//Tab Instance
		private static LanguageTab languageTab;
					
		public LanguageButton() {
			super(languageTab = new LanguageTab());
						
			//Do audio buttons settings
			this.setPosition(-0.7f, -0.2f);
			this.setScale(this.getScale().divide(1.5f));
			this.setColor(UiColors.WHITE);	
			this.setActive(false);
			this.setHasTexture(true);
			this.setGuiTexture(new Texture(TextureRepository.LANGUAGE));
		}

		public static LanguageTab getGraphicsTab() {
			return languageTab;
		}

}
