package survivalGame.guis.pauseMenu;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.audio.AudioSource;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.guis.GameMenus;
import survivalGame.resources.Sounds;

public class PauseMenu extends UiComponent{
	
	//Menu Button
	private ResumeButton resumeButton;
	private OpenServerButton openServerButton;
	private SettingsButton settingsButton;
	private QuitButton quitButton;
	
	//Audio Source
	private static AudioSource source;
	
	public PauseMenu() {
		super(1);
		doGeneralSettings();
		initButtons();
		initAudio();
	}
	
	@Override
	public void updateSelf()  {
		if(InputHandler.isKeyPressed(GLFW.GLFW_KEY_ESCAPE) 
				&& !GameMenus.isInMenu() && !GameMenus.getMainMenu().isOpen()) {
			this.setActive(true);
			Mouse.setMouseVisible(true);
			GameMenus.setInMenu(true);
		} else if(this.isActive() && !Mouse.isMouse)
			Mouse.setMouseVisible(true);
	}
	
	private void initAudio() {
		source = new AudioSource();
		source.setPosition(new Vector3f(10,20,10));
		source.Play(Sounds.BOUNCE);
	}
	
	private void initButtons() {
		this.resumeButton = new ResumeButton();
		this.addComponent(resumeButton);
		this.openServerButton = new OpenServerButton();
		this.addComponent(openServerButton);
		this.settingsButton = new SettingsButton();
		this.addComponent(settingsButton);
		this.quitButton = new QuitButton();
		this.addComponent(quitButton);
	}
	
	private void doGeneralSettings() {
		this.setActive(false);
		this.setScale(1f, 1f);
		this.setAlpha(0.65f);
	}

	public ResumeButton getResumeButton() {
		return resumeButton;
	}

	public OpenServerButton getOpenServerButton() {
		return openServerButton;
	}

	public SettingsButton getSettingsButton() {
		return settingsButton;
	}

	public QuitButton getQuitButton() {
		return quitButton;
	}

	public static AudioSource getSource() {
		return source;
	}

}
