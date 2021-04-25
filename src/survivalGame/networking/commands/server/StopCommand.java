package survivalGame.networking.commands.server;

import survivalGame.networking.commands.Command;
import survivalGame.networking.server.Server;

public class StopCommand extends Command {

	public StopCommand() {
		super("stop", "Stops the server and removes and players.");
	}

	@Override
	public void execute(String[] args) {
		Server.stopServer();
	}

}
