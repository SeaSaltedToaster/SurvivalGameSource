package survivalGame.world.dualContouring.qef;

import seaSaltedEngine.tools.math.Vector2f;
import seaSaltedEngine.tools.math.Vector3f;
import seaSaltedEngine.tools.math.Vector4f;

public class LevenQefSolver implements SvdSolver{

	 int SVD_NUM_SWEEPS = 10;
	    float PSUEDO_INVERSE_THRESHOLD = 0.1f;

	    private float rsqrt(float a) {
	        return (float) Math.pow(a, -0.5);
	    }

	    private Vector4f svd_mul_matrix_vec(float[][] mat3x3_a, Vector4f b) {
	        Vector4f result = new Vector4f();
	        result.x = b.dot(new Vector4f(mat3x3_a[0][0], mat3x3_a[0][1], mat3x3_a[0][2], 0.f));
	        result.y = b.dot(new Vector4f(mat3x3_a[1][0], mat3x3_a[1][1], mat3x3_a[1][2], 0.f));
	        result.z = b.dot(new Vector4f(mat3x3_a[2][0], mat3x3_a[2][1], mat3x3_a[2][2], 0.f));
	        result.w = 0.f;
	        return result;
	    }

	    private void givens_coeffs_sym(float a_pp, float a_pq, float a_qq, Vector2f cs) {
	        if (a_pq == 0.f) {
			    cs.x = 1.f;
			    cs.y = 0.f;
	            return;
	        }
	        float tau = (a_qq - a_pp) / (2.f * a_pq);
	        float stt = (float) Math.sqrt(1.f + tau * tau);
	        float tan = 1.f / ((tau >= 0.f) ? (tau + stt) : (tau - stt));
		    cs.x = rsqrt(1.f + tan * tan);
		    cs.y = tan * (cs.x);
	    }

	    private void svd_rotate_xy(Vector2f xy, float c, float s) {
	        float u = xy.x;
	        float v = xy.y;
		    xy.x = c * u - s * v;
		    xy.y = s * u + c * v;
	    }

	    private void svd_rotateq_xy(Vector3f xya, float c, float s) {
	        float cc = c * c;
	        float ss = s * s;
	        float mx = 2 * c * s * (xya.getZ());
	        float u = xya.x;
	        float v = xya.y;
	        xya.x = cc * u - mx + ss * v;
	        xya.y = ss * u + mx + cc * v;
	    }

	    private void svd_rotate(float[][] mat3x3_vtav, float[][] mat3x3_v, int a, int b) {
	        if (mat3x3_vtav[a][b] == 0.0) return;

	        Vector2f cs = new Vector2f();
	        givens_coeffs_sym(mat3x3_vtav[a][a], mat3x3_vtav[a][b], mat3x3_vtav[b][b], cs);

	        Vector3f xyz = new Vector3f(mat3x3_vtav[a][a], mat3x3_vtav[b][b], mat3x3_vtav[a][b]);
	        svd_rotateq_xy(xyz, cs.x,cs.y);
	        mat3x3_vtav[a][a] = xyz.x; mat3x3_vtav[b][b] = xyz.y; mat3x3_vtav[a][b] = xyz.z;

	        Vector2f xy = new Vector2f(mat3x3_vtav[0][3-b], mat3x3_vtav[1-a][2]);
	        svd_rotate_xy(xy, cs.x, cs.y);
	        mat3x3_vtav[0][3-b] = xy.x; mat3x3_vtav[1-a][2] = xy.y;

	        mat3x3_vtav[a][b] = 0f;

	        xy.x = mat3x3_v[0][a]; xy.y = mat3x3_v[0][b];
	        svd_rotate_xy(xy, cs.x, cs.y);
	        mat3x3_v[0][a] = xy.x; mat3x3_v[0][b] = xy.y;

	        xy.x = mat3x3_v[1][a]; xy.y = mat3x3_v[1][b];
	        svd_rotate_xy(xy, cs.x, cs.y);
	        mat3x3_v[1][a] = xy.x; mat3x3_v[1][b] = xy.y;

	        xy.x = mat3x3_v[2][a]; xy.y = mat3x3_v[2][b];
	        svd_rotate_xy(xy, cs.x, cs.y);
	        mat3x3_v[2][a] = xy.x; mat3x3_v[2][b] = xy.y;
	    }

	    private void svd_solve_sym(float[] mat3x3_tri_a, Vector4f sigma, float[][] mat3x3_v) {
	        // assuming that A is symmetric: can optimize all operations for the upper right triagonal
	        float[][] mat3x3_vtav = new float[3][3];
	        mat3x3_vtav[0][0] = mat3x3_tri_a[0];    mat3x3_vtav[0][1] = mat3x3_tri_a[1];    mat3x3_vtav[0][2] = mat3x3_tri_a[2];
	        mat3x3_vtav[1][0] = 0.f;                mat3x3_vtav[1][1] = mat3x3_tri_a[3];    mat3x3_vtav[1][2] = mat3x3_tri_a[4];
	        mat3x3_vtav[2][0] = 0.f;                mat3x3_vtav[2][1] = 0.f;                mat3x3_vtav[2][2] = mat3x3_tri_a[5];

	        // assuming V is identity: you can also pass a matrix the rotations should be applied to. (U is not computed)
	        for (int i = 0; i < SVD_NUM_SWEEPS; ++i) {
	            svd_rotate(mat3x3_vtav, mat3x3_v, 0, 1);
	            svd_rotate(mat3x3_vtav, mat3x3_v, 0, 2);
	            svd_rotate(mat3x3_vtav, mat3x3_v, 1, 2);
	        }

	        sigma.setX(mat3x3_vtav[0][0]);
	        sigma.setY(mat3x3_vtav[1][1]);
	        sigma.setZ(mat3x3_vtav[2][2]);
	        sigma.setW(0.f);
	    }

	    private float svd_invdet(float x, float tol) {
	        return (float) ((Math.abs(x) < tol || Math.abs(1.0 / x) < tol) ? 0.0 : (1.0 / x));
	    }

	    private float[][] svd_pseudoinverse(Vector4f sigma, float[][] mat3x3_v) {
	        float d0 = svd_invdet(sigma.x, PSUEDO_INVERSE_THRESHOLD);
	        float d1 = svd_invdet(sigma.y, PSUEDO_INVERSE_THRESHOLD);
	        float d2 = svd_invdet(sigma.z, PSUEDO_INVERSE_THRESHOLD);
	        float[][] mat3x3_o = new float[3][3];
	        mat3x3_o[0][0] = mat3x3_v[0][0] * d0 * mat3x3_v[0][0] + mat3x3_v[0][1] * d1 * mat3x3_v[0][1] + mat3x3_v[0][2] * d2 * mat3x3_v[0][2];
	        mat3x3_o[0][1] = mat3x3_v[0][0] * d0 * mat3x3_v[1][0] + mat3x3_v[0][1] * d1 * mat3x3_v[1][1] + mat3x3_v[0][2] * d2 * mat3x3_v[1][2];
	        mat3x3_o[0][2] = mat3x3_v[0][0] * d0 * mat3x3_v[2][0] + mat3x3_v[0][1] * d1 * mat3x3_v[2][1] + mat3x3_v[0][2] * d2 * mat3x3_v[2][2];
	        mat3x3_o[1][0] = mat3x3_v[1][0] * d0 * mat3x3_v[0][0] + mat3x3_v[1][1] * d1 * mat3x3_v[0][1] + mat3x3_v[1][2] * d2 * mat3x3_v[0][2];
	        mat3x3_o[1][1] = mat3x3_v[1][0] * d0 * mat3x3_v[1][0] + mat3x3_v[1][1] * d1 * mat3x3_v[1][1] + mat3x3_v[1][2] * d2 * mat3x3_v[1][2];
	        mat3x3_o[1][2] = mat3x3_v[1][0] * d0 * mat3x3_v[2][0] + mat3x3_v[1][1] * d1 * mat3x3_v[2][1] + mat3x3_v[1][2] * d2 * mat3x3_v[2][2];
	        mat3x3_o[2][0] = mat3x3_v[2][0] * d0 * mat3x3_v[0][0] + mat3x3_v[2][1] * d1 * mat3x3_v[0][1] + mat3x3_v[2][2] * d2 * mat3x3_v[0][2];
	        mat3x3_o[2][1] = mat3x3_v[2][0] * d0 * mat3x3_v[1][0] + mat3x3_v[2][1] * d1 * mat3x3_v[1][1] + mat3x3_v[2][2] * d2 * mat3x3_v[1][2];
	        mat3x3_o[2][2] = mat3x3_v[2][0] * d0 * mat3x3_v[2][0] + mat3x3_v[2][1] * d1 * mat3x3_v[2][1] + mat3x3_v[2][2] * d2 * mat3x3_v[2][2];
	        return mat3x3_o;
	    }

	    private Vector4f svd_solve_ATA_ATb(
	            float[] mat3x3_tri_ATA,
	            Vector4f ATb)
	    {
	        float[][] mat3x3_V = new float[3][3];
	        mat3x3_V[0][0] = 1.f; mat3x3_V[0][1] = 0.f; mat3x3_V[0][2] = 0.f;
	        mat3x3_V[1][0] = 0.f; mat3x3_V[1][1] = 1.f; mat3x3_V[1][2] = 0.f;
	        mat3x3_V[2][0] = 0.f; mat3x3_V[2][1] = 0.f; mat3x3_V[2][2] = 1.f;

	        Vector4f sigma = new Vector4f();
	        svd_solve_sym(mat3x3_tri_ATA, sigma, mat3x3_V);

	        // A = UEV^T; U = A / (E*V^T)
	        float[][] mat3x3_Vinv = svd_pseudoinverse(sigma, mat3x3_V);
	        return svd_mul_matrix_vec(mat3x3_Vinv, ATb);
	    }

	    private Vector4f svd_vmul_sym(float[] mat3x3_tri_A, Vector4f v) {
	        Vector4f A_row_x = new Vector4f(mat3x3_tri_A[0], mat3x3_tri_A[1], mat3x3_tri_A[2], 0.f);
	        Vector4f result = new Vector4f();
	        result.x = v.dot(A_row_x);
	        result.y = mat3x3_tri_A[1] * v.x + mat3x3_tri_A[3] * v.y + mat3x3_tri_A[4] * v.z;
	        result.z = mat3x3_tri_A[2] * v.x + mat3x3_tri_A[4] * v.y + mat3x3_tri_A[5] * v.z;
	        return result;
	    }

	    @Override
	    public float qef_calc_error(float[] mat3x3_tri_A, Vector4f x, Vector4f b) {
	        Vector4f tmp = svd_vmul_sym(mat3x3_tri_A, x);
	        tmp = b.subtract(tmp);

	        return tmp.dot(tmp);
	    }

	    @Override
	    public Vector4f solve(float[] mat3x3_tri_ATA, Vector4f ATb, Vector4f masspoint) {
	        // prevent a div-by-zero exception
	        masspoint.set(masspoint.div(Math.max(masspoint.w, 1.f)));

	        Vector4f A_mp = svd_vmul_sym(mat3x3_tri_ATA, masspoint);
	        A_mp = ATb.subtract(A_mp);

	        Vector4f solvedPos = svd_solve_ATA_ATb(mat3x3_tri_ATA, A_mp);

//	        float error = qef_calc_error(mat3x3_tri_ATA, solvedPos, ATb);
	        solvedPos = solvedPos.add(masspoint);

	        return solvedPos;
	    }

	    public float solve(float[] mat3x3_tri_ATA, Vector4f ATb, Vector4f masspoint, Vector4f pos) {
	        masspoint.set(masspoint.div(Math.max(masspoint.w, 1.f)));

	        Vector4f A_mp = svd_vmul_sym(mat3x3_tri_ATA, masspoint);
	        A_mp = ATb.subtract(A_mp);

	        Vector4f solvedPos = svd_solve_ATA_ATb(mat3x3_tri_ATA, A_mp);

	        float error = qef_calc_error(mat3x3_tri_ATA, solvedPos, ATb);
	        solvedPos = solvedPos.add(masspoint);
	        pos.set(solvedPos);
	        return error;
	    }
	
}
