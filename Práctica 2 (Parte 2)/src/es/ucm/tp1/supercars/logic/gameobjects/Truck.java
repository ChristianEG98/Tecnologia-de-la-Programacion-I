package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Truck extends GameObject{

	private static int counter = 0;
	public static final String INFO = "[TRUCK] moves towards the player";
	
	public Truck(Game game, int x, int y) {
		super(game, x, y);
		super.symbol = "←";
	}

	@Override
	public boolean doCollision() {
		super.alive = false;
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.setCollision(true);
		return false;
	}

	@Override
	public boolean receiveShoot() {
		doCollision();
		return true;
	}

	@Override
	public void onEnter() {
		counter++;
	}

	@Override
	public void update() {
		super.x--;
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
		return receiveShoot();
	}
	
	@Override
	public boolean receiveThunder() {
		return true;
	}
}
