package survivalGame.resources.modding.loading;

import java.io.File;
import java.util.List;

import survivalGame.resources.modding.mod.Mod;

public class ModLoader {

	private static String modFolder = "resources/res/mods/";
	
	public static void loadMods() {
		List<Mod> mods = ModFolderChecker.checkForMods(new File(modFolder));
		mods.isEmpty();
	}
	
}
