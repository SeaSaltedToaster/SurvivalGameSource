package seaSaltedEngine.entity;

import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.entity.component.Component;
import seaSaltedEngine.entity.component.ComponentType;

public class Entity {

	private List<Component> components;
	private Transform transform;
	
	public Entity(Transform transform) {
		this.transform = transform;
		this.components = new ArrayList<Component>();
	}
	
	public Entity() {
		this.transform = Transform.Default;
		this.components = new ArrayList<Component>();
	}
	
	public void update() {
		for(Component component : this.getComponents()) {
			component.update();
		}
	}

	public void addComponent(Component c) {
		components.add(c);
	}
	
	public void removeComponent(Component c) {
		components.remove(c);
	}
	
	public boolean hasComponent(ComponentType comp) {
		for(Component component : components) {
			if(component.getComponentType() == comp)  {
				return true;
			}
		}
		return false;
	}
	
	public Component getComponent(ComponentType comp) {
		for(Component component : components) {
			if(component.getComponentType() == comp)  {
				return component;
			}
		}
		return null;
	}
	
	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	public List<Component> getComponents() {
		return components;
	}
	
}
