package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.guis.settings.tabs.AudioTab;
import survivalGame.resources.TextureRepository;

public class AudioButton extends SidebarButton {

	//Tab Instance
	private static AudioTab audioTab;
	
	public AudioButton() {
		super(audioTab = new AudioTab());
		
		//Do audio buttons settings
		this.setPosition(-0.7f, 0.6f);
		this.setScale(this.getScale().divide(1.5f));
		this.setColor(UiColors.WHITE);
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.VOLUME));
	}

	public static AudioTab getAudioTab() {
		return audioTab;
	}

}
