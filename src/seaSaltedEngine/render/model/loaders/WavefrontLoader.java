package seaSaltedEngine.render.model.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.Engine;
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
		for(int i = 0; i < objFile.length; i++) {
			
		}
		
		return vertexPositions;
	}

	private static String[] openModelFile(String file) {
		//Open file
		isr = new InputStreamReader(Class.class.getResourceAsStream(Engine.getConfigs().getResourceFolder() + file + ".obj"));
		bufferedReader = new BufferedReader(isr);
		
		//Create array of lines in file
		int count = (int) bufferedReader.lines().count();
		Logger.Log("Lines in file: "+count);
		String[] objFile = new String[count];
		
		String string = null;
		try {
			while( (string = bufferedReader.readLine()) != null ) {
				Logger.Log(string);
			}
			bufferedReader.close();
		} catch (IOException e) { e.printStackTrace(); }
		
		//Return full file
		return objFile;
	}
	
}
