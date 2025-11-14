package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}
    
	public void add(GameObject obj) {
		gameObjects.add(obj);
	}
	
	public void remove(GameObject obj) {
		gameObjects.remove(obj);
	}
	
    public void update() {
        for (GameObject obj : new ArrayList<>(gameObjects)) {
            if (obj.isAlive()) obj.update();
        }

        for (GameObject obj : new ArrayList<>(gameObjects)) {
            if (obj.isAlive()) doInteraction(obj);
        }

        // 3️⃣ Limpiar los objetos muertos
        gameObjects.removeIf(obj -> !obj.isAlive());
    }
}