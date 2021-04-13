package survivalGame.networking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;

import seaSaltedEngine.basic.logger.Logger;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.tools.math.Vector3f;
import survivalGame.entity.EntityPickaxeTest;
import survivalGame.entity.core.EntityIdentifier;
import survivalGame.entity.core.EntityType;
import survivalGame.networking.server.packets.entity.EntityCreatedPacket;
import survivalGame.networking.server.packets.entity.EntityTransformUpdatePacket;

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
			if(commandArgs[0].contains("/rotate")) {
				EntityTransformUpdatePacket entityRemovePacket = new EntityTransformUpdatePacket(new EntityIdentifier(Integer.parseInt(commandArgs[1]), EntityType.PICKAXE_TEST), new Transform(new Vector3f(0,0,0),0,0,Integer.parseInt(commandArgs[2])));
				entityRemovePacket.writeData();
				
				Logger.ServerLog("Rotated Entity With ID of "+commandArgs[1] + " at an angle of "+commandArgs[2] + " degrees.");
			}
			if(commandArgs[0].contains("/cube")) {
				EntityCreatedPacket entityRemovePacket = new EntityCreatedPacket(new EntityPickaxeTest(Transform.Default), new EntityIdentifier(1, EntityType.PICKAXE_TEST));
				entityRemovePacket.writeData();
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
