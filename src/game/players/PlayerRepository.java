package game.players;

import engine.Constants;
import game.EntityRepository;
import game.updating.EntitySectorUpdating;
import game.updating.UpdatingRecursiveAction;
import game.updating.player.PlayerSectorUpdating;
import game.updating.player.PlayerUpdatingRecursiveAction;

/**
 * Created with eclipse 13/03/2015 11:16:36 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class PlayerRepository extends EntityRepository<Player> {

	public PlayerRepository() {
		super(Player.class, Constants.MAXIMUM_PLAYERS_SIZE.getValue());
	}
	
	/**
	 * Baaaa i just have this for testing
	 */
	public void initAll() {
		for (int i = 0; i < getCapacity(); i++) {
			final Player p = new Player("[" + i + "]", i);
			add(p);
			
		}
		System.out.println("INITIALIZED ALL!");
	}

	@Override
	public EntitySectorUpdating<Player> createUpdating(final int index, Player[] elements) {
		return new PlayerSectorUpdating(index, elements);
	}

	@Override
	public UpdatingRecursiveAction createRecursiveAction() {
		int size = Constants.MAXIMUM_PLAYERS_SIZE.getValue();
		return new PlayerUpdatingRecursiveAction(0, this, 0, size);
	}

}
