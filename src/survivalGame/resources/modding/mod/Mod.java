package survivalGame.resources.modding.mod;

import survivalGame.resources.modding.mod.event.GameLoadEvent;
import survivalGame.resources.modding.mod.event.SaveLoadEvent;

public abstract class Mod {
	
	//Initialize Mod
	public abstract void preInit(GameLoadEvent loadEvent);
	public abstract void init(SaveLoadEvent sLoadEvent);
	
	//Update and Render Mod
	public abstract void update();
	public abstract void render();
	
	//Initialize Mod
	public abstract void preUnload();
	public abstract void unload();
	
}
