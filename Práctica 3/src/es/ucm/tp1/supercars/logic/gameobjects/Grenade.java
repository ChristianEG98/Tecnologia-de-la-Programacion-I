package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.instantActions.ExplosionAction;

public class Grenade extends GameObject{

	private static int counter = 0;
	public static final String INFO = "[GRENADE] Explodes in 3 cycles, harming everyone around";
	private int countdown;
	
	public Grenade(Game game, int x, int y) {
		super(game, x, y);
		countdown = 4;
		super.symbol = "ð[" + countdown + "]";
	}

	@Override
	public boolean doCollision() {
		super.alive = false;
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
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
		countdown--;
		super.symbol = "ð[" + countdown + "]";
		if(countdown == 0) {
			doCollision();
		}
	}

	@Override
	public void onDelete() {
		counter--;
		ExplosionAction explosion = new ExplosionAction(super.x, super.y);
		explosion.execute(game);
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
	public String serialize() {
		return super.serialize() + " " + counter;
	}
}
