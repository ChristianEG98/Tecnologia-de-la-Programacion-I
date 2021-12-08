package es.ucm.tp1.supercars.logic.instantActions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.InstantAction;

public class ThunderAction implements InstantAction{

	private int x, y;
	private String thunder;
	@Override
	public void execute(Game game) {
		thunder = "";
		x = game.getRandomColumn() + game.getPlayerXPosition();
		y = game.getRandomLane();
		if(x < game.getPlayerXPosition() + game.getVisibility()) {
			Collider c = game.getObjectInPosition(x, y);
			if(c != null) {
				if(c.receiveThunder()) {
					thunder = " -> " + c.toString();
					c.doCollision();
				}
			}
			else {
				thunder = "";
			}
		}
		System.out.print("Thunder hit position: (" + (x - game.getPlayerXPosition()) + " , " + y + ")" + thunder + "\n");
	}

}
