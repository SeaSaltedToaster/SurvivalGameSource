package survivalGame.world.generation.grass.component;

import seaSaltedEngine.entity.component.Component;
import seaSaltedEngine.render.model.Mesh;

public class GrassModelComponent implements Component {
	
	private Mesh mesh;
	private int modelID;
	
	public GrassModelComponent(Mesh mesh) {
		this.mesh = mesh;
		this.modelID = -1;
	}
	
	public GrassModelComponent(Mesh mesh, int id) {
		this.mesh = mesh;
		this.modelID = id;
	}
	
	@Override
	public String getComponentType() {
		return "Model_Grass";
	}

	public Mesh getMesh() {
		return mesh;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}

	public int getModelID() {
		return modelID;
	}

	public void setModelID(int modelID) {
		this.modelID = modelID;
	}

	@Override
	public void update() {
		//UNUSED
	}

	@Override
	public boolean changesRenderer() {
		return true;
	}

}
