package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class Player {

	private Game game;
	private final String SYM_ALIVE = ">";
	private final String SYM_DEAD = "@";
	private int x;
	private int y;
	private int speed = 1;
	private boolean collision = false;
	private int collectedCoins = 5;
	public static final String INFO = "[Car] the racing car";
	
	public Player(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
	}
	
	public void goUp() {
		y--;
	}
	
	public void goDown() {
		y++;
	}
	
	public void goForward() {
		x += speed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	
	public boolean getCollision() {
		return collision;
	}
	
	public int getCollectedCoins() {
		return collectedCoins;
	}
	
	public void collectCoin() {
		collectedCoins++;
	}
	
	public String toString() {
		if(!collision) return SYM_ALIVE;
		else return SYM_DEAD;
	}
	
	public boolean doCollision() {
		Collider other = game.getObjectInPosition(x, y);
		if (other != null) {
			return other.receiveCollision (this);
		}
		return false;
	}
}
