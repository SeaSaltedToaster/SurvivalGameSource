package seaSaltedEngine.entity.component;

public class SwayComponent implements Component {

	public SwayComponent() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public String getComponentType() {
		return "Sway";
	}

	@Override
	public boolean changesRenderer() {
		return false;
	}

}
