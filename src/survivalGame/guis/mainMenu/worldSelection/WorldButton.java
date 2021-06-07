package survivalGame.guis.mainMenu.worldSelection;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import survivalGame.guis.GameMenus;

public class WorldButton extends UiComponent {

	//Text
	private Text name;
	private Text lastPlayed;
	private Text size;
	
	public WorldButton(int id) {
		super(4);
		initButton(id);
		initText(id);
	}

	private void initText(float id) {
		String worldName = "World " + id;
		this.name = new Text(worldName, 2f, Fonts.ARIAL, 1f, false);
		this.name.setPosition(0.305f, 0.25f + (id * 0.35f) );
		this.name.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(name);
		
		String lastPlay = "Last played : 1/1/11 - 11:11am";
		this.lastPlayed = new Text(lastPlay, 1.5f, Fonts.ARIAL, 1f, false);
		this.lastPlayed.setPosition(0.305f, 0.35f + (id * 0.35f) );
		this.lastPlayed.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(lastPlayed);
		
		String fileSize = "World Size : 100mb";
		this.size = new Text(fileSize, 1.5f, Fonts.ARIAL, 1f, false);
		this.size.setPosition(0.305f, 0.45f + (id * 0.35f) );
		this.size.setColour(UiColors.WHITE.getVec3f());
		this.addComponent(size);
	}

	private void initButton(int id) {
		this.setPosition(0.0f, 0.6f - (id * 0.35f) );
		this.setScale(0.4f, 0.15f);
		this.setAlpha(0.5f);
	}
	
	@Override
	public void onClick() {
		WorldSelectionMenu menu = GameMenus.getWorldSelectionMenu();
		if(menu.isSelected()) menu.getSelected().setColor(UiColors.BLACK);
		menu.setSelected(this); menu.setSelected(true);
		this.setColor(UiColors.WHITE.add(UiColors.BLACK).div(2));
	}

}
