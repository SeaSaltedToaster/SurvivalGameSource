package seaSaltedEngine.collision.objects;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;

import seaSaltedEngine.tools.math.Vector3f;

public class CollisionPlane {

	private CollisionShape groundShape;
	private RigidBody physicsObject;
	
	public CollisionPlane(Vector3f position, float constant, DynamicsWorld world) {
		groundShape = new StaticPlaneShape(new javax.vecmath.Vector3f(position.x, position.y, position.z), constant);
		physicsObject = createRigidbody(position);
		world.addRigidBody(physicsObject);
	}
	
	public RigidBody createRigidbody(Vector3f position) {
		DefaultMotionState groundMotionState = new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new javax.vecmath.Vector3f(position.x, position.y, position.z), 1.0f))); 
		RigidBodyConstructionInfo groundRigidBodyCI = new RigidBodyConstructionInfo(0, groundMotionState, groundShape, new javax.vecmath.Vector3f(0,0,0)); 
	    RigidBody groundRigidBody = new RigidBody(groundRigidBodyCI); 
	    return groundRigidBody;
	}

	public CollisionShape getGroundShape() {
		return groundShape;
	}

	public void setGroundShape(CollisionShape groundShape) {
		this.groundShape = groundShape;
	}
	
}
