package game.updating.player;

import game.players.Player;
import game.updating.EntitySectorUpdating;

/**
 * Created with eclipse 13/03/2015 11:56:28 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class PlayerSectorUpdating extends EntitySectorUpdating<Player> {

	private final int index;
	
	public PlayerSectorUpdating(final int index, Player[] entities) {
		super(entities);
		this.index = index;
	}

	@Override
	public Void update() {
		System.out.println("Player Updating, Sector [" + index + "] Thread [" + Thread.currentThread().getId() + "]");
		return null;
	}

}
