package seaSaltedEngine.tools.math;

import java.nio.FloatBuffer;

public class Vector3i {

    public int x;
    public int y;
    public int z;

    /**
     * Creates a default 3-tuple vector with all values set to 0.
     */
    public static Vector3i add(Vector3i left, Vector3i right, Vector3i dest) {
		if (dest == null)
			return new Vector3i(left.x + right.x, left.y + right.y, left.z + right.z);
		else {
			dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
			return dest;
		}
	}
    
    public Vector3i translate(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
    
    public Vector3i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    
    public String toString() {
    	return new String(x+","+y+","+z);
    }

    /**
     * Creates a 3-tuple vector with specified values.
     *
     * @param x x value
     * @param y y value
     * @param z z value
     */
    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Calculates the squared length of the vector.
     *
     * @return Squared length of this vector
     */
    public int lengthSquared() {
        return x * x + y * y + z * z;
    }

    /**
     * Calculates the length of the vector.
     *
     * @return Length of this vector
     */
    public int length() {
        return (int) Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes the vector.
     *
     * @return Normalized vector
     */
    public Vector3i normalize() {
        int length = length();
        return divide(length);
    }

    /**
     * Adds this vector to another vector.
     *
     * @param other The other vector
     *
     * @return Sum of this + other
     */
    public Vector3i add(Vector3i other) {
        int x = this.x + other.x;
        int y = this.y + other.y;
        int z = this.z + other.z;
        return new Vector3i(x, y, z);
    }

    /**
     * Negates this vector.
     *
     * @return Negated vector
     */
    public Vector3i negate() {
        return scale(-1);
    }

    /**
     * Subtracts this vector from another vector.
     *
     * @param other The other vector
     *
     * @return Difference of this - other
     */
    public Vector3i subtract(Vector3i other) {
        return this.add(other.negate());
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param scalar Scalar to multiply
     *
     * @return Scalar product of this * scalar
     */
    public Vector3i scale(int scalar) {
        int x = this.x * scalar;
        int y = this.y * scalar;
        int z = this.z * scalar;
        return new Vector3i(x, y, z);
    }

    /**
     * Divides a vector by a scalar.
     *
     * @param scalar Scalar to multiply
     *
     * @return Scalar quotient of this / scalar
     */
    public Vector3i divide(int scalar) {
        return scale(1 / scalar);
    }

    /**
     * Calculates the dot product of this vector with another vector.
     *
     * @param other The other vector
     *
     * @return Dot product of this * other
     */
    public int dot(Vector3i other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * Calculates the dot product of this vector with another vector.
     *
     * @param other The other vector
     *
     * @return Cross product of this x other
     */
    public Vector3i cross(Vector3i other) {
        int x = this.y * other.z - this.z * other.y;
        int y = this.z * other.x - this.x * other.z;
        int z = this.x * other.y - this.y * other.x;
        return new Vector3i(x, y, z);
    }
    
    public Vector3i set(Vector3i src) {
		x = src.x;
		y = src.y;
		z = src.z;
		return this;
	}
    
    public void set(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
    
    public static Vector3i cross(
			Vector3i left,
			Vector3i right,
			Vector3i dest)
	{

		if (dest == null)
			dest = new Vector3i();

		dest.set(
				left.y * right.z - left.z * right.y,
				right.x * left.z - right.z * left.x,
				left.x * right.y - left.y * right.x
				);

		return dest;
	}

    /**
     * Calculates a linear interpolation between this vector with another
     * vector.
     *
     * @param other The other vector
     * @param alpha The alpha value, must be between 0.0 and 1.0
     *
     * @return Linear interpolated vector
     */
    public Vector3i lerp(Vector3i other, int alpha) {
        return this.scale(1 - alpha).add(other.scale(alpha));
    }

    /**
     * Stores the vector in a given Buffer.
     *
     * @param buffer The buffer to store the vector data
     */
    public void toBuffer(FloatBuffer buffer) {
        buffer.put(x).put(y).put(z);
        buffer.flip();
    }
}
