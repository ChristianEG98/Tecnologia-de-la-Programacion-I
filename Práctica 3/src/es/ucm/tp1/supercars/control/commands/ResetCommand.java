package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.exceptions.InputOutputRecordException;
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
	public boolean execute(Game game) throws InputOutputRecordException {
		if(level == null) game.reset();
		else {
			System.out.println("Level: " + level.toString());
			System.out.println("Random generator initialized with seed: " + seed);
			game.reset(level, seed);
		}
		
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException{
		if (super.matchCommandName(commandWords[0]) && commandWords.length == 3) {
			long seed;
			boolean levelFound = false;
			for(Level level: Level.values()) {
				if(level.name().equalsIgnoreCase(commandWords[1])) levelFound = true;
			}
			if(!levelFound) throw new CommandParseException("[ERROR]: Command r: Level must be one of: TEST, EASY, HARD, ADVANCED");
			try {
				seed = Long.parseLong(commandWords[2]);
			} catch(NumberFormatException e) {
				throw new CommandParseException("[ERROR]: Command r: the seed is not a proper long number");
			}
			return new ResetCommand(Level.valueOfIgnoreCase(commandWords[1]), seed);
		}
		return super.parse(commandWords);
	}
}
