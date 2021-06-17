package survivalGame.entity.components;

import seaSaltedEngine.entity.component.Component;
import survivalGame.inventory.Container;

public class ContainerComponent implements Component {

	private Container container;
	
	public ContainerComponent(int rows, int collumns) {
		this.container = new Container(rows, collumns);
	}

	@Override
	public void update() {
		//Update container
	}

	@Override
	public String getComponentType() {
		return "Container";
	}

	@Override
	public boolean changesRenderer() {
		return false;
	}

	public Container getContainer() {
		return container;
	}

}
