package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Coin extends GameObject{
	
	private static int counter = 0;
	public static final String INFO = "[Coin] gives 1 coin to the player";
	
	public Coin(Game game, int x, int y) {
		super(game, x, y);
		super.symbol = "¢";
	}
	
	@Override
	public boolean doCollision() {
		super.alive = false;
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.collectCoins(1);
		doCollision();
		return false;
	}
	
	@Override
	public boolean receiveShoot() {
		return false;
	}

	@Override
	public void onEnter() {
		counter++;
	}

	@Override
	public void update() {
	}

	@Override
	public void onDelete() {
		counter--;
	}

	public static void reset() {
		counter = 0;
	}

	public static int getCounter() {
		return counter;		
	}

	@Override
	public boolean receiveExplosion() {
		return false;
	}

	@Override
	public boolean receiveThunder() {
		return false;
	}

	@Override
	public void receiveWave() {
		x += 1;
	}

	@Override
	public String status() {
		return "";
	}
}
