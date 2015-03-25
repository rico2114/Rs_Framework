package game.players;

import game.Entity;

/**
 * Created with eclipse 13/03/2015 11:09:09 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class Player extends Entity {

	private final String username;
	
	public Player(final String username, int slot) {
		super(slot);
		this.username = username;
	}

	@Override
	public String toString() {
		return "[Player] Username (" + username + ").";
	}
}
