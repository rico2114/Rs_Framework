package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

	private List<Action<?>> actions = new ArrayList<>();
	
	@Override
	public void add(Action<?> source) {	
		final List<Action<?>> breakActions = breakCurrentActions(source);
		
		for (Action<?> action : breakActions) {
			cancel(action);
		}
		
		actions.add(source);
		Launcher.getServer().getWorld().getPool().add(source);
	}

	@Override
	public void remove(Action<?> source) {
		cancel(source);
	}

	@Override
	public Action<?> get(int slot) {
        throw new UnsupportedOperationException();
	}

	@Override
	public int getCapacity() {
		return -1;
	}
	
	private void cancel(final Action<?> action) {
		synchronized (action) {
			action.cancel();
		}
		actions.remove(action);
	}

	private List<Action<?>> breakCurrentActions(final Action<?> peek) {
		final List<Action<?>> list = Collections.emptyList();
		if (actions.isEmpty() || Objects.isNull(peek))
			return list;

		boolean breakByWalking = false;
		boolean breakByStack = false;

		for (Action<?> action : actions) {
			final Walkable currentWalkable = action.getWalkable();
			final Stackable currentStackable = action.getStackable();

			breakByWalking = (currentWalkable.equals(Walkable.BREAK_ON_WALKING) && peek instanceof WalkAction);
			breakByStack = (currentStackable.equals(Stackable.BREAK_ON_STACK));
			
			if (breakByWalking || breakByStack) 
				list.add(action);
		}
		return list;
	}
}
