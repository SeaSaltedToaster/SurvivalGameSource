package seaSaltedEngine.collision;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
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

	private static BroadphaseInterface broadphase = new DbvtBroadphase();
    private static DefaultCollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
    private static CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
    private static SequentialImpulseConstraintSolver solver = new SequentialImpulseConstraintSolver();

    public static DiscreteDynamicsWorld dynamicsWorld;
    
    static CollisionSphere fallShape;
    static CollisionPlane plane;
    static Entity entity;
    
    public static void init() {
    	dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
    	dynamicsWorld.setGravity(new javax.vecmath.Vector3f(0, -9.8f, 0));
    	
    	fallShape = new CollisionSphere(new Vector3f(16,100,16),1,100, dynamicsWorld);
    	plane = new CollisionPlane(new Vector3f(0,0,0), 10, dynamicsWorld);
  
    	entity = new EntityPickaxeTest(new seaSaltedEngine.basic.objects.Transform(new Vector3f(0,0,0),0,0,0));
    	GameWorld.getMainWorldEntityBatch().getEntities().add(entity);
    	dynamicsWorld.setDebugDrawer(new DebugDrawer());
    }
    
    public static void updateTest() {
    	dynamicsWorld.stepSimulation((float)Window.getDelta(), 10); 

    	Transform trans = new Transform();
        fallShape.getPhysicsObject().getMotionState().getWorldTransform(trans);
        
        entity.getTransform().setPosition(new Vector3f(trans.origin.x,trans.origin.y,trans.origin.z));
//        dynamicsWorld.debugDrawObject(trans, fallShape.getShape(), new javax.vecmath.Vector3f(1,0,0));
    }

	public static DiscreteDynamicsWorld getDynamicsWorld() {
		return dynamicsWorld;
	}

}
