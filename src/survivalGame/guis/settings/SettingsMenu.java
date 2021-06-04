package survivalGame.guis.settings;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import survivalGame.guis.GameMenus;
import survivalGame.guis.settings.sidebar.SettingsSidebar;
import survivalGame.guis.settings.sidebar.SidebarButton;

public class SettingsMenu extends UiComponent {

	//Exit Handlers
	private SettingsMenuHandler handler;
	private String returnID;
	
	//Menu Components
	private SettingsSidebar sidebar;
	private SidebarButton[] buttons;
	
	//Text and References
	private Text title;
	
	public SettingsMenu() {
		super(2);
		doGeneralSettings();
		createComponents();
		
		this.handler = new SettingsMenuHandler();
	}
	
	private void createComponents() {
		this.sidebar = new SettingsSidebar();
		this.addComponent(sidebar);
		this.title = new Text("Settings", 3f, Fonts.ARIAL, 1f, true);
		this.title.setPosition(-0.315f, 0.1f);
		this.title.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(title);
		this.buttons = sidebar.createButtons();
	}
	
	private void doGeneralSettings() {
		this.setScale(2, 2);
		this.setAlpha(0.65f);
		this.setActive(false);
	}
	
	public void open(String returnID) {
		this.returnID = returnID;
		this.setActive(true);
	}
	
	public void close() {
		GameMenus.setInMenu(false);
		this.setActive(false);
		this.handler.handleReturn(returnID);
	}
	
	public void closeAllTabs() {
		for(SidebarButton button : buttons) {
			if(button == null || button.getOpen() == null) continue;
			button.getOpen().setActive(false);
		}
	}

	public SettingsMenuHandler getHandler() {
		return handler;
	}

	public String getReturnID() {
		return returnID;
	}

	public SettingsSidebar getSidebar() {
		return sidebar;
	}

	public SidebarButton[] getButtons() {
		return buttons;
	}

	public Text getTitle() {
		return title;
	}

}
