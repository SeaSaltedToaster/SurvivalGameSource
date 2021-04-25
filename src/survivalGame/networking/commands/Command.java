package survivalGame.networking.commands;

public abstract class Command {

	private String commandName;
	private String commandDescription;
	
	public Command(String name, String description) {
		this.commandName = name;
		this.commandDescription = description;
	}
	
	public abstract void execute(String[] args);

	public String getCommandName() {
		return commandName;
	}

	public String getCommandDescription() {
		return commandDescription;
	}
	
}
