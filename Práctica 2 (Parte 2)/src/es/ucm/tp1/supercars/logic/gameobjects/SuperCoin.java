package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class SuperCoin extends GameObject{

	private static int counter = 0;
	public static final String INFO = "[SUPERCOIN] gives 1000 coins";
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		super.symbol = "$";
	}

	@Override
	public boolean doCollision() {
		super.alive = false;
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.collectCoins(1000);
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

	public static boolean hasSuperCoin() {
		return counter > 0;
	}

	@Override
	public boolean receiveExplosion() {
		return receiveShoot();
	}
	
	@Override
	public boolean receiveThunder() {
		return false;
	}
}
