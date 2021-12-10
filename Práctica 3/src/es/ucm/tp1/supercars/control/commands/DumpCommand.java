package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class DumpCommand extends Command {

	private static final String NAME = "dump";

	private static final String DETAILS = "[d]ump <filename>";

	private static final String SHORTCUT = "d";

	private static final String HELP = "Shows the content of a saved file";

	private String filename;
	
	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	public DumpCommand(String filename) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.filename = filename + ".txt";
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    System.out.println(everything);
		} catch(Exception e) {
			throw new CommandExecuteException("An error ocurred on reading a file");
		}
		return false;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		if (super.matchCommandName(commandWords[0])) {
			if (commandWords.length != 2) throw new CommandParseException("[ERROR]: Command dump: Incorrect number of arguments");
			return new DumpCommand(commandWords[1]);
		}
		
		return super.parse(commandWords);
	}
}
