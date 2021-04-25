package survivalGame.networking.commands;

import java.util.ArrayList;
import java.util.List;

import survivalGame.networking.commands.server.HelpCommand;
import survivalGame.networking.commands.server.KickCommand;
import survivalGame.networking.commands.server.StopCommand;

public class ServerCommands {
	
	//List of all registered commands
	private static List<Command> serverCommands;
	
	//Register all built in commands
	public static void registerCommands() {
		serverCommands = new ArrayList<Command>();
		registerNewCommand(new StopCommand());
		registerNewCommand(new KickCommand());
		registerNewCommand(new HelpCommand());
	}
	
	//Register New Command
	public static void registerNewCommand(Command command) {
		serverCommands.add(command);
	}

	public static List<Command> getServerCommands() {
		return serverCommands;
	}

}
