package survivalGame.resources.modding.loading;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import seaSaltedEngine.basic.logger.Logger;
import survivalGame.resources.modding.mod.Mod;
import survivalGame.resources.modding.mod.event.SaveLoadEvent;

public class ModFolderChecker {

	public static List<Mod> checkForMods(File file) {
		if(!file.exists() || file == null) return null;
		List<Mod> mods = new ArrayList<Mod>();
		for(File modFile : file.listFiles()) {
			try {
				mods.add(loadModFile(modFile));
			} catch (MalformedURLException | ClassNotFoundException | InstantiationException
					| IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return mods;
	}
	
	private static Mod loadModFile(File jarPath) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(jarPath);
		} catch (IOException e1) {
			Logger.Log("Failed to load Mod");
			e1.printStackTrace();
		}
		
		Enumeration<JarEntry> e = jarFile.entries();
		Logger.Log("Loading mod: "+jarPath.getAbsoluteFile().getName().replace(".jar", ""));
		URL[] urls = { new URL("jar:file:" + jarPath+"!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		while (e.hasMoreElements()) {
		    JarEntry je = e.nextElement();
		    if(je.isDirectory() || !je.getName().endsWith(".class"))
		        continue;
		    String className = je.getName().substring(0,je.getName().length()-6);
		    className = className.replace('/', '.');
		    Class<?> c = cl.loadClass(className);
		    if(c.getSuperclass() == Mod.class) {
		    	Mod mod = (Mod) c.newInstance();
		    	mod.init(new SaveLoadEvent());
		    	return (Mod) mod;
		    }
		}
		return null;
	}
	
}
