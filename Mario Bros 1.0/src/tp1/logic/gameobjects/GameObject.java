package tp1.logic.gameobjects;

import tp1.logic.GameItem;
import tp1.logic.Position;
import tp1.logic.Action;
import tp1.logic.Game;

public abstract class GameObject implements GameItem {

	private boolean isAlive;
	protected Position pos;
	protected Game game; 
	
	public GameObject(Game game, Position pos) {
		this.isAlive = true;
		this.game = game;
		this.pos = pos;
	}
	
	public boolean isInPosition(Position p) {
		return this.isAlive && this.pos.equals(p);
	}
 	
	public boolean isAlive() {
		return this.isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}
	
	public abstract boolean isSolid();
	public abstract void update();
	
	public abstract String getIcon();

	protected void move(Action dir) {
		// TODO Auto-generated method stub
	}
}
