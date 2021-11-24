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
	
	public void removeDeadObjects() {
		for(int i = 0; i < gameObjectList.size(); i++) {
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
	
	public GameObject secondObjectInPosition(int x, int y) {
		for(int i = gameObjectList.size() - 1; i >= 0; i--) {
			if(gameObjectList.get(i).isInPosition(x, y) && gameObjectList.get(i).isAlive()) {
				return gameObjectList.get(i);
			}
		}
		return null;
	}

	public int objectsCounterInPosition(int x, int y) {
		int counter = 0;
		for(GameObject object : gameObjectList) {
			if(object.isInPosition(x, y) && object.isAlive()) {
				counter++;
			}
		}
		return counter;
	}

	public void waveObjects(Game game) {
		for(int i = gameObjectList.size() - 1; i >= 0; i--) {
			if(gameObjectList.get(i).getX() >= game.getPlayerXPosition() && gameObjectList.get(i).getX() < (game.getVisibility() + game.getPlayerXPosition() - 1)) {
				if(game.getObjectInPosition(gameObjectList.get(i).getX() + 1, gameObjectList.get(i).getY()) == null) {
					gameObjectList.get(i).increaseX(1);
				}
			}
		}
	}
	
}
