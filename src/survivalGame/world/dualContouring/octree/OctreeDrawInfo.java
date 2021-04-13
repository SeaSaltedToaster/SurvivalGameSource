package survivalGame.world.dualContouring.octree;

import seaSaltedEngine.tools.math.Vector3f;
import seaSaltedEngine.tools.math.Vector4f;
import survivalGame.world.dualContouring.qef.QEFData;

public class OctreeDrawInfo {
    public int index;
    public int corners;
    public Vector4f position;
    public Vector3f averageNormal;
    public QEFData qef;
}