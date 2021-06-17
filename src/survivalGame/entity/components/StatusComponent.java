package survivalGame.entity.components;

import seaSaltedEngine.entity.component.Component;

public class StatusComponent implements Component {

	public StatusComponent() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public String getComponentType() {
		return "Status";
	}

	@Override
	public boolean changesRenderer() {
		return false;
	}

}
