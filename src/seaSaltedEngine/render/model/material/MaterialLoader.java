package seaSaltedEngine.render.model.material;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import seaSaltedEngine.tools.math.Vector3f;

public class MaterialLoader {

	public static List<Material> loadMaterial(String file) {
		InputStreamReader isr = new InputStreamReader(Class.class.getResourceAsStream("/res/"+file+".mtl"));
		BufferedReader br = new BufferedReader(isr);
		String line;
		
		List<Material> materials = null;
		Material currentMaterial = null;
		
		try {
			while (true) {
				line = br.readLine();
				if(line == null) {
					break;
				} else if (line.startsWith("# Blender MTL File: ")) {
					
				} else if (line.startsWith("# Material Count: ")) {
					String count = line.replace("# Material Count: ", "");
					materials = new ArrayList<Material>(Integer.parseInt(count));
				} else if (line.startsWith("end")) {
					break;
				}
				
				if(line.startsWith("newmtl ")) {
					currentMaterial = new Material(line.replace("newmtl ","")); 
				}
				if(line.startsWith("Ka")) {
					currentMaterial.setAmbientColor(loadAmbientColor(line));
				} else if(line.startsWith("Kd")) {
					currentMaterial.setDiffuseColor(loadDiffuseColor(line));
				} else if(line.startsWith("Ks")) {
					materials.add(currentMaterial);
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return materials;
	}
	
	private static Vector3f loadDiffuseColor(String line) {
		Vector3f vector = new Vector3f();
		String string = line.replaceFirst("Kd ", "");
		String[] lines = string.split(" ");
		
		vector.x = Float.parseFloat(lines[0]);
		vector.y = Float.parseFloat(lines[1]);
		vector.z = Float.parseFloat(lines[2]);
		return vector;
	}
	
	private static Vector3f loadAmbientColor(String line) {
		Vector3f vector = new Vector3f(0,0,0);
		String[] lines = line.split(" ");
		
		vector.x = Float.parseFloat(lines[1]);
		vector.y = Float.parseFloat(lines[2]);
		vector.z = Float.parseFloat(lines[3]);
		return vector;
	}
	
}
