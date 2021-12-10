package es.ucm.tp1.supercars.logic.instantActions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.InstantAction;

public class ShootAction implements InstantAction{

	@Override
	public void execute(Game game) {
		boolean shooted = false;
		int x = game.getPlayerXPosition();
		int maxColumns = 0;
		
		while(!shooted && maxColumns < 7) {
			maxColumns++;
			if(game.getObjectInPosition(x, game.getPlayerYPosition()) != null) {
				Collider object = game.getObjectInPosition(x, game.getPlayerYPosition());
				shooted = object.receiveShoot();
			}
			x++;
		}
	}

}
