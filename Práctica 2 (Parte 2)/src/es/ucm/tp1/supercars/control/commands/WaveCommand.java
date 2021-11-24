package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.instantActions.WaveAction;

public class WaveCommand extends Command implements Buyable{

	private static final String NAME = "wave";

	private static final String DETAILS = "[w]ave";

	private static final String SHORTCUT = "w";

	private static final String HELP = "do wave";
	
	private WaveAction wave = new WaveAction();
	
	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		if(buy(game)) {
			wave.execute(game);
			game.increaseForwadsCells();
		}
		else {
			System.out.print("Not enough coins\n");
			System.out.print("[ERROR]: Failed to wave\n");
			return false;
		}
		return true;
	}

	@Override
	public int cost() {
		return 5;
	}
}
