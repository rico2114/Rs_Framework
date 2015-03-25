package game.updating;

import game.Entity;

/**
 * Created with eclipse 13/03/2015 11:36:05 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public abstract class EntitySectorUpdating<E extends Entity> implements Updateable<Void> {

	private final E [] entities;
	
	public EntitySectorUpdating(final E [] entities) {
		this.entities = entities;
	}	
	
	public final E [] getEntities() {
		return entities;
	}
}
