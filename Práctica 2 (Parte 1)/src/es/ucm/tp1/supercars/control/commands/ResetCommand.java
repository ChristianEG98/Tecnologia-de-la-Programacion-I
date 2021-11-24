package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.Game;

public class ResetCommand extends Command{

	private static final String NAME = "reset";

	private static final String DETAILS = "[r]eset [<level> <seed>]";

	private static final String SHORTCUT = "r";

	private static final String HELP = "reset game";
	
	private Level level;
	
	private Long seed;

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	public ResetCommand(Level level, Long seed) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.level = level;
		this.seed = seed;
	}
	
	@Override
	public boolean execute(Game game) {
		if(level == null) game.reset();
		else game.reset(level, seed);
		
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) {
		if (super.matchCommandName(commandWords[0]) && commandWords.length == 3) {
			return new ResetCommand(Level.valueOfIgnoreCase(commandWords[1]), Long.parseLong(commandWords[2]));
		}
		return super.parse(commandWords);
	}
}
