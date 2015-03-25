package networking.interpreter.impl;

import game.events.ButtonClickEvent;
import networking.Packet;
import networking.interpreter.PacketInterpreter;

/**
 * Created with eclipse 21/03/2015 11:52:53 a. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class ButtonClickInterpreter implements PacketInterpreter<ButtonClickEvent> {

	@Override
	public ButtonClickEvent interpret(Packet packet) {
		final int id = packet.getBuffer().getShort();
		final int interfaceId = packet.getBuffer().getInt();
		return new ButtonClickEvent(id, interfaceId);
	}

}
