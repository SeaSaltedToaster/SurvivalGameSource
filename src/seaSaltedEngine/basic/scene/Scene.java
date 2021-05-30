package seaSaltedEngine.basic.scene;

import java.util.List;

import seaSaltedEngine.guis.core.UiComponent;
import survivalGame.entity.core.EntityBatch;

public class Scene {
	
	private List<UiComponent> uis;
	private EnvironmentVariables environment;
	private EntityBatch sceneBatch;
	
	public Scene(EntityBatch sceneBatch, EnvironmentVariables environment, List<UiComponent> uis) {
		this.uis = uis;
		this.environment = environment;
		this.sceneBatch = sceneBatch;
	}

	public List<UiComponent> getUis() {
		return uis;
	}
	
	public EnvironmentVariables getEnvironment() {
		return environment;
	}
	
	public EntityBatch getSceneBatch() {
		return sceneBatch;
	}
	
	public void setUis(List<UiComponent> uis) {
		this.uis = uis;
	}
	
	public void setEnvironment(EnvironmentVariables environment) {
		this.environment = environment;
	}
	
	public void setSceneBatch(EntityBatch sceneBatch) {
		this.sceneBatch = sceneBatch;
	}

}
