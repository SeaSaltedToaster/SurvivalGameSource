package seaSaltedEngine.basic.input.keybinding;

public class Control {

	private String name;
	private int key;

	public Control(String name, int key) {
		super();
		this.name = name;
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public int getKey() {
		return key;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKey(int key) {
		this.key = key;
	}

}
