package survivalGame.guis.settings.components;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.basic.input.keybinding.Control;
import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import survivalGame.guis.components.ScrollPane;

public class KeybindSelector extends UiComponent {

	//Text
	private Text text;
	private Text key;
	private float index;
	
	//Unbind key
	private DeselectButton button;
	private Control control;
	private KeybindSelectListener listener;

	public KeybindSelector(Control control, int index, ScrollPane pane) {
		super(1); 
		this.setAlpha(0f);
		this.setPosition(0, 0);
		this.setScale(0, 0);
		this.control = control;
		this.index = index;
		this.listener = new KeybindSelectListener(this);
		initComponents(pane);
	}

	private void initComponents(ScrollPane pane) {
		//Create and Add text
		this.text = new Text("> "+control.getName() + " : " + getkeyName(control.getKey()), 2f, Fonts.ARIAL, 1f, false);
		this.text.setColour(UiColors.WHITE.getVec3f());
		this.text.setPosition(0.225f, 0.45f + (index/5) );
		pane.addComponent(text);
		
		//Create Selection button
		this.button = new DeselectButton(listener);
		this.button.setPosition(0.5f, 0.45f - (index/5) + 0.025f );
		this.button.setColor(UiColors.WHITE);
		pane.addComponent(button);
	}
	
	public void setKeybind(Control control, int key) {
		control.setKey(key);
		text.setTextString("> "+control.getName() + " : " + getkeyName(key));
		text.reset();
	}
	
	public String getkeyName(int key) {
		return GLFW.glfwGetKeyName(key, GLFW.glfwGetKeyScancode(key));
	}

	public Text getText() {
		return text;
	}

	public Text getKey() {
		return key;
	}

	public float getIndex() {
		return index;
	}

	public DeselectButton getButton() {
		return button;
	}

	public Control getControl() {
		return control;
	}

	public KeybindSelectListener getListener() {
		return listener;
	}
	
}
