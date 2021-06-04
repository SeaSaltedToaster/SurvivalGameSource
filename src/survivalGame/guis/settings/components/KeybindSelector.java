package survivalGame.guis.settings.components;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import survivalGame.resources.keybinding.Control;

public class KeybindSelector extends UiComponent {

	//Text
	private Text text;
	private Text key;
	private float index;
	
	//Unbind key
	private DeselectButton button;
	private Control control;
	private KeybindSelectListener listener;

	public KeybindSelector(Control control, int index) {
		super(1); 
		this.setAlpha(0f);
		this.setPosition(0, 0);
		this.control = control;
		this.index = index;
		this.listener = new KeybindSelectListener(this);
		initComponents();
	}

	private void initComponents() {
		//Create and Add text
		this.text = new Text(">"+control.getName() + " : " + getkeyName(control.getKey()), 2f, Fonts.ARIAL, 1f, false);
		this.text.setColour(UiColors.WHITE.getVec3f());
		this.text.setPosition(0.225f, 0.45f + (index/5) );
		this.addComponent(text);
		
		//Create Selection button
		this.button = new DeselectButton(listener);
		this.button.setPosition(0.5f, 0.45f - (index/5) );
		this.button.setColor(UiColors.WHITE);
		this.addComponent(button);
	}
	
	public void setKeybind(Control control, int key) {
		control.setKey(key);
		text.setTextString(">"+control.getName() + " : " + getkeyName(key));
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
