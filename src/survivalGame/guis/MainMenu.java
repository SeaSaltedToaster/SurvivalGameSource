package survivalGame.guis;

import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.core.UiMaster;
import seaSaltedEngine.guis.text.Text;
import seaSaltedEngine.guis.text.TextMaster;
import seaSaltedEngine.guis.transitions.Transition;
import seaSaltedEngine.guis.transitions.drivers.SlideDriver;
import seaSaltedEngine.render.model.texture.Texture;
import seaSaltedEngine.tools.math.Vector2f;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.resources.TextureRepository;

public class MainMenu extends UiComponent {

	private Transition APPEAR = new Transition().alphaDriver(new SlideDriver(0,1,50f));
	private Text text;
	
	public MainMenu() {
		super(1);
		
		this.setColor(UiColors.GRAY);
		this.setScale(new Vector2f(1,1));
		this.setPosition(new Vector2f(0,0));
		this.setAlpha(1);
		this.getAnimator().doTransition(APPEAR);
		this.setHasTexture(true);
		this.setGuiTexture(new Texture(TextureRepository.SLOT));
		
		this.text = new Text("bob");
		this.text.setColour(new Vector3f(0,1,0));
		this.text.setPosition(0.0f, 0.5f);
		
		TextMaster.loadText(text);
		UiMaster.add(this);
	}

}
