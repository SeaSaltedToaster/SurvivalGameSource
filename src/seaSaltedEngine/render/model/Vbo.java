package seaSaltedEngine.render.model;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL33;

public class Vbo {

	private final int vboId;
	private final int type;
	
	public Vbo(int vboId, int type){
		this.vboId = vboId;
		this.type = type;
	}
	
	public int getVboId() {
		return vboId;
	}

	public int getType() {
		return type;
	}

	public static Vbo create(int type){
		int id = GL15.glGenBuffers();
		return new Vbo(id, type);
	}
	
	public void bind(){
		GL15.glBindBuffer(type, vboId);
	}
	
	public void unbind(){
		GL15.glBindBuffer(type, 0);
	}
	
	public void storeData(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		storeData(buffer);
	}

	public void storeData(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		storeData(buffer);
	}
	
	public void storeData(IntBuffer data){
		GL15.glBufferData(type, data, GL15.GL_STATIC_DRAW);
	}
	
	public void storeData(FloatBuffer data){
		GL15.glBufferData(type, data, GL15.GL_STATIC_DRAW);
	}

	public void delete(){
		GL15.glDeleteBuffers(vboId);
	}
	
	public static Vbo createEmpty(int floatCount) {
		int vboid = GL15.glGenBuffers();
		Vbo vbo = new Vbo(vboid, GL15.GL_ARRAY_BUFFER);
		return vbo;
	}
	
	public void addInstancedAttrib(Vao vao, Vbo vbo, int attrib, int dataSize, int length, int offset) {
		vao.bind(); vbo.bind();
		GL20.glVertexAttribPointer(attrib, dataSize, GL15.GL_FLOAT, false, length, offset);	
		GL33.glVertexAttribDivisor(attrib, 1);
		vao.unbind(); vbo.unbind();
	}
	
}
