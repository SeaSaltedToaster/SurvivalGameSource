package survivalGame.world.dualContouring.qef;

import seaSaltedEngine.tools.math.Vector4f;

public interface SvdSolver {
    Vector4f solve(float[] ATA, Vector4f ATb, Vector4f pointaccum);
    float qef_calc_error(float[] mat3x3_tri_A, Vector4f x, Vector4f atb);
}
