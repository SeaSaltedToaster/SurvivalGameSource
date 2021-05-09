package seaSaltedEngine.collision.objects;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;

import seaSaltedEngine.tools.math.Vector3f;

public class CollisionSphere {
	
	private CollisionShape shape;
	private RigidBody physicsObject;
	
	private float mass;
	
	public CollisionSphere(Vector3f position, float constant, float mass, DynamicsWorld world) {
		this.shape = new SphereShape(constant);
		this.mass = mass;
		this.physicsObject = createRigidbody(position.x, position.y, position.z);
		world.addRigidBody(physicsObject);
	}
	
	public RigidBody createRigidbody(float x, float y, float z) {
		DefaultMotionState fallMotionState = new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new javax.vecmath.Vector3f(x, y, z), 1.0f)));
		
		javax.vecmath.Vector3f fallInertia = new javax.vecmath.Vector3f(0,0,0);
		shape.calculateLocalInertia(mass, fallInertia);
		
		RigidBodyConstructionInfo fallRigidBodyCI = new RigidBodyConstructionInfo(mass,fallMotionState,shape,fallInertia);
		RigidBody fallRigidBody = new RigidBody(fallRigidBodyCI); 
	    return fallRigidBody;
	}

	public CollisionShape getShape() {
		return shape;
	}

	public RigidBody getPhysicsObject() {
		return physicsObject;
	}

	public void setShape(CollisionShape shape) {
		this.shape = shape;
	}

	public void setPhysicsObject(RigidBody physicsObject) {
		this.physicsObject = physicsObject;
	}

}
