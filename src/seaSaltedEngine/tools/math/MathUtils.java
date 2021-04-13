package seaSaltedEngine.tools.math;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.camera.Camera;
import seaSaltedEngine.render.display.WindowManager;

public class MathUtils {
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.setIdentity();
		transformationMatrix = transformationMatrix.multiply(Matrix4f.translate(translation.x, translation.y, translation.z));
		transformationMatrix = transformationMatrix.multiply(Matrix4f.rotate(rx, 1, 0, 0));
		transformationMatrix = transformationMatrix.multiply(Matrix4f.rotate(ry, 0, 1, 0));
		transformationMatrix = transformationMatrix.multiply(Matrix4f.rotate(rz, 0, 0, 1));
		transformationMatrix = transformationMatrix.multiply(Matrix4f.scale(scale, scale, scale));
		return transformationMatrix;
	}
	
	public static Matrix4f createViewMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		viewMatrix = viewMatrix.multiply(Matrix4f.rotate(camera.getPitch(), 1, 0, 0));
		viewMatrix = viewMatrix.multiply(Matrix4f.rotate(camera.getYaw(), 0, 1, 0));
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		viewMatrix  = viewMatrix.multiply(Matrix4f.translate(negativeCameraPos.x, negativeCameraPos.y, negativeCameraPos.z));
		return viewMatrix;
	}
	
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.setIdentity();
		transformationMatrix = transformationMatrix.multiply(Matrix4f.translate(translation.x, translation.y, 0));
		transformationMatrix = transformationMatrix.multiply(Matrix4f.scale(scale.x, scale.y, 0));
		return transformationMatrix;
	}
	
	public static Matrix4f createTransformationMatrix2(Vector2f translation, Vector2f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation.x, translation.y, 1f);
		Matrix4f.scale(scale.x, scale.y, 1f);
		return matrix;
	}
	
	 public static float cosInterpolate(float a, float b, float blend) {
		 double ft = blend * Math.PI;
		 float f = (float)((1.0D - Math.cos(ft)) * 0.5D);
		 return a * (1.0F - f) + b * f;
	 }
	
	 public static Matrix4f createProjectionMatrix(float FOV, float NEAR_PLANE, float FAR_PLANE) {
			Matrix4f projectionMatrix = new Matrix4f();
			float aspectRatio = (float) WindowManager.getWindowSizeX(Engine.getWindowInstance().getWindowID()) / (float) WindowManager.getWindowSizeY(Engine.getWindowInstance().getWindowID());
			float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV/2f))) * aspectRatio);
			float x_scale = y_scale / aspectRatio;
			float frustum_length = FAR_PLANE - NEAR_PLANE;
			projectionMatrix = new Matrix4f();
			projectionMatrix.m00 = x_scale;
			projectionMatrix.m11 = y_scale;
			projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
			projectionMatrix.m23 = -1;
			projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
			projectionMatrix.m33 = 0;
			return projectionMatrix;
		}
	
	public static float calculateVectorRotationY(Vector2f direction2D) {
		direction2D.normalize();
	    return -((float)Math.toDegrees(Math.atan2(direction2D.y, direction2D.x) - 1.5707963705062866D));
	}
	
//	public static Matrix4f createViewMatrix(Camera camera) {
//		Matrix4f viewMatrix = new Matrix4f();
//		viewMatrix.setIdentity();
//		viewMatrix = viewMatrix.multiply(Matrix4f.rotate(camera.getPitch(), 1, 0, 0));
//		viewMatrix = viewMatrix.multiply(Matrix4f.rotate(camera.getYaw(), 0, 1, 0));
//		Vector3f cameraPos = camera.getPosition();
//		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
//		viewMatrix  = viewMatrix.multiply(Matrix4f.translate(negativeCameraPos.x, negativeCameraPos.y, negativeCameraPos.z));
//		return viewMatrix;
//	}
	
	public static Vector3f coordinate(Vector3f vec1, Vector3f vec2)
	{
	    return new Vector3f(vec2.x - vec1.x, vec2.y - vec1.y, vec2.z - vec1.z);
	}
	
	public static float barryCentric(Vector3f p1, Vector3f p2, Vector3f p3, Vector2f pos) {
		float det = (p2.z - p3.z) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.z - p3.z);
		float l1 = ((p2.z - p3.z) * (pos.x - p3.x) + (p3.x - p2.x) * (pos.y - p3.z)) / det;
		float l2 = ((p3.z - p1.z) * (pos.x - p3.x) + (p1.x - p3.x) * (pos.y - p3.z)) / det;
		float l3 = 1.0f - l1 - l2;
		return l1 * p1.y + l2 * p2.y + l3 * p3.y;
	}

	public static float degreesToRadians(float degrees) {
		return degrees * 0.017453292F;
	}
	
}
