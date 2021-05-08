package seaSaltedEngine.collision;

import javax.vecmath.Vector3f;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorldType;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;

public class PhysicsManager extends DynamicsWorld {

	public PhysicsManager(Dispatcher dispatcher, BroadphaseInterface broadphasePairCache, CollisionConfiguration collisionConfiguration) {
		super(dispatcher, broadphasePairCache, collisionConfiguration);
	}

	@Override
	public void addRigidBody(RigidBody arg0) {
		
	}

	@Override
	public void clearForces() {
		
	}

	@Override
	public void debugDrawWorld() {
		
	}

	@Override
	public ConstraintSolver getConstraintSolver() {
		return null;
	}

	@Override
	public Vector3f getGravity(Vector3f arg0) {
		return null;
	}

	@Override
	public DynamicsWorldType getWorldType() {
		return DynamicsWorldType.SIMPLE_DYNAMICS_WORLD;
	}

	@Override
	public void removeRigidBody(RigidBody arg0) {
		
	}

	@Override
	public void setConstraintSolver(ConstraintSolver arg0) {
		
	}

	@Override
	public void setGravity(Vector3f arg0) {
		
	}

	@Override
	public int stepSimulation(float arg0, int arg1, float arg2) {
		return 0;
	}

}
