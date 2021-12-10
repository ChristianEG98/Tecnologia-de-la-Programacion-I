package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.GameSerializer;

public class SaveCommand extends Command {

	private static final String NAME = "save";

	private static final String DETAILS = "sa[v]e <filename>";

	private static final String SHORTCUT = "v";

	private static final String HELP = " Save the state of the game to a file.";

	private String filename;
	
	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public SaveCommand(String filename) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.filename = filename + ".txt";
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
			GameSerializer gs = new GameSerializer(game);
			writer.write("Super cars 3.0\n\n");
			writer.write(gs.serializerInfo());
			System.out.println("Game successfully saved to file " + filename);
		}
		catch(IOException e) {
			throw new CommandExecuteException(String.format("[ERROR]: %s", e.getMessage()));
		}
		return false;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		if (super.matchCommandName(commandWords[0]) ) {
			if (commandWords.length != 2) throw new CommandParseException("[ERROR]: Command save: Incorrect number of arguments");
			return new SaveCommand(commandWords[1]);
		}
		
		return super.parse(commandWords);
	}
}
