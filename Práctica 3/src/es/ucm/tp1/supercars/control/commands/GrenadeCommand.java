package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.exceptions.InvalidPositionException;
import es.ucm.tp1.supercars.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.GameObjectGenerator;

public class GrenadeCommand extends Command implements Buyable{

	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade <x> <y>";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private int x, y;

	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public GrenadeCommand(int x, int y) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if(!game.isValidVisiblePosition(x, y)) throw new InvalidPositionException("Invalid position.\n");
			buy(game);
			GameObjectGenerator.generateGrenade(game, x + game.getCycles(), y);
		} catch(InvalidPositionException | NotEnoughCoinsException e) {
			throw new CommandExecuteException(String.format("%s[ERROR]: Failed to add grenade", e.getMessage()));
		}

		game.update();
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		int x, y;
		if (super.matchCommandName(commandWords[0])) {
			if(commandWords.length != 3) throw new CommandParseException("[ERROR]: Incorrect number of arguments for grenade command: " + DETAILS);
			try {
				x = Integer.parseInt(commandWords[1]);
				y = Integer.parseInt(commandWords[2]);
			} catch(NumberFormatException e) {
				throw new CommandParseException("[ERROR]: Number expected");
			}
			return new GrenadeCommand(x, y);
		}
		return super.parse(commandWords);
	}

	@Override
	public int cost() {
		return 3;
	}
}
