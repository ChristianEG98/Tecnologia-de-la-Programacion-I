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
	
	public void removeDeadObjects() {
		for(int i = 0; i < gameObjectList.size(); i++) {
			if(!gameObjectList.get(i).isAlive()) {
				remove(gameObjectList.get(i).getX(), gameObjectList.get(i).getY());
			}
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


}
