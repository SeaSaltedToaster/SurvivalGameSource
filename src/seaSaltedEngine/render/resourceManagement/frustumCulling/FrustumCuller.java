package seaSaltedEngine.render.resourceManagement.frustumCulling;

import seaSaltedEngine.Engine;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.entity.component.FrustumCullComponent;
import seaSaltedEngine.tools.math.Vector3f;
import seaSaltedEngine.tools.math.Vector4f;

public class FrustumCuller {
	
  private static final int NUM_PLANES = 5;
  private Frustum frustum;
  private Plane[] planes = new Plane[5];
  
  public FrustumCuller() {
    this.frustum = new Frustum();
    initPlanes();
    constructPlanes();
  }

  public static boolean checkRender(Entity entity) {
	  FrustumCullComponent component = (FrustumCullComponent) entity.getComponent("FrustumCull");
	  return Engine.getRenderer().getCuller().isInFrustum(entity.getTransform().getPosition(), component.getRadius());
  }
  
  public void update() {
    this.frustum.update();
    constructPlanes();
  }
  
  public void update(Vector4f[] frustumVertices) {
    this.frustum.update(frustumVertices);
    constructPlanes(); 
  } 
  
  public boolean isInFrustum(Vector3f center, float radius) {
    byte b;
    int i;
    Plane[] arrayOfPlane;
    for (i = (arrayOfPlane = this.planes).length, b = 0; b < i; ) { Plane plane = arrayOfPlane[b];
      if (plane.getSignedDistance(center) < -radius)
        return false; 
      b++; }
    
    return true;
  }
  
  public boolean isInFrustum(Vector3f mins, Vector3f maxs) {
    for (int i = 0; i < 5; i++) {
      Plane plane = this.planes[i];
      float pX = ((plane.getNormal()).x > 0.0F) ? maxs.x : mins.x;
      float pY = ((plane.getNormal()).y > 0.0F) ? maxs.y : mins.y;
      float pZ = ((plane.getNormal()).z > 0.0F) ? maxs.z : mins.z;
      float pointDistance = (plane.getNormal()).x * pX + (plane.getNormal()).y * pY + (plane.getNormal()).z * pZ;
      if (pointDistance < -plane.getConstant()) {
        return false;
      }
    } 
    return true;
  }
  
  private void initPlanes() {
    for (int i = 0; i < this.planes.length; i++) {
      this.planes[i] = new Plane();
    }
  }
  
  private void constructPlanes() {
    this.planes[0].setPlane(this.frustum.getVertex(0), this.frustum.getVertex(4), this.frustum.getVertex(5));
    this.planes[1].setPlane(this.frustum.getVertex(0), this.frustum.getVertex(2), this.frustum.getVertex(6));
    this.planes[2].setPlane(this.frustum.getVertex(1), this.frustum.getVertex(3), this.frustum.getVertex(2));
    this.planes[3].setPlane(this.frustum.getVertex(7), this.frustum.getVertex(6), this.frustum.getVertex(2));
    this.planes[4].setPlane(this.frustum.getVertex(1), this.frustum.getVertex(5), this.frustum.getVertex(7));
  }


public static int getNumPlanes() {
	return NUM_PLANES;
}
}
