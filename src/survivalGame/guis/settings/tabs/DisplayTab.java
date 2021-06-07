package survivalGame.guis.settings.tabs;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;

public class DisplayTab extends UiComponent {

	//Text
	private Text title;
	private Text warn;
	
	public DisplayTab() {
		super(3);
		this.setScale(0.6f, 0.7f);
		this.setAlpha(0.5f);
		this.setActive(false);
		
		this.title = new Text("Display Settings", 2f, Fonts.ARIAL, 1f, true);
		this.title.setPosition(-0.19f, 0.3f);
		this.title.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(title);
		
		this.warn = new Text("No Settings are availible...", 1.5f, Fonts.ARIAL, 1f, true);
		this.warn.setPosition(0f, 0.5f);
		this.warn.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(warn);
	}
	
}
