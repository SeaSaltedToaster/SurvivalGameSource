package survivalGame.networking.server.packets;

import survivalGame.networking.client.Client;
import survivalGame.networking.server.Server;

public abstract class Packet {
	
	public byte packetId;

    public Packet(int packetId) {
        this.packetId = (byte) packetId;
    }

    public void writeData(Client client) {
    	client.send(new String(getData()));
    }

    public void writeData() {
    	Server.sendDataToAllClients(new String(getData()));
    }

    public String readData(byte[] data) {
        String message = new String(data).trim();
        return message.substring(2);
    }

    public abstract byte[] getData();

    public static PacketTypes lookupPacket(String packetId) {
        try {
            return lookupPacket(Integer.parseInt(packetId));
        } catch (NumberFormatException e) {
            return PacketTypes.INVALID;
        }
    }

    public static PacketTypes lookupPacket(int id) {
        for (PacketTypes p : PacketTypes.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return PacketTypes.INVALID;
    }

}
