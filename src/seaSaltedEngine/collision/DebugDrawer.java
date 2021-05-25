package seaSaltedEngine.collision;

import javax.vecmath.Vector3f;

import com.bulletphysics.linearmath.IDebugDraw;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class DebugDrawer extends IDebugDraw {

	@Override
	public void draw3dText(Vector3f arg0, String arg1) {
		
	}

	@Override
	public void drawContactPoint(Vector3f arg0, Vector3f arg1, float arg2, int arg3, Vector3f arg4) {
		
	}

	@Override
	public void drawLine(Vector3f arg0, Vector3f arg1, Vector3f arg2) {
		GL11.glBegin(GL13.GL_LINES);
			GL13.glVertex2f(10, 10);
			GL13.glVertex2f(20, 20);
			GL13.glColor3f(arg2.x, arg2.y, arg2.z);
		GL13.glEnd();
	}

	@Override
	public int getDebugMode() {
		return 0;
	}

	@Override
	public void reportErrorWarning(String arg0) {
		
	}

	@Override
	public void setDebugMode(int arg0) {
		
	}

}
