package survivalGame.guis.chat;

import seaSaltedEngine.basic.logger.Logger;

public class UiChatLog {

	//Log
	private String[] chatLog;
	private int MAX_SESSION_CHAT = 100;
	
	//Current
	private int currentChatSize = 0;
	
	public UiChatLog() {
		chatLog = new String[MAX_SESSION_CHAT];
	}
	
	public void addChatLog(String playerName, String message) {
		String nameMessage = ("<" + playerName + "> ");
		String finalMessage = nameMessage + message;
		Logger.Log(finalMessage);
		chatLog[currentChatSize] = finalMessage;
		currentChatSize++;
	}

	public String[] getChatLog() {
		return chatLog;
	}

	public int getCurrentChatSize() {
		return currentChatSize;
	}

	public int getMAX_SESSION_CHAT() {
		return MAX_SESSION_CHAT;
	}

}
