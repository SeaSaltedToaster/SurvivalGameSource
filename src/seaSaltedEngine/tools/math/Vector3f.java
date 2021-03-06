/*
 * The MIT License (MIT)
 *
 * Copyright © 2015-2017, Heiko Brumme
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package seaSaltedEngine.tools.math;

import java.nio.FloatBuffer;

/**
 * This class represents a (x,y,z)-Vector. GLSL equivalent to vec3.
 *
 * @author Heiko Brumme
 */
public class Vector3f {

    public float x;
    public float y;
    public float z;

    public static Vector3f UP = new Vector3f(0,1,0);
    
    /**
     * Creates a default 3-tuple vector with all values set to 0.
     */
    public static Vector3f add(Vector3f left, Vector3f right, Vector3f dest) {
		if (dest == null)
			return new Vector3f(left.x + right.x, left.y + right.y, left.z + right.z);
		else {
			dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
			return dest;
		}
	}
    
    public Vector3f(float t)
	{
		this.setX(t);
		this.setY(t);
		this.setZ(t);
	}
    
    public static float dot(Vector3f left, Vector3f right) {
		return left.x * right.x + left.y * right.y + left.z * right.z;
	}
    
    public Vector3f mul(float r)
	{
		return new Vector3f(this.x * r, this.y * r, this.z * r);
	}
    
    public Vector3f mul(Vector3f r)
	{
		return new Vector3f(this.x * r.x, this.y * r.y, this.z * r.z);
	}
    
    public Vector3f div(Vector3f r)
	{
		return new Vector3f(this.x / r.getX(), this.y / r.getY(), this.getZ() / r.getZ());
	}
	
	public Vector3f div(float r)
	{
		return new Vector3f(this.x / r, this.y / r, this.z / r);
	}
	
	public Vector3f divNY(float r)
	{
		return new Vector3f(this.x / r, this.y, this.z / r);
	}
    
    public Vector3f translate(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
    
    public static Vector2f toVector2f(Vector3f pos) {
		return new Vector2f(pos.x, pos.z);
	}
    
    public Vector2f toVector2f() {
		return new Vector2f(x, z);
	}
    
    public Vector3f() {
        this.x = 0f;
        this.y = 0f;
        this.z = 0f;
    }
    
    public Vector3f(Vector3f pos) {
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
    }
    
    public Vector3f(Vector4f pos) {
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
    }
    
    public String toString() {
    	return new String((int)x+":"+(int)y+":"+(int)z);
    }
    
    public Vector3i toInt() {
    	return new Vector3i((int)x,(int)y,(int)z);
    }

    /**
     * Creates a 3-tuple vector with specified values.
     *
     * @param x x value
     * @param y y value
     * @param z z value
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Calculates the squared length of the vector.
     *
     * @return Squared length of this vector
     */
    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    /**
     * Calculates the length of the vector.
     *
     * @return Length of this vector
     */
    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes the vector.
     *
     * @return Normalized vector
     */
    public Vector3f normalize() {
        float length = length();
        return divide(length);
    }

    /**
     * Adds this vector to another vector.
     *
     * @param other The other vector
     *
     * @return Sum of this + other
     */
    public Vector3f add(Vector3f other) {
        float x = this.x + other.x;
        float y = this.y + other.y;
        float z = this.z + other.z;
        return new Vector3f(x, y, z);
    }

    /**
     * Negates this vector.
     *
     * @return Negated vector
     */
    public Vector3f negate() {
        return scale(-1f);
    }

    /**
     * Subtracts this vector from another vector.
     *
     * @param other The other vector
     *
     * @return Difference of this - other
     */
    public Vector3f subtract(Vector3f other) {
        return this.add(other.negate());
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param scalar Scalar to multiply
     *
     * @return Scalar product of this * scalar
     */
    public Vector3f scale(float scalar) {
        float x = this.x * scalar;
        float y = this.y * scalar;
        float z = this.z * scalar;
        return new Vector3f(x, y, z);
    }

    /**
     * Divides a vector by a scalar.
     *
     * @param scalar Scalar to multiply
     *
     * @return Scalar quotient of this / scalar
     */
    public Vector3f divide(float scalar) {
        return scale(1f / scalar);
    }

    /**
     * Calculates the dot product of this vector with another vector.
     *
     * @param other The other vector
     *
     * @return Dot product of this * other
     */
    public float dot(Vector3f other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * Calculates the dot product of this vector with another vector.
     *
     * @param other The other vector
     *
     * @return Cross product of this x other
     */
    public Vector3f cross(Vector3f other) {
        float x = this.y * other.z - this.z * other.y;
        float y = this.z * other.x - this.x * other.z;
        float z = this.x * other.y - this.y * other.x;
        return new Vector3f(x, y, z);
    }
    
    public Vector3f set(Vector3f src) {
		x = src.x;
		y = src.y;
		z = src.z;
		return this;
	}
    
    public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
    
    public static Vector3f cross(
			Vector3f left,
			Vector3f right,
			Vector3f dest)
	{

		if (dest == null)
			dest = new Vector3f();

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
    public Vector3f lerp(Vector3f other, float alpha) {
        return this.scale(1f - alpha).add(other.scale(alpha));
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

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}

}
