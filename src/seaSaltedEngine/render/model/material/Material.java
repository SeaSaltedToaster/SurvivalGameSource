package seaSaltedEngine.render.model.material;

import seaSaltedEngine.tools.math.Vector3f;

public class Material {
	
private String materialName; //Name
	
	private Vector3f ambientColor; //KA
	private Vector3f diffuseColor; //KD
	private Vector3f specularColor; //KS
	
	private float specularExponent; //NS

	public Material(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public Vector3f getAmbientColor() {
		return ambientColor;
	}

	public Vector3f getDiffuseColor() {
		return diffuseColor;
	}

	public Vector3f getSpecularColor() {
		return specularColor;
	}

	public float getSpecularExponent() {
		return specularExponent;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public void setAmbientColor(Vector3f ambientColor) {
		this.ambientColor = ambientColor;
	}

	public void setDiffuseColor(Vector3f diffuseColor) {
		this.diffuseColor = diffuseColor;
		System.out.println(diffuseColor);
	}

	public void setSpecularColor(Vector3f specularColor) {
		this.specularColor = specularColor;
	}

	public void setSpecularExponent(float specularExponent) {
		this.specularExponent = specularExponent;
	}

}
