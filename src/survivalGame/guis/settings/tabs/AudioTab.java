package survivalGame.guis.settings.tabs;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import seaSaltedEngine.tools.math.Vector2f;
import seaSaltedEngine.tools.math.Vector4f;
import survivalGame.guis.components.SliderUi;
import survivalGame.guis.pauseMenu.PauseMenu;

public class AudioTab extends UiComponent {

	//Items in tab
	private SliderUi volumeSlider;
	private Text volumeText;
	
	//Title 
	private Text title;
	
	public AudioTab() {
		super(3);
		initPanel();
		initVolumeSlider();
	}
	
	@Override
	public void updateSelf() {
		PauseMenu.getSource().setPitch(volumeSlider.getSliderValue()*3);
	}
	
	private void initVolumeSlider() {
		this.volumeSlider = new SliderUi(new Vector2f(0.15f,0.475f));
		this.volumeSlider.setActive(false);
		this.volumeSlider.getSliderBar().setColor(UiColors.WHITE);
		this.volumeSlider.getSliderTab().getOverrideColor().add(new Vector4f(0.25f,0.25f,0.25f,1f));
		this.addComponent(volumeSlider);
		
		this.volumeText = new Text("> Volume", 1.75f, Fonts.ARIAL, 1f, true);
		this.volumeText.setPosition(-0.225f, 0.465f);
		this.volumeText.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(volumeText);
	}
	
	private void initPanel() {
		this.setScale(0.6f, 0.7f);
		this.setAlpha(0.5f);
		this.setActive(false);
		
		this.title = new Text("Audio Settings", 2f, Fonts.ARIAL, 1f, true);
		this.title.setPosition(-0.2f, 0.3f);
		this.title.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(title);
	}

}
