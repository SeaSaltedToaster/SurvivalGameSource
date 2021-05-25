package seaSaltedEngine.entity.component;

import seaSaltedEngine.render.model.Mesh;

public class ModelComponent implements Component {

	private Mesh mesh;
	private int modelID;
	
	public ModelComponent(Mesh mesh) {
		this.mesh = mesh;
		this.modelID = -1;
	}
	
	public ModelComponent(Mesh mesh, int id) {
		this.mesh = mesh;
		this.modelID = id;
	}
	
	@Override
	public String getComponentType() {
		return "Model";
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
		return false;
	}

}
