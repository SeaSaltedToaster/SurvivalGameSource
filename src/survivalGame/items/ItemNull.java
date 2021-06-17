package survivalGame.items;

import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.inventory.item.Item;
import survivalGame.resources.TextureRepository;

public class ItemNull extends Item {

	public ItemNull() {
		super(999, new Texture(TextureRepository.EMPTY), -1, "");
	}

	@Override
	public void onRightClick() {
		
	}

	@Override
	public void onLeftClick() {
		
	}

	@Override
	public void onWield() {
		
	}

	@Override
	public void update() {
		
	}

}
