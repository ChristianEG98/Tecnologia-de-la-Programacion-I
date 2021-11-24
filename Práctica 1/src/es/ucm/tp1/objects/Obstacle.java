package es.ucm.tp1.objects;

import es.ucm.tp1.logic.Game;

public class Obstacle {

	private Game game;
	private int x;
	private int y;
	private int resistance = 1;
	
	public Obstacle(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getResistance() {
		return resistance;
	}
	
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}
}
