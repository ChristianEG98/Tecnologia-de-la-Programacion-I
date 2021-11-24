package es.ucm.tp1.supercars.logic.instantActions;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.InstantAction;

public class WaveAction implements InstantAction{

	@Override
	public void execute(Game game) {
		game.waveAction();
	}

}
