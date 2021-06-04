package survivalGame.guis.settings.sidebar;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;

public class SettingsSidebar extends UiComponent {

	private int TAB_COUNT = 5;
	
	public SettingsSidebar() {
		super(2);
		this.setAlpha(0.5f);
		this.setColor(UiColors.BLACK);
		this.setPosition(-0.7f, 0f);
		this.setScale(0.075f, 0.7f);
	}
	
	public SidebarButton[] createButtons() {
		//Initialize Button Array
		SidebarButton[] buttons = new SidebarButton[TAB_COUNT];
		
		//Loop and create buttons
		buttons[0] = new AudioButton();
		buttons[1] = new ControlsButton();
		buttons[2] = new DisplayButton();
		buttons[3] = new GraphicsButton();
		buttons[4] = new CloseButton();
		
		//Add all to sidebar
		for(SidebarButton button : buttons) {
			if(button == null) continue;
			this.addComponent(button);
		}
		
		//Return new array of buttons
		return buttons;
	}

}
