package survivalGame.world.terrain.data;

import java.nio.ByteBuffer;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.tools.math.Vector3f;

public class TerrainVertex {
	
	private Vector3f position;
	private Vector3f normal;
	private Color vertexColor;
	
	public TerrainVertex(Vector3f position, Vector3f normal, Color vertexColor) {
		this.position = position;
		this.normal = normal;
		this.vertexColor = vertexColor;
	}
	
	public void packData(ByteBuffer buffer) {
		packPosition(buffer);
		Logger.Log("Buffer size: " + buffer.position());
	}
	
	private void packPosition(ByteBuffer buffer) {
		buffer.putFloat(position.getX());
		buffer.putFloat(position.getY());
		buffer.putFloat(position.getZ());
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getNormal() {
		return normal;
	}
	
	public Color getVertexColor() {
		return vertexColor;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}
	
	public void setVertexColor(Color vertexColor) {
		this.vertexColor = vertexColor;
	}

}
