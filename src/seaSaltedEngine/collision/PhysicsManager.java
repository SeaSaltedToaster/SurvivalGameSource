package seaSaltedEngine.collision;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.render.display.Window;

public class PhysicsManager {

	private static BroadphaseInterface broadphase = new DbvtBroadphase();
    private static SequentialImpulseConstraintSolver solver = new SequentialImpulseConstraintSolver();
    
    private static DefaultCollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
    private static CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);

    public static DiscreteDynamicsWorld dynamicsWorld;
    
    public static void init() {
    	dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
    	dynamicsWorld.setGravity(new javax.vecmath.Vector3f(0, -9.8f, 0));
    	
    	Logger.Log("Initialized JBullet Physics Engine");
    }
    
    public static void updateTest() {
    	dynamicsWorld.stepSimulation((float)Window.getDelta(), 10); 
    }

	public static DiscreteDynamicsWorld getDynamicsWorld() {
		return dynamicsWorld;
	}

}
