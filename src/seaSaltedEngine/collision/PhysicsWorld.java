package seaSaltedEngine.collision;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;

public class PhysicsWorld extends DiscreteDynamicsWorld {

	public PhysicsWorld(Dispatcher dispatcher, BroadphaseInterface pairCache, ConstraintSolver constraintSolver,
			CollisionConfiguration collisionConfiguration) {
		super(dispatcher, pairCache, constraintSolver, collisionConfiguration);
	}

}
