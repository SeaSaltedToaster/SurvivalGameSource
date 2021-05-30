package seaSaltedEngine.guis.text.renderer;

public class TextMeshData {
	
	private float[] vertexPositions;
    private float[] textureCoords;
     
    public TextMeshData(float[] vertexPositions, float[] textureCoords){
        this.vertexPositions = vertexPositions;
        this.textureCoords = textureCoords;
    }
 
    public float[] getVertexPositions() {
        return vertexPositions;
    }
 
    public float[] getTextureCoords() {
        return textureCoords;
    }
 
    public int getVertexCount() {
        return vertexPositions.length/2;
    }

}
