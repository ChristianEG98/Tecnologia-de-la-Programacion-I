package es.ucm.tp1.objects;

import es.ucm.tp1.logic.Game;

public class Coin {

	private Game game;
	private int x;
	private int y;
	
	public Coin(Game game, int x, int y) {
		this.game = game;
		this.setX(x);
		this.setY(y);
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
}
