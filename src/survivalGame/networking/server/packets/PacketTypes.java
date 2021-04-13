package survivalGame.networking.server.packets;

public enum PacketTypes {
	
	INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(02), SERVER_CLOSE(03), ENTITY_CREATED(04), ENTITY_REMOVED(05), ENTITY_TRANSFORM_UPDATE(06);

    private int packetId;

    private PacketTypes(int packetId) {
        this.packetId = packetId;
    }

    public int getId() {
        return packetId;
    }

}
