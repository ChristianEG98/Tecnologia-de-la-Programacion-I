package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.Game;

public abstract class Command {

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";

	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";

	/* @formatter:off */
	private static final Command[] AVAILABLE_COMMANDS = {
		new HelpCommand(),
		new InfoCommand(),
		new UpdateCommand(),
		new GoUpCommand(),
		new GoDownCommand(),
		new ExitCommand(),
		new ResetCommand(),
		new TestCommand(),
		new ShootCommand(),
		new GrenadeCommand(),
		new WaveCommand(),
		new ClearCommand(),
		new CheatCommand(),
	};
	/* @formatter:on */

	public static Command getCommand(String[] commandWords, Level level) {
		Command command = null;
		int i = 0;
		while(AVAILABLE_COMMANDS.length > i) {
			if(AVAILABLE_COMMANDS[i].parse(commandWords) == null) {
				i++;
			}
			else {
				return AVAILABLE_COMMANDS[i].parse(commandWords);
			}
		}
		return command;
	}

	private final String name;

	private final String shortcut;

	private final String details;

	private final String help;

	public Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}

	public abstract boolean execute(Game game);

	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	protected Command parse(String[] words) {
		if (matchCommandName(words[0])) {
			if(words.length == 3 && matchCommandName("reset")){
				System.out.format("[ERROR]: Command %s: Level must be one of: TEST, EASY, HARD, ADVANCED%n%n", shortcut);
			}
			else if (words.length != 1) {
				System.out.format("[ERROR]: Command %s: %s%n%n", shortcut, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				return this;
			}
		}
		return null;
	}
	
	private String getCommandInfo() {
		return details + ": " + help + "\n";
	}
	
	protected StringBuilder commandsInfo() {
		StringBuilder buffer = new StringBuilder();
		for(int i  = 0; i < AVAILABLE_COMMANDS.length; i++) {
			
			buffer.append(AVAILABLE_COMMANDS[i].getCommandInfo());
		}
		return buffer;
	}
}
