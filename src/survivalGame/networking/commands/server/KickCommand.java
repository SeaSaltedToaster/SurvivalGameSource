package survivalGame.networking.commands.server;

import survivalGame.networking.commands.Command;
import survivalGame.networking.server.Server;

public class KickCommand extends Command {

	public KickCommand() {
		super("kick", "Temporarily kicks a specific player.");
	}

	@Override
	public void execute(String[] args) {
		int index = Integer.parseInt(args[1]);
		Server.kickPlayer(index);
	}

}
