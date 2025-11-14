package tp1.logic;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.Land;

public interface GameItem {
	
	public boolean isInPosition(Position pos);
	public boolean isSolid();
	public boolean isAlive();

	public boolean interactWith(GameItem item);

	public boolean receiveInteraction(ExitDoor obj);
	public boolean receiveInteraction(Goomba obj);
	public boolean receiveInteraction(Mario obj);
	public boolean receiveInteraction(Land obj);
}