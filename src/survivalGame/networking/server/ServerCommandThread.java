package survivalGame.networking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;

import survivalGame.networking.commands.Command;
import survivalGame.networking.commands.ServerCommands;

public class ServerCommandThread extends Thread {
	
	private boolean isRunning;
	private DatagramSocket serverSocket;
	
	public void startServerThread(DatagramSocket serverSocket) {
		this.isRunning = true;
		this.setServerSocket(serverSocket);
		
		this.start();
	}
	
	@Override
	public void run() {
		while(isRunning) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			String string = null;
			try {
				string = reader.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			String[] commandArgs = string.split(" ");
			callCommand(commandArgs);
		}
	}
	
	private void callCommand(String[] args) {
		for(Command command : ServerCommands.getServerCommands()) {
			if(args[0].contains(command.getCommandName())) {
				command.execute(args); 
				return;
			}
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public DatagramSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(DatagramSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
}
