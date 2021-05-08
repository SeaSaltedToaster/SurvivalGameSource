package survivalGame.world.dualContouring;

import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.dualContouring.octree.OctreeDrawInfo;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.dualContouring.octree.OctreeNodeType;
import survivalGame.world.dualContouring.qef.LevenQefSolver;
import survivalGame.world.dualContouring.qef.QEFData;
import survivalGame.world.terrain.TerrainChunk;
import survivalGame.world.terrain.blocks.VoxelTerrainGenerator;

import static survivalGame.world.dualContouring.DualContouring.*;
import static survivalGame.world.dualContouring.octree.OctreeNodeType.Node_Leaf;

public class OctreeBuilder {
	
	//Generates array of voxels
	protected static VoxelTerrainGenerator generator;
	
	//Base function called to generate octree
	public static OctreeNode BuildOctree(Vector3f min, int size, TerrainChunk chunk) {
        OctreeNode root = new OctreeNode(min, size, OctreeNodeType.Node_Internal);
        generator = new VoxelTerrainGenerator();
        generator.generate();
        root = ConstructOctreeNodes(root, chunk);
        return root;
    }

	//Method goes through every node until the size is equal to 1
	private static OctreeNode ConstructOctreeNodes(OctreeNode node, TerrainChunk chunk) {
        if (node == null) return null;
        
        if (node.size == 1)
            return ConstructLeaf(node, chunk);

	    int childSize = node.size / 2;
        for (int i = 0; i < 8; i++) {
            Vector3f childMin = node.min.add(CHILD_MIN_OFFSETS[i].mul(childSize));
            OctreeNode child = new OctreeNode(childMin, childSize, OctreeNodeType.Node_Internal);
            node.children[i] = ConstructOctreeNodes(child, chunk);
        }

        return node;
    }
	
	//Only called for nodes with a size of 1 (Leaf)
	private static OctreeNode ConstructLeaf(OctreeNode leaf, TerrainChunk chunk) {
        if (leaf == null || leaf.size != 1) return null;
        
        int corners = 0;
        for (int i = 0; i < 8; i++) {
            Vector3f cornerPos = leaf.min.add(CHILD_MIN_OFFSETS[i]);

            float density = getDensity(cornerPos, chunk);
            
		    int material = density < 0.f ? MATERIAL_SOLID : MATERIAL_AIR;
		    
            corners |= (material << i);
        }

        if (corners == 0 || corners == 255) {
            return null;    // voxel is full inside or outside the volume
        }

        // otherwise the voxel contains the surface, so find the edge intersections
	    int MAX_CROSSINGS = 12;
        int edgeCount = 0;
        Vector3f averageNormal = new Vector3f();
        QEFData qef = new QEFData(new LevenQefSolver());

        for (int i = 0; i < 12 && edgeCount < MAX_CROSSINGS; i++) {
		    int c1 = edgevmap[i][0];
		    int c2 = edgevmap[i][1];

		    int m1 = (corners >> c1) & 1;
		    int m2 = (corners >> c2) & 1;

            if ((m1 == MATERIAL_AIR && m2 == MATERIAL_AIR) || (m1 == MATERIAL_SOLID && m2 == MATERIAL_SOLID)) {
                continue; // no zero crossing on this edge
            }

            Vector3f p1 = leaf.min.add(CHILD_MIN_OFFSETS[c1]);
            Vector3f p2 = leaf.min.add(CHILD_MIN_OFFSETS[c2]);
            Vector3f p = ApproximateZeroCrossingPosition(p1, p2, chunk);
            Vector3f n = CalculateSurfaceNormal(p, p1, p2, p1);
            qef.qef_add_point(p, n);
            averageNormal = averageNormal.add(n);
            edgeCount++;
        }

        OctreeDrawInfo drawInfo = new OctreeDrawInfo();
        drawInfo.position = qef.solve();
        drawInfo.qef = qef;
        drawInfo.averageNormal = averageNormal.div((float)edgeCount).normalize();
        drawInfo.averageNormal.normalize();
        drawInfo.corners = corners;

        leaf.Type = Node_Leaf;
        leaf.drawInfo = drawInfo;
        
        return leaf;
    }
	
	private static float getDensity(Vector3f cornerPos, TerrainChunk chunk) {
		float density = 0;
		int x = (int) (Math.abs(cornerPos.x) - (Math.abs(chunk.getTransform().getIndexX()-1) * 64));
		int y = (int) (Math.abs(cornerPos.y));
		int z = (int) (Math.abs(cornerPos.z) - (Math.abs(chunk.getTransform().getIndexY()-1) * 64));
		density = generator.getBlockMap()[Math.abs(x)][Math.abs(y)][Math.abs(z)] * 2 - 1;
		return density;
	}
	
	private static Vector3f ApproximateZeroCrossingPosition(Vector3f p0, Vector3f p1, TerrainChunk chunk) {
        // approximate the zero crossing by finding the min value along the edge
        float minValue = 100000.f;
        float t = 0.f;
        float currentT = 0.f;
        int steps = 8;
        float increment = 1.f / (float)steps;
        while (currentT <= 1.f)
        {
            Vector3f p = p0.add(p1.subtract(p0).mul(currentT)); // p = p0 + ((p1 - p0) * currentT);
            
            float density = Math.abs(getDensity(p, chunk));
            
            if (density < minValue) {
                minValue = density;
                t = currentT;
            }
            currentT += increment;
        }
        return p0.add((p1.subtract(p0)).mul(t)); // p0 + ((p1 - p0) * t);
    }

    public static Vector3f CalculateSurfaceNormal(Vector3f p, Vector3f a, Vector3f b, Vector3f c) {
        float H = 1f;
        Vector3f xOffcet = new Vector3f(H, 0.f, 0.f);
        Vector3f yOffcet = new Vector3f(0.f, H, 0.f);
        Vector3f zOffcet = new Vector3f(0.f, 0.f, H);
        float dx = SimplexNoise.Sample(p.add(xOffcet)) - SimplexNoise.Sample(p.subtract(xOffcet));
        float dy = SimplexNoise.Sample(p.add(yOffcet)) - SimplexNoise.Sample(p.subtract(yOffcet));
        float dz = SimplexNoise.Sample(p.add(zOffcet)) - SimplexNoise.Sample(p.subtract(zOffcet));

        Vector3f v = new Vector3f(dx, dy, dz);
        v.normalize();
        return v;
    }
    
    public static final int[][] edgevmap = {
            {0,4},{1,5},{2,6},{3,7},
            {0,2},{1,3},{4,6},{5,7},
            {0,1},{2,3},{4,5},{6,7}
    };
    
    static final Vector3f[] CHILD_MIN_OFFSETS = {
            new Vector3f( 0, 0, 0 ),
            new Vector3f( 0, 0, 1 ),
            new Vector3f( 0, 1, 0 ),
            new Vector3f( 0, 1, 1 ),
            new Vector3f( 1, 0, 0 ),
            new Vector3f( 1, 0, 1 ),
            new Vector3f( 1, 1, 0 ),
            new Vector3f( 1, 1, 1 ),
    };
	
}
