package game;

import java.util.Objects;

import engine.Launcher;
import engine.interfaces.Repository;
import game.Action.Stackable;
import game.Action.Walkable;
import game.actions.WalkAction;

/**
 * Created with eclipse 24/03/2015 8:18:35 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class ActionRepository implements Repository<Action<?>> {

	private Action<?> action = null;
	
	@Override
	public void add(Action<?> source) {	
		boolean shouldBreak = shouldBreakCurrentAction(source);
		
		if (shouldBreak) {
			synchronized (action) {
				action.cancel();
			}
		}
		
		Launcher.getServer().getWorld().getPool().add(action = source);
	}

	@Override
	public void remove(Action<?>  source) {
        throw new UnsupportedOperationException();
	}

	@Override
	public Action<?> get(int slot) {
        throw new UnsupportedOperationException();
	}

	@Override
	public int getCapacity() {
		return -1;
	}

	private boolean shouldBreakCurrentAction(final Action<?> peek) {
		if (Objects.isNull(action) || Objects.isNull(peek))
			return false;

		final Walkable currentWalkable = action.getWalkable();
		final Stackable currentStackable = action.getStackable();
		
		boolean breakByWalking = (currentWalkable.equals(Walkable.BREAK_ON_WALKING) && peek instanceof WalkAction);
		boolean breakByStack = (currentStackable.equals(Stackable.BREAK_ON_STACK));

		return breakByWalking || breakByStack;
	}
}
