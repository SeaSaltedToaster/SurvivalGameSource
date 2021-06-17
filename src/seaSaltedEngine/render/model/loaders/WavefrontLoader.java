package seaSaltedEngine.render.model.loaders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.Engine;
import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.MeshData;
import seaSaltedEngine.render.model.material.Material;
import seaSaltedEngine.render.model.material.MaterialLoader;
import seaSaltedEngine.tools.math.Vector3f;

public class WavefrontLoader {

	private static List<Material> currentMaterials = new ArrayList<Material>();
	private static Material current;
	
	public static Mesh loadObjModel(String fileName) {
		InputStreamReader isr = new InputStreamReader(WavefrontLoader.class.getResourceAsStream("/res/"+fileName+".obj"));
		BufferedReader br = new BufferedReader(isr);
		String line;
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector3f> colors = new ArrayList<Vector3f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		float[] verticesArray = null;
		float[] normalsArray = null;
		float[] textureArray = null;
		int[] indicesArray = null;
		try {

			while (true) {
				line = br.readLine();
				String[] currentLine = line.split(" ");
				if (line.startsWith("v ")) {
					Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]),
							Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
					vertices.add(vertex);
				} else if (line.startsWith("mtllib ")) {
					String materialName = fileName;
					currentMaterials = MaterialLoader.loadMaterial(materialName);
				} else if (line.startsWith("vn ")) {
					Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]),
							Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
					normals.add(normal);
				} else if(line.startsWith("usemtl ")) {
					String materialName = line.replaceFirst("usemtl ", "");
					Material material = getMaterialFromName(materialName);
					current = material;
				} else if (line.startsWith("f ")) {
					textureArray = new float[vertices.size() * 3];
					normalsArray = new float[vertices.size() * 3];
					break;
				}
			}
			
			for (int i = 0; i < vertices.size()*1.4f; i++) {
				  colors.add(new Vector3f(1,1,1));
			}

			while (line != null) {
				if(line.startsWith("usemtl ")) {
					String materialName = line.replaceFirst("usemtl ", "");
					Material material = getMaterialFromName(materialName);
					current = material;
					
					line = br.readLine();
					continue;
				} else if (!line.startsWith("f ")) {
					line = br.readLine();
					continue;
				}
				
				String[] currentLine = line.split(" ");
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");
				
				colors.set(Integer.parseInt(vertex1[1]), current.getDiffuseColor());
				colors.set(Integer.parseInt(vertex2[1]), current.getDiffuseColor());
				colors.set(Integer.parseInt(vertex3[1]), current.getDiffuseColor());
				
				processVertex(vertex1,indices,colors,normals,textureArray,normalsArray);
				processVertex(vertex2,indices,colors,normals,textureArray,normalsArray);
				processVertex(vertex3,indices,colors,normals,textureArray,normalsArray);
				line = br.readLine();
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		verticesArray = new float[vertices.size()*3];
		indicesArray = new int[indices.size()];
		
		int vertexPointer = 0;
		for(Vector3f vertex:vertices){
			verticesArray[vertexPointer++] = vertex.x;
			verticesArray[vertexPointer++] = vertex.y;
			verticesArray[vertexPointer++] = vertex.z;
		}
		
		for(int i=0;i<indices.size();i++){
			indicesArray[i] = indices.get(i);
		}
		
		return new Mesh(new MeshData(Engine.getRenderer().getLoader().loadToVAO(verticesArray, textureArray, normalsArray, indicesArray)));

	}
	
	private static Material getMaterialFromName(String name) {
		for(Material material : currentMaterials) {
			if(material.getMaterialName().equalsIgnoreCase(name)) 
				return material;
		}
		return null;
	}

	private static void processVertex(String[] vertexData, List<Integer> indices,
			List<Vector3f> textures, List<Vector3f> normals, float[] textureArray,
			float[] normalsArray) {
		int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
		indices.add(currentVertexPointer);
		Vector3f currentTex = textures.get(Integer.parseInt(vertexData[1])-1);
		textureArray[currentVertexPointer*3] = currentTex.x;
		textureArray[currentVertexPointer*3+1] = currentTex.y;
		textureArray[currentVertexPointer*3+2] = currentTex.z;
		Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2])-1);
		normalsArray[currentVertexPointer*3] = currentNorm.x;
		normalsArray[currentVertexPointer*3+1] = currentNorm.y;
		normalsArray[currentVertexPointer*3+2] = currentNorm.z;	
	}
	
//	private static InputStreamReader isr;
//	private static BufferedReader bufferedReader;
//	
//	public static void loadWavefrontModel(String file) {
//		//Opening and extract contents of file
//		String[] objFile = openModelFile(file);
//		
//		//Extracting Data from Opened File
//		List<Vector3f> positions = getVertexPositions(objFile);
//		positions.size();
//	}
//	
//	private static List<Vector3f> getVertexPositions(String[] objFile) {
//		//Creating the Array of positions
//		List<Vector3f> vertexPositions = new ArrayList<Vector3f>();
//		
//		//Looping through each line of the file 
//		for(int i = 0; i < objFile.length; i++) {
//			
//		}
//		
//		return vertexPositions;
//	}
//
//	private static String[] openModelFile(String file) {
//		//Open file
//		isr = new InputStreamReader(Class.class.getResourceAsStream(Engine.getConfigs().getResourceFolder() + file + ".obj"));
//		bufferedReader = new BufferedReader(isr);
//		
//		//Create array of lines in file
//		int count = (int) bufferedReader.lines().count();
//		Logger.Log("Lines in file: "+count);
//		String[] objFile = new String[count];
//		
////		String string = null;
////		try {
////			while( (string = bufferedReader.readLine()) != null ) {
////				Logger.Log(string);
////			}
////			bufferedReader.close();
////		} catch (IOException e) { e.printStackTrace(); }
//		
//		//Return full file
//		return objFile;
//	}
	
}
