package survivalGame.networking.server.packets;

public class DisconnectPacket extends Packet {

	private String username;

    public DisconnectPacket(byte[] data) {
        super(01);
        this.username = readData(data);
    }

    public DisconnectPacket(String username) {
        super(01);
        this.username = username;
    }

    @Override
    public byte[] getData() {
        return ("01" + this.username).getBytes();
    }

    public String getUsername() {
        return username;
    }

}
