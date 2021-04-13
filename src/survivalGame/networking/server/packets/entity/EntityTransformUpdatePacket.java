package survivalGame.networking.server.packets.entity;

import seaSaltedEngine.basic.objects.Transform;
import survivalGame.entity.core.EntityIdentifier;
import survivalGame.networking.server.packets.Packet;

public class EntityTransformUpdatePacket extends Packet {

	private EntityIdentifier identifier;
	private Transform transform;
	
	public EntityTransformUpdatePacket(EntityIdentifier identifier, Transform transform) {
		super(06);
		
		this.identifier = identifier;
		this.transform = transform;
	}

	@Override
	public byte[] getData() {
		String baseEntityString = new String("06" + "EntityTransformUpdate-[Identifier,"+identifier.getEntityID()+","+identifier.getType().name()+",]-[Transform,"+transform.toString()+",]-");
		return baseEntityString.getBytes();
	}

	public EntityIdentifier getIdentifier() {
		return identifier;
	}

	public Transform getTransform() {
		return transform;
	}

}
