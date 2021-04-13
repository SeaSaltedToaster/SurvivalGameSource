package survivalGame.networking.server.packets;

public class ServerClosePacket extends Packet {

	public ServerClosePacket() {
		super(03);
	}

	@Override
	public byte[] getData() {
		return new String("03"+"Server_Closed_By_Host").getBytes();
	}

}
