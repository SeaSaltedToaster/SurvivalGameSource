package survivalGame.inventory;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.guis.GameMenus;
import survivalGame.inventory.item.ItemStack;
import survivalGame.inventory.item.Items;
import survivalGame.resources.TextureRepository;

public class Container extends UiComponent {

	//Static Mouse Slot
	private static MouseSlot mouseSlot = new MouseSlot();
	
	//This container
	private ContainerSlot[] slots;
	private ItemStack[] items;
	
	public Container(int rows, int collumns) {
		super(1);
		initContainerSettings(rows, collumns);
		initSlots(rows, collumns);
		initItems(rows * collumns);
	}

	@Override
	public void updateSelf() {
		if(this.isActive()) {
			Engine.getCamera().setCancelUpdate(true);
			mouseSlot.setActive(true);
		} else {
			mouseSlot.setActive(false);
		}
		updateItems();
	}

	private void updateItems() {
		int index = 0;
		for(ContainerSlot slot : slots) {
			slot.setItem(items[index]);
			index++;
		}
	}
	
	public void addItem(ItemStack stack) {
		for(ItemStack slot : items) {
			if(slot.getItem().getId() == Items.NULL.getId()) {
				slot.setItem(stack.getItem());
				slot.setAmmount(stack.getAmmount());
				return;
			}
		}
		Logger.Log("Container is full");
	}
	
	private void initItems(int size) {
		items = new ItemStack[size];
		for(int i = 0; i < size; i++) {
			items[i] = new ItemStack(Items.NULL, 1);
		}
	}

	private void initSlots(int rows, int collumns) {
		slots = new ContainerSlot[rows * collumns];
		int index = 0;
		for(int y = 0; y < collumns; y++) {	
			for(int x = 0; x < rows; x++) {	
				ContainerSlot slot = new ContainerSlot(this);
				slots[index] = slot;
				slot.setPosition( (x * 0.125f) - (rows * 0.0535f) , (y * 0.2f) - (collumns * 0.07f) );
				this.addComponent(slot);
				index++;
			}
		}
	}

	private void initContainerSettings(int rows, int collumns) {
		float dx = rows * 0.07f;
		float dy = collumns * 0.12f;
		this.setScale(dx, dy);
		this.setPosition(0f, 0f);
		this.setActive(false);
		
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.INVENTORY));
	}
	
	public void close() {
		this.setActive(false);
		GameMenus.setInMenu(false);
		Mouse.setMouseVisible(false);
	}
	
	public void open() {
		this.setActive(true);
		GameMenus.setInMenu(true);
		Mouse.setMouseVisible(true);
	}

	public static ContainerSlot getMouseSlot() {
		return mouseSlot;
	}

	public ContainerSlot[] getSlots() {
		return slots;
	}

	public ItemStack[] getItems() {
		return items;
	}

	public static void setMouseSlot(MouseSlot mouseSlot) {
		Container.mouseSlot = mouseSlot;
	}

	public void setSlots(ContainerSlot[] slots) {
		this.slots = slots;
	}

	public void setItems(ItemStack[] items) {
		this.items = items;
	}

}
