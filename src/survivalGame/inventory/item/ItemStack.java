package survivalGame.inventory.item;

public class ItemStack {

	private Item item;
	private int ammount;
	private int stackSize;
	
	//Constructors
	public ItemStack(Item item) {
		this.item = item;
		this.item.setStack(this);
		this.stackSize = item.getStackSize();
		this.ammount = 1;
	}
	
    public ItemStack(Item item, int ammount) {
		this.ammount = ammount;
		this.item = item;
		this.item.setStack(this);
		this.stackSize = item.getStackSize();
	}
    
    public boolean addToStack() {
    	if(ammount >= stackSize) {
    		System.out.println("Item stack is full"); return false;
    	}
    	ammount += 1;
    	return true;
    }
    
    public boolean addToStack(int count) {
    	if(ammount >= stackSize || ammount >= (stackSize+count)) {
    		System.out.println("Item stack is full"); return false;
    	}
    	ammount += count;
    	return true;
    }
    
    public void remove() {
    	ammount -= 1;
    	if(ammount <= 0) {
    		this.item = null;
    	}
    }
    
    public void remove(int i) {
    	ammount -= i;
    	if(ammount <= 0) {
    		this.item = null;
    	}
    }
    
    public boolean isFull() {
    	if(ammount >= stackSize) {
    		return true;
    	} 
    	return false;
    }

	public ItemStack copy() {
		ItemStack stack = new ItemStack(Items.NULL);
		stack.setAmmount(getAmmount());
		stack.setItem(getItem());
		stack.setStackSize(getStackSize());
		return stack;
	}

	public Item getItem() {
		return item;
	}

	public int getAmmount() {
		return ammount;
	}

	public int getStackSize() {
		return stackSize;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setStackSize(int stackSize) {
		this.stackSize = stackSize;
	}
    
}
