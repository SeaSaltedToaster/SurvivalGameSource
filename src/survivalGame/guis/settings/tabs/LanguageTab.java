package survivalGame.guis.settings.tabs;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;

public class LanguageTab extends UiComponent {

	//Text
	private Text title;
	private Text current;
	
	public LanguageTab() {
		super(3);
		this.setScale(0.6f, 0.7f);
		this.setAlpha(0.5f);
		this.setActive(false);
		
		this.title = new Text("Language Settings", 2f, Fonts.ARIAL, 1f, true);
		this.title.setPosition(-0.18f, 0.3f);
		this.title.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(title);
		
		this.current = new Text("> English <", 1.5f, Fonts.ARIAL, 1f, true);
		this.current.setPosition(-0.225f, 0.45f);
		this.current.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(current);
	}

}
