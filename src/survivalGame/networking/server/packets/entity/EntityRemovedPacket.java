package survivalGame.networking.server.packets.entity;

import survivalGame.entity.core.EntityIdentifier;
import survivalGame.networking.server.packets.Packet;

public class EntityRemovedPacket extends Packet {

	private EntityIdentifier identifier;
	
	public EntityRemovedPacket(EntityIdentifier identifier) {
		super(05);
		this.identifier = identifier;
		
	}

	@Override
	public byte[] getData() {
		return new String("05" + "EntityRemoved-[Identifier,"+identifier.getEntityID()+","+identifier.getType().name()+",]").getBytes();
	}

	public EntityIdentifier getIdentifier() {
		return identifier;
	}

}
