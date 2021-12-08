package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Turbo extends GameObject{

	private static int counter = 0;
	public static final String INFO = "[TURBO] pushes the car: 3 columns";
	
	public Turbo(Game game, int x, int y) {
		super(game, x, y);
		super.symbol = ">>>";
	}

	@Override
	public boolean doCollision() {
		super.alive = false;
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.increaseX(3);
		game.increaseCycles(3);
		return true;
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
		// TODO Auto-generated method stub
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
		return false;
	}

	@Override
	public void receiveWave() {
		x += 1;
	}
}
