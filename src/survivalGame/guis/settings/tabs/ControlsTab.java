package survivalGame.guis.settings.tabs;

import seaSaltedEngine.basic.input.keybinding.Control;
import seaSaltedEngine.basic.input.keybinding.Controls;
import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import survivalGame.guis.components.ScrollPane;
import survivalGame.guis.settings.components.KeybindSelector;

public class ControlsTab extends UiComponent {

	//Tab Items
	private ScrollPane scrollPane;
	private KeybindSelector[] selectors;
	
	//Title
	private Text title;
	public static boolean isSelecting = false;
	
	public ControlsTab() {
		super(3);
		initPanel();
		initPane();
		initSelectors();
	}
	
	private void initPanel() {
		this.setScale(0.6f, 0.7f);
		this.setAlpha(0.5f);
		this.setActive(false);
		
		this.title = new Text("Controls", 2f, Fonts.ARIAL, 1f, true);
		this.title.setPosition(-0.24f, 0.3f);
		this.title.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(title);
	}
	
	private void initSelectors() {
		int index = 0;
		selectors = new KeybindSelector[Controls.getKeybindings().size()];
		for(Control control : Controls.getKeybindings()) {
			KeybindSelector selector = new KeybindSelector(control, index, scrollPane);
			selectors[index] = selector;
			scrollPane.addComponent(selector);
			index++;
		}
	}
	
	private void initPane() {
		this.scrollPane = new ScrollPane(0f,0.025f,0.575f,0.55f);
		this.scrollPane.setScissor(true);
		this.scrollPane.setAlpha(0f);
		this.addComponent(scrollPane);
	}

}
