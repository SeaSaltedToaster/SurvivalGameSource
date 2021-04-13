package survivalGame.world.terrain.renderer;

import java.nio.ByteBuffer;

import seaSaltedEngine.basic.objects.Vertex;
import seaSaltedEngine.tools.math.Vector3f;

public class ChunkVertex extends Vertex {

	public static final float BYTES_PER_VERTEX = 27;
	
	public Vector3f position; //12 Bytes
	public Vector3f normal; //12 Bytes
	public byte[] color; //3 Bytes
	
	//Optimized Byte Value Per Vertex?
	public ChunkVertex(Vector3f position, Vector3f normal, byte[] color) {
		super(position, normal);
		this.position = position;
		this.normal = normal;
		this.color = color;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getNormal() {
		return normal;
	}

	public byte[] getColor() {
		return color;
	}
	
	public void storeData(ByteBuffer buffer) {
		storePosition(buffer, position);
		storeNormal(buffer, normal);
		buffer.put(color);
	}
	
	public void storePosition(ByteBuffer buffer, Vector3f position) {
		buffer.putFloat(position.x);
		buffer.putFloat(position.y);
		buffer.putFloat(position.z);
	}
	
	public void storeNormal(ByteBuffer buffer, Vector3f normal) {
		buffer.putFloat(normal.x);
		buffer.putFloat(normal.y);
		buffer.putFloat(normal.z);
	}
	
}
