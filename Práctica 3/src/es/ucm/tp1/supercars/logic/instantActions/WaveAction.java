package es.ucm.tp1.supercars.logic.instantActions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.InstantAction;

public class WaveAction implements InstantAction{

	@Override
	public void execute(Game game) {
		for(int y = 0; y < game.getRoadWidth(); y++) {
			for(int x = game.getRoadLenght() - 1; x >= game.getPlayerXPosition(); x--) {
				Collider c = game.getObjectInPosition(x, y);
				Collider cNext = game.getObjectInPosition(x + 1, y);
				if(c != null && cNext == null && x <= game.getLastVisibleColumn()) {
					c.receiveWave();
				}
			}
		}
	}

}
