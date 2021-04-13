package seaSaltedEngine.render.shaders.objects;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import seaSaltedEngine.tools.math.Matrix4f;

public class UniformMatrix4f extends UniformMatrix {

	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

	public UniformMatrix4f(String name) {
		super(name);
	}
	
	public void loadMatrix(Matrix4f matrix){
		matrix.toBuffer(matrixBuffer);
		GL20.glUniformMatrix4fv(super.getLocation(), false, matrixBuffer);
	}
	
}
