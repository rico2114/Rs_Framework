package networking.interpreter;

import engine.interfaces.Event;
import game.Entity;

/**
 * Created with eclipse 21/03/2015 11:54:35 a. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public interface PacketEvent<E extends Entity> extends Event {

	boolean isValid(final E player);
}
