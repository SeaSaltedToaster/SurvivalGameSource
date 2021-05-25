package seaSaltedEngine.render.model.loaders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.tools.math.Vector3f;

public class WavefrontLoader {

	private static InputStreamReader isr;
	private static BufferedReader bufferedReader;
	
	public static void loadWavefrontModel(String file) {
		//Opening and extract contents of file
		String[] objFile = openModelFile(file);
		
		//Extracting Data from Opened File
		List<Vector3f> positions = getVertexPositions(objFile);
		positions.size();
	}
	
	private static List<Vector3f> getVertexPositions(String[] objFile) {
		//Creating the Array of positions
		List<Vector3f> vertexPositions = new ArrayList<Vector3f>();
		
		//Looping through each line of the file 
		for(String line : objFile) {
			line.chars();
		}
		return vertexPositions;
	}

	private static String[] openModelFile(String file) { //TODO Engine Resource Folder?
		//Open file
		isr = new InputStreamReader(Class.class.getResourceAsStream("/res/"+file+".obj"));
		bufferedReader = new BufferedReader(isr);
		
		//Create array of lines in file
		Logger.Log("Lines in file: "+bufferedReader.lines().count());
		String[] objFile = new String[(int) bufferedReader.lines().count()];
		
		//Return full file
		return objFile;
	}
	
}
