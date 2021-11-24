package es.ucm.tp1.objects;

import es.ucm.tp1.logic.Game;

public class ObstacleList {

	private final String SYM = "░";
	private Game game;
	private Obstacle obstacleList[];
	private int obstacleCounter = 0;
	
	public ObstacleList(Game game) {
		this.game = game;
		obstacleList = new Obstacle[game.getRoadLenght()];
	}
	
	public void add(Obstacle obstacle) {
		obstacleList[obstacleCounter] = obstacle;
		obstacleCounter++;
	}
	
	public void remove(int x, int y) {
		int pos = -1;
		
		for(int i = 0; i < obstacleCounter; i++) {
			if(obstacleList[i].getX() == x && obstacleList[i].getY() == y) pos = i;
		}
		
		if(pos != -1) {
			for(int i = pos; i < obstacleCounter - 1; i++) {
				obstacleList[i] = obstacleList[i + 1];
			}
			obstacleCounter--;
		}
	}
	
	public boolean obstacleInPosition(int x, int y) {
		for(int i = 0; i < obstacleCounter; i++) {
			if(obstacleList[i].getX() == x && obstacleList[i].getY() == y) return true;
		}
		return false;
	}
	
	public int getObstacleCounter() {
		return obstacleCounter;
	}
	
	public String toString() {
		return SYM;
	}
	
}
