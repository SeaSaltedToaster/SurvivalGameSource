package survivalGame.entity;

import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.input.Keys;
import seaSaltedEngine.basic.input.keybinding.Controls;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.render.display.Window;
import survivalGame.entity.components.ContainerComponent;
import survivalGame.guis.GameMenus;
import survivalGame.guis.statusUis.UiBar;
import survivalGame.inventory.Container;
import survivalGame.inventory.item.ItemStack;
import survivalGame.inventory.item.Items;

public class EntityPlayer extends Entity {

	//Client
	private Container inventory;
	
	//Status Bars
	private UiBar healthBar;
	private UiBar hungerBar;
	private UiBar thirstBar;
	
	//network
	private String username;
	
	public EntityPlayer(Transform transform, String username) {
		super(transform);
		
		ContainerComponent component = new ContainerComponent(7,4);
		this.inventory = component.getContainer();
		this.addComponent(component);
		
		this.healthBar = new UiBar(0.8f,-0.7f);
		this.healthBar.getFillUi().setColor(UiColors.RED);
		this.hungerBar = new UiBar(0.8f,-0.8f);
		this.hungerBar.getFillUi().setColor(UiColors.ORANGE);
		this.thirstBar = new UiBar(0.8f,-0.9f);
		this.thirstBar.getFillUi().setColor(UiColors.AQUA);
		
		this.username = username;
	}
	
	@Override
	public void update() {
		checkInputs();
		doDebugChecks();
		hungerBar.increase( (float) -(0.25f * Window.getDelta()) );
		thirstBar.increase( (float) -(0.25f * Window.getDelta()) );
	}

	private void doDebugChecks() {
		if(InputHandler.isKeyPressed(Keys.F)) {
			inventory.addItem(new ItemStack(Items.CARROT,64));
		}
	}

	private void checkInputs() {
		if(InputHandler.isKeyPressed(Controls.getControl("Inventory").getKey()) 
				&& !GameMenus.isInMenu()) {
			inventory.open();
		} else if(InputHandler.isKeyPressed(Controls.getControl("Pause / Exit").getKey())) {
			inventory.close();
		}
	}

	public Container getInventory() {
		return inventory;
	}

	public String getUsername() {
		return username;
	}

}
