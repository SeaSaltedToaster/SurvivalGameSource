package seaSaltedEngine.collision.mesh;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.BvhTriangleMeshShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;

public class CollisionMesh {
	
	private CollisionObject object;
	private RigidBody rigidBody;
	
	public CollisionMesh(BvhTriangleMeshShape shape, seaSaltedEngine.tools.math.Vector3f position) {
		object = new CollisionObject();
		object.setCollisionShape(shape);
		object.setWorldTransform(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(position.x, position.y, position.z), 1f)));
		
		rigidBody = createRigidbody(position.x, position.y, position.z);
	}
	
	public RigidBody createRigidbody(float x, float y, float z) {
		DefaultMotionState motionState = new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new javax.vecmath.Vector3f(x, y, z), 1.0f))); 
		
		RigidBodyConstructionInfo rigidBodyCI = new RigidBodyConstructionInfo(0, motionState, object.getCollisionShape(), new javax.vecmath.Vector3f(0,0,0)); 
	    RigidBody rigidBody = new RigidBody(rigidBodyCI);
	    return rigidBody;
	}

	public CollisionObject getObject() {
		return object;
	}

	public RigidBody getRigidBody() {
		return rigidBody;
	}

}
