package testing;

import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.Vector2f;

public class TestButton extends UiComponent {

	private boolean wireframe = false;
	
	public TestButton() {
		super(1);
	}
	
	@Override
	public void onClick() {
		if(!wireframe) {
			OpenGL.setPolygonWire(); wireframe = true; 
			return;
		}
		if(wireframe) {
			OpenGL.setPolygonFill(); wireframe = false; 
			return;
		}
	}
	
	@Override
	public void onHover() {
		this.setScale(this.getScale().add(new Vector2f(0.05f,0.05f)));
	}
	
	@Override
	public void stopHover() {
		this.setScale(this.getScale().subtract(new Vector2f(0.05f,0.05f)));
	}

}
