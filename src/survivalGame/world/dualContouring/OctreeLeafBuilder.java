package survivalGame.world.dualContouring;

import static survivalGame.world.dualContouring.DcArrays.CHILD_MIN_OFFSETS;
import static survivalGame.world.dualContouring.DcArrays.edgevmap;

import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.entity.world.EntityCrystal;
import survivalGame.entity.world.EntityGrass;
import survivalGame.entity.world.EntityTree;
import survivalGame.world.dualContouring.octree.OctreeInfo;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.dualContouring.octree.OctreeNodeType;
import survivalGame.world.dualContouring.quadraticErrorFunction.LevenQefSolver;
import survivalGame.world.dualContouring.quadraticErrorFunction.QefData;
import survivalGame.world.terrain.TerrainChunk;

public class OctreeLeafBuilder {
	
	public static int MATERIAL_SOLID = 1, MATERIAL_AIR = 0;
	
	private static float densityFunc(TerrainChunk chunk, Vector3f position, float[][][] terrainMap) {
		float subX = Math.abs(Math.abs(chunk.getIndexX()) * 64);
		float subZ = Math.abs(Math.abs(chunk.getIndexZ()) * 64);
		return (float) terrainMap[(int) Math.abs(position.x-subX) ][(int) position.y ][(int) Math.abs(position.z-subZ) ] - 5;
	}

	public static OctreeNode ConstructLeaf(OctreeNode leaf, TerrainChunk chunk) {
        if (leaf == null || leaf.getSize() != 1) return null;
        
        int corners = 0;
        float[] cube = new float[8];
        for (int i = 0; i < 8; i++) {
            Vector3f cornerPos = leaf.getPosition().add(CHILD_MIN_OFFSETS[i]);

            float density = densityFunc(chunk, cornerPos, chunk.getTerrainMap());
            
		    int material = density < 0.f ? MATERIAL_SOLID : MATERIAL_AIR;
		    
		    cube[i] = density;
            corners |= (material << i);
        }

        if (corners == 0 || corners == 255) return null;
        
        if(corners > 1 && leaf.getPosition().y > 35 && Math.random() > 0.8f) { //TODO Tidy in other method or class
        	chunk.addObject(new EntityGrass(new Transform(leaf.getPosition(),0,0,0)), 1f);
        }
        if(corners > 1 && leaf.getPosition().y > 40 && Math.random() > 0.999f) {
        	chunk.addObject(new EntityTree(new Transform(leaf.getPosition(),0,0,0)), 5f);
        }
        if(corners > 1 && leaf.getPosition().y < 35 && Math.random() > 0.999f) {
        	chunk.addObject(new EntityCrystal(new Transform(leaf.getPosition(),0,0,0)), 5f);
        }

        // otherwise the voxel contains the surface, so find the edge intersections
	    int MAX_CROSSINGS = 12;
        int edgeCount = 0;
        Vector3f averageNormal = new Vector3f();
        QefData qef = new QefData(new LevenQefSolver());

        for (int i = 0; i < 12 && edgeCount < MAX_CROSSINGS; i++) {
		    int c1 = edgevmap[i][0];
		    int c2 = edgevmap[i][1];

		    int m1 = (corners >> c1) & 1;
		    int m2 = (corners >> c2) & 1;

            if ((m1 == MATERIAL_AIR && m2 == MATERIAL_AIR) || (m1 == MATERIAL_SOLID && m2 == MATERIAL_SOLID)) {
                continue; // no zero crossing on this edge
            }

            Vector3f p1 = leaf.getPosition().add(CHILD_MIN_OFFSETS[c1]);
            Vector3f p2 = leaf.getPosition().add(CHILD_MIN_OFFSETS[c2]);
            Vector3f p = ApproximateZeroCrossingPosition(p1, p2, chunk);
            Vector3f n = new Vector3f(1);
            qef.qef_add_point(p, n);
            averageNormal = averageNormal.add(n);
            edgeCount++;
        }

        OctreeInfo drawInfo = new OctreeInfo(0,corners, new Vector3f(0,1,0));
        leaf.setPosition(qef.solve().getVec3f());
        leaf.setNodeType(OctreeNodeType.Node_Leaf);
        leaf.setNodeInfo(drawInfo);
        
        return leaf;
    }
	
	private static Vector3f ApproximateZeroCrossingPosition(Vector3f p0, Vector3f p1, TerrainChunk chunk) {
        // approximate the zero crossing by finding the min value along the edge
        float minValue = 100000f;
        float t = 0.f;
        float currentT = 0.f;
        int steps = 8;
        float increment = 1.f / (float)steps;
        while (currentT <= 1.f)
        {
            Vector3f p = p0.add(p1.subtract(p0).mul(currentT)); // p = p0 + ((p1 - p0) * currentT);
            
            float density = Math.abs(densityFunc(chunk, p, chunk.getTerrainMap()));
            
            if (density < minValue) {
                minValue = density;
                t = currentT;
            }
            currentT += increment;
        }
        return p0.add((p1.subtract(p0)).mul(t)); // p0 + ((p1 - p0) * t);
    }
	
}
