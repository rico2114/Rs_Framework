package game.actions;

import game.Action;
import game.Entity;
import game.events.WalkEvent;

/**
 * Created with eclipse 24/03/2015 9:29:17 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class WalkAction extends Action<Entity> {

	private final WalkEvent event;
	
	public WalkAction(final Entity entity, final WalkEvent event, int cycles) {
		super(entity, Walkable.BREAK_ON_WALKING, Stackable.BREAK_ON_STACK, cycles);
		this.event = event;
	}

	@Override
	public void run() {
		final Entity entity = getEntity();
		if (event.isValid(entity)) {
			cancel();
			return;
		}
		
		// TODO: walk entity.getWalkingQueue().walkTo(event.getDestination);
	}
}
