package tp1.control.commands;

import tp1.view.Messages;
import java.util.Arrays;
import java.util.List;

public class CommandGenerator {

    private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
        new ActionCommand(),
        new UpdateCommand(),
        new ResetCommand(),
        new HelpCommand(),
        new ExitCommand()
    );

	public static Command parse(String[] commandWords) {		
		for (Command c: AVAILABLE_COMMANDS) {
			Command parsed = c.parse(commandWords);
            if (parsed != null) return parsed;
		}
		return null;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		
		for (Command c: AVAILABLE_COMMANDS) {
			commands.append(c.helpText()).append(Messages.LINE_SEPARATOR);
		}
		
		return commands.toString();
	}
}
