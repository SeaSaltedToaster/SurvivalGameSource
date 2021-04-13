package survivalGame.networking.server.packets;

public class ConnectionPacket extends Packet {

	private String username;

    public ConnectionPacket(byte[] data) {
        super(00);
        String[] dataArray = readData(data).split(",");
        this.username = dataArray[0];
    }

    public ConnectionPacket(String username) {
        super(00);
        this.username = username;
    }
    
    @Override
    public byte[] getData() {
        return ("00" + this.username).getBytes();
    }

    public String getUsername() {
        return username;
    }
	
}
