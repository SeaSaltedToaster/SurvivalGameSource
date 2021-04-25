package survivalGame.networking.commands.server;

import seaSaltedEngine.basic.logger.Logger;
import survivalGame.networking.commands.Command;
import survivalGame.networking.commands.ServerCommands;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("help", "Lists every command name and description.");
	}

	@Override
	public void execute(String[] args) {
		Logger.ServerLog("Every server command ("+ServerCommands.getServerCommands().size()+"):");
		for(Command command : ServerCommands.getServerCommands()) {
			Logger.ServerLog("/"+command.getCommandName()+": "+command.getCommandDescription());
		}
	}

}
