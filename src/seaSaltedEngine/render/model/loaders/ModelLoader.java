package seaSaltedEngine.render.model.loaders;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import seaSaltedEngine.render.model.Vao;

public class ModelLoader {

	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	
	public Vao loadToVAO(float[] pos, float[] colors, float[] normals, int[] indices) {
		int vaoID = createVAO();
		Vao vao = new Vao(vaoID);
		vao.createIndexBuffer(indices);
		vao.createAttribute(0, pos, 3);
		vao.createAttribute(1, colors, 3);
		vao.createAttribute(2, normals, 3);
		vao.unbind(0,1,2);
		return vao;
	}
	
	public Vao loadToVAO(float[] positions, int dimensions){
		int vaoID = createVAO();
		storeDataInAttributeList(0, dimensions, positions);
		unbindVAO();
		return new Vao(vaoID);
	}
	
	public Vao loadToVAO(float[] positions, float[] textureCoords) {
        int vaoID = createVAO();
        Vao vao = new Vao(vaoID);
        vao.createAttribute(0, positions, 2);
        vao.createAttribute(1, textureCoords, 2);
        vao.unbind(0,1);
        return vao;
    }
	
	public void cleanUp() {
		for (Integer x : vaos)
			GL30.glDeleteVertexArrays(x);
		for (Integer x : vbos)
			GL15.glDeleteBuffers(x);
	}
	
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		vaos.add(vaoID);
		return vaoID;
	}
	
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
}
