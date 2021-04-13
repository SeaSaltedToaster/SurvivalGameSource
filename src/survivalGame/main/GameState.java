package survivalGame.main;

public abstract class GameState {

	public abstract void init();
	public abstract void deinit();
	
	public abstract void update();
	public abstract void render();
	
}
