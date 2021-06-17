package survivalGame.inventory.item;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.inventory.item.components.ItemComponent;

public abstract class Item {

	private int stackSize;
	private Texture icon;
	private String name;
	private int id;
	
	private ItemStack stack;
	private Mesh model;
	
	private List<String> loreTags = new ArrayList<String>();
	private String customName;
		
	protected List<ItemComponent> components = new ArrayList<ItemComponent>();
	
	public Item(int stackSize, Texture tex, int id, String name) {
		this.stackSize = stackSize;
		this.icon = tex;
		this.name = name;
		this.id = id;
		this.customName = name;
	}
	
	public Item(int stackSize, Texture tex, int id, String name, Mesh model) {
		this.stackSize = stackSize;
		this.icon = tex;
		this.name = name;
		this.id = id;
		this.customName = name;
		this.model = model;
	}
	
	public abstract void onRightClick();
	
	public abstract void onLeftClick();
	
	public abstract void onWield();
	
	public abstract void update();

	@Override
	protected Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return this;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public int getStackSize() {
		return stackSize;
	}

	public Texture getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	public String getStringId() {
		return ""+id;
	}

	public void setStackSize(int stackSize) {
		this.stackSize = stackSize;
	}

	public void setIcon(Texture icon) {
		this.icon = icon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getLoreTags() {
		return loreTags;
	}

	public Mesh getModel() {
		return model;
	}

	public ItemStack getStack() {
		return stack;
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;
	}
	
}
