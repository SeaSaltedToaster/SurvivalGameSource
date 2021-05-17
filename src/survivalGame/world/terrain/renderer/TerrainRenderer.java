package survivalGame.world.terrain.renderer;

import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import seaSaltedEngine.Engine;
import seaSaltedEngine.render.model.Vao;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.generation.WorldGenerator;
import survivalGame.world.terrain.TerrainChunk;

public class TerrainRenderer {

	private static TerrainShader shader;

	public static void init() {
		shader = new TerrainShader();
		shader.start();
		shader.getProjectionMatrix().loadMatrix(Engine.getRenderer().getProjectionMatrix());
		shader.stop();
	}
	
	public static void renderChunks() {
		for(Entry<TerrainChunk, Boolean> entry : WorldGenerator.getWorldChunks().entrySet()) {
			if(entry.getValue())
				renderChunk(entry.getKey());
		}
	}
	
	public static void renderChunk(TerrainChunk chunk) {
		beginRendering();
		prepareModel(chunk.getMesh().getTerrainMesh().getMeshVao());
		prepareInstance(chunk);
		GL11.glDrawElements(GL11.GL_TRIANGLES, chunk.getMesh().getTerrainMesh().getMeshVao().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
		unbindModel();	
		finishRendering();
	}
	
	private static void beginRendering() {
		OpenGL.enableCull();
		shader.start();
		shader.getViewMatrix().loadMatrix(MathUtils.createViewMatrix(Engine.getCamera()));
		shader.getLightPosition().loadVec3(new Vector3f(128,100,128));
	}
	
	private static void finishRendering() {
		shader.stop();
		OpenGL.disableCull();
	}
	
	private static void prepareModel(Vao model) {
		model.bind(0,1,2);
		OpenGL.enableVertexAttribArrays(0,1,2);
	}
	
	private static void unbindModel() {
		GL30.glBindVertexArray(0);
		OpenGL.disableVertexAttribArrays(0,1,2);
	}
	
	private static void prepareInstance(TerrainChunk entity) {
		float entityX = (entity.getPosition().x - entity.getIndexX() * 64) - entity.getIndexX();
		float entityZ = (entity.getPosition().z - entity.getIndexZ() * 64) - entity.getIndexZ();
		shader.getTransformationMatrix().loadMatrix(MathUtils.createTransformationMatrix(new Vector3f(entityX, 0, entityZ), 0, 0, 0, 1));
	}

	
}
