package seaSaltedEngine.tools;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

import seaSaltedEngine.tools.math.Vector3f;

public class OpenGL {

	public static void enableCull() {
		GL11.glEnable(GL11.GL_CULL_FACE); 
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public static void disableCull() {
		GL11.glDisable(GL11.GL_CULL_FACE); 
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public static void enableFrontCull() {
		GL11.glEnable(GL11.GL_CULL_FACE); 
		GL11.glCullFace(GL11.GL_FRONT);
	}
	
	public static void disableFrontCull() {
		GL11.glDisable(GL11.GL_CULL_FACE); 
		GL11.glCullFace(GL11.GL_FRONT);
	}
	
	public static void setDepthTest(boolean value) {
		if(value)
			GL11.glEnable(GL_DEPTH_TEST); 
		if(!value)
			GL11.glDisable(GL_DEPTH_TEST);
	}
	
	public static void setPolygonFill() {
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
	}
	
	public static void setPolygonWire() {
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
	}
	
	public static void refreshGL() {
		glEnable(GL_DEPTH_TEST);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void enableVertexAttribArrays(int... bind) {
		for(int vertexArray : bind) {
			GL20.glEnableVertexAttribArray(vertexArray);
		}
	}
	
	public static void disableVertexAttribArrays(int... bind) {
		for(int vertexArray : bind) {
			GL20.glDisableVertexAttribArray(vertexArray);
		}
	}
	
	public static void clearColor() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public static void clearDepth() {
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public static void activateTexture(int texture) {
		GL13.glActiveTexture(GL13.GL_TEXTURE+texture); 
	}
	
	public static void clearColor(Vector3f color, float a) {
		GL11.glClearColor(color.x, color.y, color.z, a);
	}
	
	public static void detachAndRemoveShaders(int vert, int frag, int programID) {
		GL20.glDetachShader(programID, vert);
		GL20.glDetachShader(programID, frag);
		GL20.glDeleteShader(vert);
		GL20.glDeleteShader(frag);
	}
	
}
