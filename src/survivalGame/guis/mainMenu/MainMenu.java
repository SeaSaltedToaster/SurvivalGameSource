package survivalGame.guis.mainMenu;

import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.core.UiMaster;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.resources.TextureRepository;

public class MainMenu {

	//UIs
	private UiComponent background;
	private UiComponent logo;
	
	//Buttons
	private PlayButton playButton;
	private SettingsButton settingsButton;
	private QuitButton quitButton;
	
	//Values and Constants
	private Texture bg;
	private boolean isOpen = true;
	
	public MainMenu() {
		initBackground();
		initButtons();
	}

	private void initBackground() {
		this.bg = new Texture(TextureRepository.MENU);
		this.isOpen = true;
		
		this.background = new UiComponent(1);
		this.background.setHasTexture(true);
		this.background.setGuiTexture(bg);
		this.background.setScale(1f,1f);
		UiMaster.add(background);
		
		this.logo = new UiComponent(1);
		this.logo.setPosition(0f, 0.7f);
		this.logo.setScale(0.5f, 0.2f);
		this.logo.setHasTexture(true);
		this.logo.setGuiTexture(new Texture(TextureRepository.LOGO));
		this.logo.setActive(false);
	}
	
	private void initButtons() {
		this.playButton = new PlayButton();
		this.background.addComponent(playButton);
		
		this.settingsButton = new SettingsButton();
		this.background.addComponent(settingsButton);
		
		this.quitButton = new QuitButton();
		this.background.addComponent(quitButton);
	}
	
	public void setActive(boolean state) {
		this.isOpen = false;
		for(UiComponent component : background.getChildren()) {
			component.setActive(state);
		}
	}

	public boolean isOpen() {
		return isOpen;
	}

	public UiComponent getBackground() {
		return background;
	}

	public void setBackground(UiComponent background) {
		this.background = background;
	}

}
