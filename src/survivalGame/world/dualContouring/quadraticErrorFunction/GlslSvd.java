package survivalGame.world.dualContouring.quadraticErrorFunction;

import seaSaltedEngine.tools.math.Vector2f;
import seaSaltedEngine.tools.math.Vector3f;
import seaSaltedEngine.tools.math.Vector4f;

public class GlslSvd implements SvdSolver{

    private final int SVD_NUM_SWEEPS = 5;
    private final float Tiny_Number = (float) 1.e-20;

    private float rsqrt(float x) {
        return (float) (1.0 / Math.sqrt(x));
    }

    private Vector2f givens_coeffs_sym(float a_pp, float a_pq, float a_qq) {
        Vector2f f = new Vector2f();
        if (a_pq == 0.0) {
            f.x = 1;
            f.y = 0;
            return f;
        }
        float tau = (a_qq - a_pp) / (2 * a_pq);
        float stt = (float) Math.sqrt(1.0 + tau * tau);
        float tan = 1.0f / ((tau >= 0) ? (tau + stt) : (tau - stt));
        f.x = rsqrt(1 + tan * tan);
        f.y = tan * f.x;
        return f;
    }

    private Vector2f svd_rotate_xy(float x, float y, float c, float s) {
        float u = x;
        float v = y;
        Vector2f a = new Vector2f();
        a.x = c * u - s * v;
        a.y = s * u + c * v;
        return a;
    }

    private Vector2f svd_rotateq_xy(float x, float y, float a, float c, float s) {
        float cc = c * c;
        float ss = s * s;
        float mx = 2 * c * s * a;
        float u = x; float v = y;
        Vector2f val = new Vector2f();
        val.x = cc * u - mx + ss * v;
        val.y = ss * u + mx + cc * v;
        return val;
    }

    private void svd_rotate(float[][] vtav, float[][]v, int a, int b) {
        if (vtav[a][b] == 0.0) return;

        Vector2f f = givens_coeffs_sym(vtav[a][a], vtav[a][b], vtav[b][b]);
        Vector2f val = svd_rotateq_xy(vtav[a][a], vtav[b][b], vtav[a][b], f.x, f.y);
        vtav[a][a] = val.x; vtav[b][b] = val.y;
        val = svd_rotate_xy(vtav[0][3-b], vtav[1-a][2], f.x, f.y);
        vtav[0][3-b] = val.x; vtav[1-a][2] = val.y;
        vtav[a][b] = 0;

        val = svd_rotate_xy(v[0][a], v[0][b], f.x, f.y);
        v[0][a] = val.x; v[0][b] = val.y;
        val = svd_rotate_xy(v[1][a], v[1][b], f.x, f.y);
        v[1][a] = val.x; v[1][b] = val.y;
        val = svd_rotate_xy(v[2][a], v[2][b], f.x, f.y);
        v[2][a] = val.x; v[2][b] = val.y;
    }

    float[][] SMatToMat(float[] a){
        float[][] mat = new float[3][3];
        mat[0][0] = a[0]; mat[0][1] = a[1]; mat[0][2] = a[2];
        mat[1][0] = 0.f;  mat[1][1] = a[3]; mat[1][2] = a[4];
        mat[2][0] = 0.f;  mat[2][1] = 0.f;  mat[2][2] = a[5];
        return mat;
    }

    private float[] svd_solve_sym(float[] a, float[][] v) {
        // assuming that A is symmetric: can optimize all operations for the upper right triagonal
        // assuming V is identity: you can also pass a matrix the rotations should be applied to U is not computed
        float[][] vtav = new float[3][3];
        vtav[0][0] = a[0];   vtav[0][1] = a[1];   vtav[0][2] = a[2];
        vtav[1][0] = 0.f;    vtav[1][1] = a[3];   vtav[1][2] = a[4];
        vtav[2][0] = 0.f;    vtav[2][1] = 0.f;    vtav[2][2] = a[5];

        for (int i = 0; i < SVD_NUM_SWEEPS; ++i) {
            svd_rotate(vtav, v, 0, 1);
            svd_rotate(vtav, v, 0, 2);
            svd_rotate(vtav, v, 1, 2);
        }
        return new float[] {vtav[0][0],vtav[1][1],vtav[2][2]};
    }

    private float svd_invdet(float x, float tol) {
        return (float) ((Math.abs(x) < tol || Math.abs(1.0 / x) < tol) ? 0.0 : (1.0 / x));
    }

    private float[][] svd_pseudoinverse(float[] sigma, float[][] v) {
        float d0 = svd_invdet(sigma[0], Tiny_Number);
        float d1 = svd_invdet(sigma[1], Tiny_Number);
        float d2 = svd_invdet(sigma[2], Tiny_Number);
        float[][] o = new float[3][3];
        o[0][0] = v[0][0] * d0 * v[0][0] + v[0][1] * d1 * v[0][1] + v[0][2] * d2 * v[0][2];
        o[0][1] = v[0][0] * d0 * v[1][0] + v[0][1] * d1 * v[1][1] + v[0][2] * d2 * v[1][2];
        o[0][2] = v[0][0] * d0 * v[2][0] + v[0][1] * d1 * v[2][1] + v[0][2] * d2 * v[2][2];
        o[1][0] = v[1][0] * d0 * v[0][0] + v[1][1] * d1 * v[0][1] + v[1][2] * d2 * v[0][2];
        o[1][1] = v[1][0] * d0 * v[1][0] + v[1][1] * d1 * v[1][1] + v[1][2] * d2 * v[1][2];
        o[1][2] = v[1][0] * d0 * v[2][0] + v[1][1] * d1 * v[2][1] + v[1][2] * d2 * v[2][2];
        o[2][0] = v[2][0] * d0 * v[0][0] + v[2][1] * d1 * v[0][1] + v[2][2] * d2 * v[0][2];
        o[2][1] = v[2][0] * d0 * v[1][0] + v[2][1] * d1 * v[1][1] + v[2][2] * d2 * v[1][2];
        o[2][2] = v[2][0] * d0 * v[2][0] + v[2][1] * d1 * v[2][1] + v[2][2] * d2 * v[2][2];
        return o;
    }

    float unExponent(float number, int w){
        // float number = t;//12.43543542f;
        // int aux = (int)(number*100);//1243
        // float result = aux/100f;//12.43
        int aux = (int)(number*w);
        return aux/(float)w;
    }

    //roundAvoid(260.775d, 2)); // OUTPUTS: 260.77 instead of expected 260.78
    public static float roundAvoid(float value, int nDigits) {
        float scale = (float) Math.pow(10, nDigits);
        return Math.round(value * scale) / scale;
    }

    Vector4f svd_solve_ATA_ATb(float[] ATA, Vector4f ATb) {
        float[][] V = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } }; // mat3 V = mat3(1.0);
        float[] sigma = svd_solve_sym(ATA, V);
        float[][] vInv = svd_pseudoinverse(sigma, V);            // A = UEV^T; U = A / (E*V^T)
        return vmulSym(vInv, ATb); // x = vInv * ATb;
    }

    private Vector4f vmulSym(float[][] mat3x3_a, Vector4f b) {
        Vector4f result = new Vector4f();
        result.x = b.dot(new Vector4f(mat3x3_a[0][0], mat3x3_a[0][1], mat3x3_a[0][2], 0.f));
        result.y = b.dot(new Vector4f(mat3x3_a[1][0], mat3x3_a[1][1], mat3x3_a[1][2], 0.f));
        result.z = b.dot(new Vector4f(mat3x3_a[2][0], mat3x3_a[2][1], mat3x3_a[2][2], 0.f));
        result.w = 0.f;
        return result;
    }

    public Vector4f vmulSym(float[] mat3x3_tri_A, Vector4f v) {
        Vector4f A_row_x = new Vector4f(mat3x3_tri_A[0], mat3x3_tri_A[1], mat3x3_tri_A[2], 0.f);
        Vector4f result = new Vector4f();
        result.x = v.dot(A_row_x);
        result.y = mat3x3_tri_A[1] * v.x + mat3x3_tri_A[3] * v.y + mat3x3_tri_A[4] * v.z;
        result.z = mat3x3_tri_A[2] * v.x + mat3x3_tri_A[4] * v.y + mat3x3_tri_A[5] * v.z;
        return result;
    }

    public Vector4f vmulSym(float[][] a, Vector3f v) {
        return new Vector4f(
                (a[0][0] * v.x) + (a[0][1] * v.y) + (a[0][2] * v.z),
                (a[0][1] * v.x) + (a[1][1] * v.y) + (a[1][2] * v.z),
                (a[0][2] * v.x) + (a[1][2] * v.y) + (a[2][2] * v.z),
                0f);
    }

    @Override
    public float qef_calc_error(float[] A, Vector4f x, Vector4f b) {
//        Vector3f atax = this.ata.Vmul(pos);
//        return pos.dot(atax) - 2 * pos.dot(atb) + data.btb;

        Vector4f vtmp = b.subtract(vmulSym(A, x));
        return vtmp.dot(vtmp);
    }

    @Override
    public Vector4f solve(float[] ATA, Vector4f ATb, Vector4f pointaccum) {
        if (pointaccum.w == 0)
            throw new IllegalArgumentException("...");
        pointaccum.set(pointaccum.div(pointaccum.w));
        Vector4f tmpv = pointaccum.vmul(ATA);
        ATb = ATb.subtract(tmpv);

        Vector4f x = svd_solve_ATA_ATb(ATA, ATb);
        x = x.add(pointaccum);
        return x;
    }

    public float solve(float[] ATA, Vector4f ATb, Vector4f pointaccum, Vector4f pos) {
        if (pointaccum.w == 0)
            throw new IllegalArgumentException("...");
        pointaccum.set(pointaccum.div(pointaccum.w));
        Vector4f tmpv = vmulSym(ATA, pointaccum);
        ATb = ATb.subtract(tmpv);

        Vector4f x = svd_solve_ATA_ATb(ATA, ATb);
        float result = qef_calc_error(ATA, x, ATb);
        x = x.add(pointaccum);
        pos.set(x);
        return result;
    }
    
}