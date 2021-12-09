package es.ucm.tp1.supercars.logic;

import java.util.ArrayList;

import es.ucm.tp1.supercars.logic.gameobjects.GameObject;

public class GameObjectContainer {

	private ArrayList<GameObject> gameObjectList;
	
	public GameObjectContainer() {
		gameObjectList = new ArrayList<GameObject>();
	}
	
	public void add(GameObject object) {
		gameObjectList.add(object);
		object.onEnter();
	}
	
	public void update() {
		for(GameObject object : gameObjectList) {
			object.update();
		}
	}
	
	public void resetLevel() {
		for(int i = gameObjectList.size() - 1; i >= 0; i--) {
			gameObjectList.get(i).onDelete();
			gameObjectList.remove(i);
		}
	}
	
	public void removeDeadObjects() {
		for(int i = gameObjectList.size() - 1; i >= 0; i--) {
			if(!gameObjectList.get(i).isAlive()) {
				gameObjectList.get(i).onDelete();
				gameObjectList.remove(i);
			}
		}
	}
	
	public void remove(int x, int y) {
        int pos = -1;

        for(int i = 0; i < gameObjectList.size(); i++) {
            if(gameObjectList.get(i).isInPosition(x, y)) {
                pos = i;
            }
        }

        if(pos != -1) {
            gameObjectList.get(pos).onDelete();
            gameObjectList.remove(pos);
        }
    }
	
	public GameObject objectInPosition(int x, int y) {
		for(GameObject object : gameObjectList) {
			if(object.isInPosition(x, y) && object.isAlive()) {
				return object;
			}
		}
		return null;
	}
	
	public String printObjectsInPosition(int x, int y) {
		StringBuilder str = new StringBuilder();
		str.append("");
		for(GameObject object : gameObjectList) {
			if(object.isInPosition(x, y) && object.isAlive()) {
				str.append(object.toString() + " ");
			}
		}
		return str.toString();
	}
	
}
