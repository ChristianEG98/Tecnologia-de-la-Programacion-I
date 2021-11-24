package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Pedestrian extends GameObject{

	private static int counter = 0;
	public static final String INFO = "[PEDESTRIAN] person crossing the road up and down";
	private boolean goDown = true;
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		super.symbol = "☺";
	}

	@Override
	public boolean doCollision() {
		super.alive = false;
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.resetCoins();
		doCollision();
		return false;
	}

	@Override
	public boolean receiveShoot() {
		game.resetCollectedCoins();
		doCollision();
		return true;
	}

	@Override
	public void onEnter() {
		counter++;
	}

	@Override
	public void update() {
		if(goDown) {
			super.y++;
			if(super.getY() >= game.getRoadWidth() - 1) goDown = false;
		}
		else {
			super.y--;
			if(super.getY() <= 0) {
				goDown = true;
			}
		}
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
