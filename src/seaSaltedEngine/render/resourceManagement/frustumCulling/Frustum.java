package seaSaltedEngine.render.resourceManagement.frustumCulling;

import seaSaltedEngine.Engine;
import seaSaltedEngine.EngineConstants;
import seaSaltedEngine.render.display.Window;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Matrix4f;
import seaSaltedEngine.tools.math.Vector2f;
import seaSaltedEngine.tools.math.Vector3f;
import seaSaltedEngine.tools.math.Vector4f;

public class Frustum {

	public static final int VERTEX_COUNT = 8;
	
	private Vector4f[] originalVertices = new Vector4f[VERTEX_COUNT];
	private Vector4f[] frustumVertices = new Vector4f[VERTEX_COUNT];

	private float frustumLength;
	
	private float farWidth, farHeight, nearWidth, nearHeight;
	
	private Matrix4f cameraTransform = new Matrix4f();

	public Frustum() {
		this.frustumLength = EngineConstants.FAR_PLANE;
		initFrusutmVertices();
		calculateOriginalVertices();
		update();
	}
	
	public void update() {
		updateCameraTransform();
		for (int i = 0; i < this.frustumVertices.length; i++) {
			Matrix4f.transform(this.cameraTransform, this.originalVertices[i], this.frustumVertices[i]);
		}
	}

	public void update(float limitedDistance) {
		if (this.frustumLength != limitedDistance) {
			this.frustumLength = limitedDistance;
			calculateOriginalVertices();
		} 
		update();
	}

	public void update(Vector4f[] newVertices) {
		this.frustumVertices = newVertices;
	}

	public Vector3f getVertex(int i) {
		return new Vector3f(this.frustumVertices[i]);
	}
	
	private void calculateWidthsAndHeights() {
		this.farHeight = (float)(this.frustumLength * Math.tan(Math.toRadians((35) / 2.0F)));
		this.nearHeight = (float)(EngineConstants.NEAR_PLANE * Math.tan(Math.toRadians(((35) / 2.0F))));
		this.farWidth = this.farHeight * Window.getAspectRatio();
		this.nearWidth = this.nearHeight * Window.getAspectRatio();
	}

	private void calculateOriginalVertices() {
		calculateWidthsAndHeights();
		for (int i = 0; i < this.originalVertices.length; i++) {
			this.originalVertices[i] = getVertex((i / 4 % 2 == 0), (i % 2 == 0), (i / 2 % 2 == 0));
		}
	}

	private Vector4f getVertex(boolean isNear, boolean positiveX, boolean positiveY) {
		Vector4f vertex = new Vector4f();
	    vertex.z = isNear ? -EngineConstants.NEAR_PLANE : -this.frustumLength;
	    Vector2f sizes = isNear ? new Vector2f(this.nearWidth, this.nearHeight) : new Vector2f(this.farWidth, this.farHeight);
	    vertex.x = positiveX ? sizes.x : -sizes.x;
	    vertex.y = positiveY ? sizes.y : -sizes.y;
	    vertex.w = 1.0F;
	    return vertex;
	}
	  
	  private void initFrusutmVertices() {
	    for (int i = 0; i < this.frustumVertices.length; i++) {
	      this.frustumVertices[i] = new Vector4f();
	    }
	  }
	  
	  private void updateCameraTransform() {
	    this.cameraTransform.setIdentity();
	    this.cameraTransform.translate(Engine.getCamera().getPosition(), this.cameraTransform);
	    this.cameraTransform.rotate(MathUtils.degreesToRadians(Engine.getCamera().getYaw()), new Vector3f(0.0F, 1.0F, 0.0F));
	    this.cameraTransform.rotate(MathUtils.degreesToRadians(Engine.getCamera().getPitch()), new Vector3f(1.0F, 0.0F, 0.0F));
	  }
	
}
