package survivalGame.guis.mainMenu.worldSelection;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.core.UiMaster;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import survivalGame.MainApp;
import survivalGame.guis.GameMenus;
import survivalGame.main.states.MainGame;

public class WorldSelectionMenu extends UiComponent {

	//Bars
	private UiComponent topBar;
	private UiComponent bottomBar;
	
	//Text
	private Text header;
	private WorldButton[] buttons;
	
	//Activity
	private WorldButton selected;
	private boolean isSelected = false;
	
	//Buttons
	private BackButton backButton;
	private LoadButton loadButton;
	private ConnectButton connectButton;
	
	public WorldSelectionMenu() {
		super(2);
		initSelf();
		initBars();
		initText();
		initButtons();
	}
	
	public void loadGame() {
		//TODO Take in save file to load and add a loading menu
		GameMenus.getWorldSelectionMenu().setActive(false);
		UiMaster.remove(GameMenus.getMainMenu().getBackground());
		MainApp.loadGameState(new MainGame());
	}
	
	@Override
	public void updateSelf() {
		if(isSelected)
			this.loadButton.setActive(true);
		else
			this.loadButton.setActive(false);
	}
	
	private void initButtons() {
		int buttonCount = 3;
		this.buttons = new WorldButton[buttonCount];
		for(int i = 0; i < buttonCount; i++) {
			WorldButton button = new WorldButton(i);
			buttons[i] = button;
			this.addComponent(button);
		}
		
		this.loadButton = new LoadButton();
		this.loadButton.setActive(false);
		this.bottomBar.addComponent(loadButton);
		
		this.connectButton = new ConnectButton();
		this.bottomBar.addComponent(connectButton);
	}

	private void initText() {
		this.header = new Text("Worlds", 2.5f, Fonts.ARIAL, 1f, true);
		this.header.setPosition(0.0f, 0.04f);
		this.header.setColour(UiColors.WHITE.getVec3f());
		this.topBar.addComponent(header);
	}

	private void initBars() {
		this.topBar = new UiComponent(3);
		this.topBar.setScale(0.5f, 0.1f);
		this.topBar.setPosition(0.0f, 0.9f);
		this.topBar.setAlpha(0.8f);
		this.addComponent(topBar);
		
		this.bottomBar = new UiComponent(3);
		this.bottomBar.setScale(0.5f, 0.1f);
		this.bottomBar.setPosition(0.0f, -0.9f);
		this.bottomBar.setAlpha(0.8f);
		this.addComponent(bottomBar);
		
		this.backButton = new BackButton();
		this.backButton.setScale(0.03f, 0.05f);
		this.backButton.setPosition(-0.45f, -0.9f);
		this.addComponent(backButton);
	}

	private void initSelf() {
		this.setScale(0.5f, 1.0f);
		this.setAlpha(0.8f);
		this.setActive(false);
	}

	public WorldButton getSelected() {
		return selected;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(WorldButton selected) {
		this.selected = selected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public LoadButton getLoadButton() {
		return loadButton;
	}

}
