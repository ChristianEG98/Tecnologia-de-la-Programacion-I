package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends GameObject{

	private static int counter = 0;
	public static final String INFO = "[WALL] hard obstacle";
	private int resistance = 3;
	
	public Wall(Game game, int x, int y) {
		super(game, x, y);
		super.symbol = getSymbol();
	}
	
	protected String getSymbol() {
		if(resistance == 3) return "█";
		else if(resistance == 2) return "▒";
		else return "░";
	}

	@Override
	public boolean doCollision() {
		resistance--;
		if(resistance == 0) super.alive = false;
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.setCollision(true);
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
		game.increaseCollectedCoins(5);
	}
	
	public static void reset() {
		counter = 0;
	}

	public static int getCounter() {
		return counter;		
	}

	@Override
	public boolean receiveShoot() {
		doCollision();
		return true;
	}

	@Override
	public boolean receiveExplosion() {
		return receiveShoot();
	}

	@Override
	public boolean receiveThunder() {
		super.alive = false;
		return true;
	}

}
