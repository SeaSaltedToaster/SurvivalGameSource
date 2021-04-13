package seaSaltedEngine.render.model;

import seaSaltedEngine.render.model.comp.RendererType;
import seaSaltedEngine.render.model.comp.ShaderType;

public class MeshData {

	private RendererType meshRenderer;
	private ShaderType meshShader;
	
	private Vao meshVao;
	
	public MeshData() {
		meshRenderer = RendererType.DYNAMIC;
		meshShader = ShaderType.TEXTURED;
		
		meshVao = Vao.create();
	}
	
	public MeshData(Vao vao) {
		meshRenderer = RendererType.DYNAMIC;
		meshShader = ShaderType.TEXTURED;
		
		meshVao = vao;
	}

	public Vao getMeshVao() {
		return meshVao;
	}

	public RendererType getMeshRenderer() {
		return meshRenderer;
	}

	public ShaderType getMeshShader() {
		return meshShader;
	}

	public void setMeshRenderer(RendererType meshRenderer) {
		this.meshRenderer = meshRenderer;
	}

	public void setMeshShader(ShaderType meshShader) {
		this.meshShader = meshShader;
	}
	
}
