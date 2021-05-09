package survivalGame.world.dualContouring.quadraticErrorFunction;

import seaSaltedEngine.tools.math.Vector3f;
import seaSaltedEngine.tools.math.Vector4f;

public class QefData {
	
	public float[] mat3x3_tri_ATA;
    public Vector4f atb;
    public Vector4f massPoint;
    public Vector4f x;
    private SvdSolver solver;
    private float btb;

    public Vector4f getMasspoint() {
        return massPoint;
    }

    public void setSolvedPos(Vector4f pos) {
        x = pos;
    }

    public Vector4f getSolvedPos() {
        return x;
    }

    public QefData(SvdSolver solver) {
        mat3x3_tri_ATA = new float[6];
        atb = new Vector4f();
        massPoint = new Vector4f();
        this.solver = solver;
    }

    public void add(QefData rhs){
        mat3x3_tri_ATA[0] += rhs.mat3x3_tri_ATA[0];
        mat3x3_tri_ATA[1] += rhs.mat3x3_tri_ATA[1];
        mat3x3_tri_ATA[2] += rhs.mat3x3_tri_ATA[2];
        mat3x3_tri_ATA[3] += rhs.mat3x3_tri_ATA[3];
        mat3x3_tri_ATA[4] += rhs.mat3x3_tri_ATA[4];
        mat3x3_tri_ATA[5] += rhs.mat3x3_tri_ATA[5];
        atb.x += rhs.atb.x;
        atb.y += rhs.atb.y;
        atb.z += rhs.atb.z;
        btb += rhs.btb;
        massPoint.x += rhs.massPoint.x;
        massPoint.y += rhs.massPoint.y;
        massPoint.z += rhs.massPoint.z;
        massPoint.w += rhs.massPoint.w;
    }

    private void qef_add_point(Vector4f p, Vector4f n) {
        n.normalize();
        mat3x3_tri_ATA[0] += n.x * n.x;
        mat3x3_tri_ATA[1] += n.x * n.y;
        mat3x3_tri_ATA[2] += n.x * n.z;
        mat3x3_tri_ATA[3] += n.y * n.y;
        mat3x3_tri_ATA[4] += n.y * n.z;
        mat3x3_tri_ATA[5] += n.z * n.z;
        float dot = n.x * p.x + n.y * p.y + n.z * p.z;
        atb.x += dot * n.x;
        atb.y += dot * n.y;
        atb.z += dot * n.z;
        btb += dot * dot;
        massPoint.x += p.x;
        massPoint.y += p.y;
        massPoint.z += p.z;
        ++massPoint.w;
    }

    public void qef_add_point(Vector3f p, Vector3f n) {
        n.normalize();
        mat3x3_tri_ATA[0] += n.x * n.x;
        mat3x3_tri_ATA[1] += n.x * n.y;
        mat3x3_tri_ATA[2] += n.x * n.z;
        mat3x3_tri_ATA[3] += n.y * n.y;
        mat3x3_tri_ATA[4] += n.y * n.z;
        mat3x3_tri_ATA[5] += n.z * n.z;
        float dot = n.x * p.x + n.y * p.y + n.z * p.z;
        atb.x += dot * n.x;
        atb.y += dot * n.y;
        atb.z += dot * n.z;
        btb += dot * dot;
        massPoint.x += p.x;
        massPoint.y += p.y;
        massPoint.z += p.z;
        ++massPoint.w;
    }

    public void qef_create_from_points(Vector4f[] positions, Vector4f[] normals, int count) {
        for (int i= 0; i < count; ++i) {
            qef_add_point(positions[i], normals[i]);
        }
        massPoint = massPoint.div(massPoint.w);
    }

    public Vector4f solveSimple() {
        if (massPoint.w == 0) {
            throw new IllegalArgumentException("...");
        }
        massPoint = massPoint.mul(1.0f / massPoint.w);
        x = x.add(massPoint);
        return x;
    }

    public Vector4f solve() {
        x = solver.solve(mat3x3_tri_ATA, atb, massPoint);
        return x;
    }

    public float getError() {
        return getError(x);
        //return solver.qef_calc_error(mat3x3_tri_ATA, x, atb);
    }

    private float getError(Vector4f pos) {
        Vector4f atax = pos.vmul(mat3x3_tri_ATA);
        double result = pos.dot(atax) - 2 * pos.dot(atb) + btb;
        return (float) Math.max(result, 0);
    }

}
