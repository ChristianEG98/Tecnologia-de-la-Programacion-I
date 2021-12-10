package es.ucm.tp1.supercars.logic.instantActions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.InstantAction;

public class ExplosionAction implements InstantAction{

	private final int damage = 1;
	private int x, y;
	
	public ExplosionAction(int i, int j) {
		x = i;
		y = j;
	}

	@Override
	public void execute(Game game) {
		for(int i = -damage; i <= damage; i++) {
			for(int j = -damage; j <= damage; j++) {
				Collider c = game.getObjectInPosition(x + i, y + j);
				if(c != null) {
					c.receiveExplosion();
				}
			}
		}
	}
	
}
