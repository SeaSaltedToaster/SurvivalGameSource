package survivalGame.inventory;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.inventory.item.ItemStack;
import survivalGame.inventory.item.Items;
import survivalGame.resources.TextureRepository;

public class ContainerSlot extends UiComponent {

	//Ui Related
	private UiComponent icon;
	private Text stackSize;
	
	//Variables
	private ItemStack item;
	private Container parent;
	
	public ContainerSlot(Container parent) {
		super(3);
		this.parent = parent;
		initUI();
		initItemStack();
		generateIcon();
	}
	
	@Override
	public void onClick() {
		//Get item
		ItemStack slotItem = getItem().copy();
		ItemStack mouseItem = Container.getMouseSlot().getItem().copy();
		
		//This item
		setItem(mouseItem);
		icon.setGuiTexture(mouseItem.getItem().getIcon());
		
		//Mouse slot textures
		Container.getMouseSlot().icon.setGuiTexture(slotItem.getItem().getIcon());
		Container.getMouseSlot().setItem(slotItem);
	}
	
	@Override
	public void updateSelf() {
		this.icon.setGuiTexture(item.getItem().getIcon());
	}

	private void initItemStack() {
		this.item = new ItemStack(Items.NULL,64);
	}
	
	private void generateIcon() {
		//Init Icon
		this.icon = new UiComponent(4);
		this.icon.setHasTexture(true);
		this.icon.setGuiTexture(item.getItem().getIcon());
		this.icon.setPosition(this.getPosition());
		this.icon.setScale(this.getScale().divide(1.1f));
		this.addComponent(icon);
		
		
		//Init Text
		this.stackSize = new Text(""+item.getAmmount(), 1f, Fonts.ARIAL, 1f, false);
		this.stackSize.setPosition(1f, 1f);
		this.stackSize.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(stackSize);
	}

	private void initUI() {
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.SLOT));
		this.setAlpha(0.25f);
		this.setScale(this.getScale().divide(3.2f));
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
		this.icon.setGuiTexture(item.getItem().getIcon());
	}

	public Container getParent() {
		return parent;
	}

	public UiComponent getIcon() {
		return icon;
	}

	public Text getStackSize() {
		return stackSize;
	}

}
