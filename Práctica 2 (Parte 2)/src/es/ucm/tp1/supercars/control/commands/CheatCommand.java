package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.GameObjectGenerator;

public class CheatCommand extends Command{

	private static final String NAME = "cheat";

	private static final String DETAILS = "Cheat [1..5]";

	private static final String SHORTCUT = "1..5";

	private static final String HELP = "Removes all elements of last visible column, and adds an Advanced Object";
	
	private int id;
	
	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public CheatCommand(int id) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.id = id;
	}

	@Override
	public boolean execute(Game game) {
		int lastColumn = game.getLastVisibleColumn();
		game.clearColumn(lastColumn);
		GameObjectGenerator.forceAdvanceObject(game, id, lastColumn);
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) {
		try {
			int id = Integer.parseInt(commandWords[0]);
			if(id >= GameObjectGenerator.MIN_ID && id <= GameObjectGenerator.MAX_ID) {
				return new CheatCommand(id);
			}
		}
		catch(Exception e) {
		}

		return super.parse(commandWords);
	}
}
