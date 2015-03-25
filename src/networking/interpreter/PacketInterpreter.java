package networking.interpreter;

import networking.Packet;

/**
 * Created with eclipse 21/03/2015 11:52:20 a. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public interface PacketInterpreter<T extends PacketEvent<?>> {

	T interpret(final Packet packet);
}
