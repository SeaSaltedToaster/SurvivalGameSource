package survivalGame.items;

import seaSaltedEngine.render.model.texture.Texture;
import survivalGame.inventory.item.Item;
import survivalGame.resources.TextureRepository;

public class ItemCarrot extends Item {

	public ItemCarrot() {
		super(99, new Texture(TextureRepository.CARROT), 0, "Carrot");
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
