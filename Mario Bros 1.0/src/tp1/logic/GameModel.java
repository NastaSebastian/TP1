package tp1.logic;

import tp1.logic.gameobjects.GameObject;

public interface GameModel {
	
    public boolean isFinished();
    public void update();
    public void reset();
    public void reset(int level);
    public void exit();

    public void addObject(GameObject obj);
    public void addAction(Action action);
    public GameObject createObject(String[] objWords);
}