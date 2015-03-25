package game.events;

import game.Entity;
import game.Position;
import networking.interpreter.PacketEvent;

/**
 * Created with eclipse 24/03/2015 10:02:04 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class WalkEvent implements PacketEvent<Entity> {

	private final Position destination;
	
	public WalkEvent(final Position destination) {
		this.destination = destination;
	}
	
	@Override
	public boolean isValid(Entity entity) {
		return false;
	}

	public Position getDestination() {
		return destination;
	}
}
