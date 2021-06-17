package seaSaltedEngine.tools;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.camera.Camera;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.render.display.WindowManager;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Matrix4f;
import seaSaltedEngine.tools.math.Vector2f;
import seaSaltedEngine.tools.math.Vector3f;
import seaSaltedEngine.tools.math.Vector4f;

public class MousePicker {

	private Vector3f currentRay = new Vector3f();

	private Matrix4f viewMatrix;
	private Camera camera;
	
	private Vector3f currentTerrainPoint;

	public MousePicker(Camera cam) {
		camera = cam;
		viewMatrix = MathUtils.createViewMatrix(camera);
	}
	
	public Vector3f getCurrentTerrainPoint() {
		return currentTerrainPoint;
	}

	public Vector3f getCurrentRay() { 
		return currentRay;
	}
	
	public Vector3f getCurrentCamRay() {
		Vector3f ray = currentRay;
		ray.x = ray.x;
		ray.y = Engine.getCamera().getLookVec().y;
		ray.z = ray.z;
		return ray;
	}
	
	public Vector3f getCurrentIntRay(Vector3f pos, int ray) {
		Vector3f position = pos;
		for(int i = 0; i < ray; i++) {
			if(i == 0) {position = position.add(new Vector3f(currentRay.x,Engine.getCamera().getLookVec().y/3,currentRay.z)); continue; }
			position = position.add(new Vector3f(currentRay.x,0,currentRay.z));
		}
		return position;
	}

	public void update() {
		viewMatrix = MathUtils.createViewMatrix(camera);
		currentRay = calculateMouseRay();
	}
	
	public boolean isColliding(Vector3f entity, Vector3f cam) {
		for(int i = 0; i < 20; i++) {
			if(getCurrentIntRay(cam,i).toString().equals(entity.toString())) {
				return true;
			}
		}
		return false;
	}

	private Vector3f calculateMouseRay() {
		float mouseX = (float) Mouse.getMousePosition().x;
		float mouseY = (float) -Mouse.getMousePosition().y;
//		float mouseX = -0.5f;
//		float mouseY = 0.5f;
		Vector2f normalizedCoords = getNormalisedDeviceCoordinates(mouseX, mouseY);
		Vector4f clipCoords = new Vector4f(normalizedCoords.x, normalizedCoords.y, -1.0f, 1.0f);
		Vector4f eyeCoords = toEyeCoords(clipCoords);
		Vector3f worldRay = toWorldCoords(eyeCoords);
		return worldRay;
	}

	private Vector3f toWorldCoords(Vector4f eyeCoords) {
		Matrix4f invertedView = Matrix4f.invert(viewMatrix, null);
		Vector4f rayWorld = Matrix4f.transform(invertedView, eyeCoords, null);
		Vector3f mouseRay = new Vector3f(-rayWorld.x, rayWorld.y, rayWorld.z);
		mouseRay.normalize();
		return mouseRay;
	}

	private Vector4f toEyeCoords(Vector4f clipCoords) {
		Matrix4f invertedProjection = Matrix4f.invert(Engine.getRenderer().getProjectionMatrix(), null);
		Vector4f eyeCoords = Matrix4f.transform(invertedProjection, clipCoords, null);
		return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);
	}

	private Vector2f getNormalisedDeviceCoordinates(float mouseX, float mouseY) {
		float x = (float) ((-2.0f * mouseX) / WindowManager.getWindowSizeX() - 1f);
		float y = (float) ((2.0f * mouseY) / WindowManager.getWindowSizeY() - 1f);
		return new Vector2f(x, y);
	}

}
