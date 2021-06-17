package survivalGame.guis.chat;

import org.lwjgl.glfw.GLFW;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.input.InputHandler;
import seaSaltedEngine.basic.input.Mouse;
import seaSaltedEngine.basic.input.keybinding.Controls;
import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.guis.Listener;
import seaSaltedEngine.guis.core.UiColors;
import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.Fonts;
import seaSaltedEngine.guis.text.Text;
import survivalGame.guis.GameMenus;
import survivalGame.main.states.MainGame;

public class UiChat extends UiComponent implements Listener {

	//Variables
	private boolean isChatOpen = false;
	private String text = " ";
	private StringBuilder builder;
	
	//Components
	private UiComponent textBox;
	private Text chatText;
	
	//Previous Chats
	private static UiChatLog log;
	private Text[] textLog;
	private int logSize = 0;
	
	public UiChat() {
		super(1);
		initBackground();
		initTextBox();
		Engine.getInputHandler().getKeyboardInstance().getEvent().addListener(this);
	}
	
	private void initTextBox() {
		textBox = new UiComponent(3);
		textBox.setScale(0.4f, 0.03f);
		textBox.setPosition(-0.8f, -0.925f);
		textBox.setAlpha(0.75f);
		this.addComponent(textBox);
		
		chatText = new Text("", 1f, Fonts.ARIAL, 20f, false);
		chatText.setPosition(0f, 1.89f);
		chatText.setColour(UiColors.WHITE.getVec3f());
		this.textBox.addComponent(chatText);
		
		this.builder = new StringBuilder();
		UiChat.log = new UiChatLog();
		
		this.textLog = new Text[log.getMAX_SESSION_CHAT()];
	}

	@Override
	public void updateSelf() {
		checkOpen();
		updateChatLog();
		if(isChatOpen) Engine.getCamera().setCancelUpdate(true);
	}

	private void updateChatLog() {
		if(log.getCurrentChatSize() > logSize) {
			Text text = new Text(log.getChatLog()[logSize], 1f, Fonts.ARIAL, 20f, false);
			text.setPosition(0f, 1.89f);
			text.setColour(UiColors.WHITE.getVec3f());
			this.addComponent(text);
			textLog[logSize] = text;
			updateTextPositions();
			logSize++;
		}
	}

	private void updateTextPositions() {
		for(Text text : textLog) {
			if(text == null) return;
			text.increasePosition(0f, -0.06f);
		}
	}

	private void checkOpen() {
		if(InputHandler.isKeyPressed(Controls.getControl("Send Chat").getKey())) {
			sendChatMessage(text, MainGame.getPlayerEntitiy().getUsername());
			text = " "; chatText.setTextString(text); chatText.reset();
			close();
		} else if(InputHandler.isKeyPressed(Controls.getControl("Pause / Exit").getKey())) {
			close();
		} else if(InputHandler.isKeyPressed(Controls.getControl("Open Chat").getKey()) 
				&& !GameMenus.isInMenu() && !isChatOpen) {
			open();
		} 
	}
	
	public static void sendChatMessage(String message, String sender) {
		if(message == " ") return;
		log.addChatLog(sender, message);
		checkCommands(message, sender);
	}

	private void open() {
		this.setActive(true);
		this.isChatOpen = true;
		Mouse.setMouseVisible(true);
		GameMenus.setInMenu(true);
	}

	private static void checkCommands(String string, String userName) {
		if(string.startsWith(" /")) {
			Logger.Log(userName + " has run the command" + string.replace("/", ""));
		}
	}

	private void close() {
		this.setActive(false);
		this.isChatOpen = false;
		Mouse.setMouseVisible(false);
		GameMenus.setInMenu(false);
	}

	private void initBackground() {
		this.setActive(false);
		this.setScale(0.4f, 0.4f);
		this.setPosition(-0.8f, -0.5f);
		this.setAlpha(0.75f);
	}

	@Override
	public void notify(String update) {
		if(!isChatOpen || Integer.parseInt(update.split(":;:")[1]) == 0) return;
		String keyName = getKeyName(update);
		if(checkSpecialChar(Integer.parseInt(update.split(":;:")[0]))) return;
		text = text + keyName; chatText.setTextString(text); chatText.reset();
	}
	
	private boolean checkSpecialChar(int keyID) {
		if(keyID == GLFW.GLFW_KEY_SPACE) {
			builder.append(" ");
			text += new String(" ");
			chatText.setTextString(text); chatText.reset();
			return true;
		}
		if(keyID == GLFW.GLFW_KEY_BACKSPACE) {
			if(text.length() != 0)
				text = text.substring(0, text.length()-1);
			chatText.setTextString(text); chatText.reset();
			return true;
		}
		chatText.setTextString(text); chatText.reset();
		return false;
	}

	private String getKeyName(String update) {
		int reduced = Integer.parseInt(update.split(":;:")[0]);
		String keyName = GLFW.glfwGetKeyName(reduced, GLFW.glfwGetKeyScancode(reduced));
		if(keyName == null || keyName.equalsIgnoreCase("null")) return "";
		return keyName;
	}

	@Override
	public void cancel() {
		//Cancel when chat is over
	}

}
