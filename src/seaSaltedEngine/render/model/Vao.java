package seaSaltedEngine.render.model;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Vao {

	private static final int BYTES_PER_FLOAT = 4;
	private static final int BYTES_PER_INT = 4;
	
	public final int id;
	private int indexCount;
	
	private Vbo indexVbo;
	private List<Vbo> dataVbos = new ArrayList<Vbo>();
 
	public static Vao create() {
		int id = GL30.glGenVertexArrays();
		return new Vao(id);
	}

	public Vao(int id) {
		this.id = id;
	}
	
	public int getIndexCount(){
		return indexCount;
	}

	public void bind(int... attributes){
		bind();
		for (int i : attributes) {
			GL20.glEnableVertexAttribArray(i);
		}
	}

	public void unbind(int... attributes){
		for (int i : attributes) {
			GL20.glDisableVertexAttribArray(i);
		}
		unbind();
	}
	
	public void render() {
		bind(0,1,2);
		GL11.glDrawElements(GL11.GL_TRIANGLES, getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
		unbind(0,1,2);
	}
	
	public void createIndexBuffer(int[] indices){
		this.indexVbo = Vbo.create(GL15.GL_ELEMENT_ARRAY_BUFFER);
		indexVbo.bind();
		indexVbo.storeData(indices);
		this.indexCount = indices.length;
	}

	public void createAttribute(int attribute, float[] data, int attrSize){
		Vbo dataVbo = Vbo.create(GL15.GL_ARRAY_BUFFER);
		dataVbo.bind();
		dataVbo.storeData(data);
		GL20.glVertexAttribPointer(attribute, attrSize, GL11.GL_FLOAT, false, attrSize * BYTES_PER_FLOAT, 0);
		dataVbo.unbind();
		dataVbos.add(dataVbo);
	}
	
	public void createIntAttribute(int attribute, int[] data, int attrSize){
		Vbo dataVbo = Vbo.create(GL15.GL_ARRAY_BUFFER);
		dataVbo.bind();
		dataVbo.storeData(data);
		GL30.glVertexAttribIPointer(attribute, attrSize, GL11.GL_INT, attrSize * BYTES_PER_INT, 0);
		dataVbo.unbind();
		dataVbos.add(dataVbo);
	}
	
	public void delete() {
		GL30.glDeleteVertexArrays(id);
		for(Vbo vbo : dataVbos){
			vbo.delete();
		}
		indexVbo.delete();
	}

	private void bind() {
		GL30.glBindVertexArray(id);
	}

	private void unbind() {
		GL30.glBindVertexArray(0);
	}
	
}
