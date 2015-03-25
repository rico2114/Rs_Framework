package game.events;

import game.players.Player;
import networking.interpreter.PacketEvent;

/**
 * Created with eclipse 21/03/2015 11:53:03 a. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class ButtonClickEvent implements PacketEvent<Player> {

	private final int buttonId;
	private final int interfaceId;
	
	public ButtonClickEvent(final int buttonId, final int interfaceId) {
		this.buttonId = buttonId;
		this.interfaceId = interfaceId;
	}
	
	@Override
	public boolean isValid(Player player) {
		// TODO:
		// if (interfaceId != player.getCurrentInterface()) return false;
		// final InterfaceConfigs config = InterfaceConfigs.get(player.getCurrentInterface());
		// if (config.contains(buttonId) return true;
		return false;
	}
	
	public int getButtonId() {
		return buttonId;
	}
	
	public int getInterfaceId() {
		return interfaceId;
	}
}
