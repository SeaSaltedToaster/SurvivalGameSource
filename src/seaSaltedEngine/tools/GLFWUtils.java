package seaSaltedEngine.tools;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_DECORATED;
import static org.lwjgl.glfw.GLFW.GLFW_FOCUSED;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_DONT_CARE;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class GLFWUtils {

	public static void init() {
		glfwInit();
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_SAMPLES, GL_DONT_CARE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_DECORATED, GL_TRUE);
		glfwWindowHint(GLFW_FOCUSED, GL_TRUE);
	}
	
	private static long variableYieldTime;
	private static long lastTime;
	
	public static void sync(int fps) {
	    if (fps <= 0) return;
	     
	    long sleepTime = 1000000000 / fps;
	    long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000*1000));
	    long overSleep = 0;
	     
	    try {
	        while (true) {
	            long t = System.nanoTime() - lastTime;
	             
	            if (t < sleepTime - yieldTime) {
	                Thread.sleep(1);
	            }else if (t < sleepTime) {
	                Thread.yield();
	            }else {
	                overSleep = t - sleepTime;
	                break;
	            }
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }finally{
	        lastTime = System.nanoTime() - Math.min(overSleep, sleepTime);
	       
	        if (overSleep > variableYieldTime) {
	            variableYieldTime = Math.min(variableYieldTime + 200*1000, sleepTime);
	        }
	        else if (overSleep < variableYieldTime - 200*1000) {
	            variableYieldTime = Math.max(variableYieldTime - 2*1000, 0);
	        }
	    }
	}

	
}
