package game.content.minigames.duel;

import game.players.Player;

/**
 * Created with eclipse 26/03/2015 5:56:45 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class DuelSession {

	private Player player;
	private Player rival;
	
	public DuelSession(final Player player, final Player rival) {
		this.player = player;
		this.rival = rival;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Player getRival() {
		return rival;
	}
}
