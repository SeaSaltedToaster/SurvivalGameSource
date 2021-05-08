package survivalGame.world.terrain.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import seaSaltedEngine.Engine;
import seaSaltedEngine.render.model.Vao;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.terrain.TerrainChunk;

public class ChunkRenderer {
	
	private ChunkShader shader;

	public ChunkRenderer() {
		shader = new ChunkShader();
		shader.start();
		shader.getProjectionMatrix().loadMatrix(Engine.getRenderer().getProjectionMatrix());
		shader.stop();
	}
	
	public void renderEntities(TerrainChunk chunk) {
		beginRendering();
		prepareModel(chunk.getTerrainMesh().getMeshVao());
		prepareInstance(chunk);
		GL11.glDrawElements(GL11.GL_TRIANGLES, chunk.getTerrainMesh().getMeshVao().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
		unbindModel();	
		finishRendering();
	}
	
	private void beginRendering() {
		OpenGL.enableCull();
		shader.start();
		shader.getViewMatrix().loadMatrix(MathUtils.createViewMatrix(Engine.getCamera()));
		shader.getLightPosition().loadVec3(new Vector3f(16,64,16));
	}
	
	private void finishRendering() {
		shader.stop();
		OpenGL.disableCull();
	}
	
	private void prepareModel(Vao model) {
		model.bind(0,1,2);
		OpenGL.enableVertexAttribArrays(0,1,2);
	}
	
	private void unbindModel() {
		GL30.glBindVertexArray(0);
		OpenGL.disableVertexAttribArrays(0,1,2);
	}
	
	private void prepareInstance(TerrainChunk entity) {
		shader.getTransformationMatrix().loadMatrix(MathUtils.createTransformationMatrix(entity.getTransform().getPosition(), 0, 0, 0, 1));
	}

}
