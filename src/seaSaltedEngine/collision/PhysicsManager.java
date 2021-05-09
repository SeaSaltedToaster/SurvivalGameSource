package seaSaltedEngine.collision;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.Transform;

import seaSaltedEngine.collision.objects.CollisionPlane;
import seaSaltedEngine.collision.objects.CollisionSphere;
import seaSaltedEngine.entity.Entity;
import seaSaltedEngine.render.display.Window;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.entity.EntityPickaxeTest;
import survivalGame.world.GameWorld;

public class PhysicsManager {

	private BroadphaseInterface broadphase = new DbvtBroadphase();
    private DefaultCollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
    private CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
    private SequentialImpulseConstraintSolver solver = new SequentialImpulseConstraintSolver();

    public static DiscreteDynamicsWorld dynamicsWorld;
    
    CollisionSphere fallShape;
    CollisionPlane plane;
    Entity entity;
    
    public PhysicsManager() {
    	dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
    	dynamicsWorld.setGravity(new javax.vecmath.Vector3f(0, -9.8f, 0));
    	
    	fallShape = new CollisionSphere(new seaSaltedEngine.tools.math.Vector3f(0,100,0),1,1, dynamicsWorld);
    	plane = new CollisionPlane(new Vector3f(0,0,0), 10, dynamicsWorld);
  
    	entity = new EntityPickaxeTest(seaSaltedEngine.basic.objects.Transform.Default);
    	GameWorld.getMainWorldEntityBatch().getEntities().add(entity);
    }
    
    public void updateTest() {
    	dynamicsWorld.stepSimulation((float)Window.getDelta(), 10); 

    	Transform trans = new Transform();
        fallShape.getPhysicsObject().getMotionState().getWorldTransform(trans);
        
        entity.getTransform().setPosition(new Vector3f(trans.origin.x,trans.origin.y,0));
    }

	public static DiscreteDynamicsWorld getDynamicsWorld() {
		return dynamicsWorld;
	}

}
