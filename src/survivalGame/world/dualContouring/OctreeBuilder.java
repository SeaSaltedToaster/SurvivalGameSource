package survivalGame.world.dualContouring;

import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.world.dualContouring.octree.OctreeData;
import survivalGame.world.dualContouring.octree.OctreeDrawInfo;
import survivalGame.world.dualContouring.octree.OctreeNode;
import survivalGame.world.dualContouring.octree.OctreeNodeType;
import survivalGame.world.dualContouring.qef.LevenQefSolver;
import survivalGame.world.dualContouring.qef.QEFData;

import static survivalGame.world.dualContouring.DualContouring.*;
import static survivalGame.world.dualContouring.octree.OctreeNodeType.Node_Leaf;

public class OctreeBuilder {
	
	public static OctreeNode BuildOctree(Vector3f min, int size, OctreeData data) {
        OctreeNode root = new OctreeNode(min, size, OctreeNodeType.Node_Internal, data);
        root = ConstructOctreeNodes(root, data);
        return root;
    }

	private static OctreeNode ConstructOctreeNodes(OctreeNode node, OctreeData data) {
        if (node == null) {
            return null;
        }
        if (node.size == 1) {
            return ConstructLeaf(node);
        }

	    int childSize = node.size / 2;
        boolean hasChildren = false;

        for (int i = 0; i < 8; i++) {
            Vector3f childMin = node.min.add(CHILD_MIN_OFFSETS[i].mul(childSize));
            OctreeNode child = new OctreeNode(childMin, childSize, OctreeNodeType.Node_Internal, data);
            node.children[i] = ConstructOctreeNodes(child, data);
            hasChildren |= (node.children[i] != null);
        }

        if (!hasChildren) {
            return null;
        }

        return node;
    }
	
	private static OctreeNode ConstructLeaf(OctreeNode leaf) {
        if (leaf == null || leaf.size != 1) {
            return null;
        }

        int corners = 0;
        for (int i = 0; i < 8; i++) {
            Vector3f cornerPos = leaf.min.add(CHILD_MIN_OFFSETS[i]);
            
            float density = SimplexNoise.Sample(cornerPos); //IMPORTANTE THING POG
            
		    int material = density < 0.f ? MATERIAL_SOLID : MATERIAL_AIR;
		    
            corners |= (material << i);
        }

        if (corners == 0 || corners == 255) {
            return null;    // voxel is full inside or outside the volume
        }

        // otherwise the voxel contains the surface, so find the edge intersections
	    int MAX_CROSSINGS = 6;
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
            Vector3f p = ApproximateZeroCrossingPosition(p1, p2);
            Vector3f n = CalculateSurfaceNormal(p);
            qef.qef_add_point(p, n);
            averageNormal = averageNormal.add(n);
            edgeCount++;
        }

        OctreeDrawInfo drawInfo = new OctreeDrawInfo();
        drawInfo.position = qef.solve();
        drawInfo.qef = qef;
        drawInfo.averageNormal = averageNormal.div((float)edgeCount);//.normalize();
        drawInfo.averageNormal.normalize();
        drawInfo.corners = corners;

        leaf.Type = Node_Leaf;
        leaf.drawInfo = drawInfo;
        return leaf;
    }
	
	private static Vector3f ApproximateZeroCrossingPosition(Vector3f p0, Vector3f p1) {
        // approximate the zero crossing by finding the min value along the edge
        float minValue = 100000.f;
        float t = 0.f;
        float currentT = 0.f;
        int steps = 8;
        float increment = 1.f / (float)steps;
        while (currentT <= 1.f)
        {
            Vector3f p = p0.add(p1.subtract(p0).mul(currentT)); // p = p0 + ((p1 - p0) * currentT);
            
            float density = Math.abs(SimplexNoise.Sample(p));
            
            if (density < minValue) {
                minValue = density;
                t = currentT;
            }
            currentT += increment;
        }
        return p0.add((p1.subtract(p0)).mul(t)); // p0 + ((p1 - p0) * t);
    }

    public static Vector3f CalculateSurfaceNormal(Vector3f p) {
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
	
}
