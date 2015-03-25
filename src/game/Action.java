package game;

import engine.interfaces.Task;

/**
 * Created with eclipse 24/03/2015 8:19:25 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public abstract class Action<T extends Entity> extends Task {

	private final T entity;
	private final Walkable walkable;
	private final Stackable stackable;
	
	public Action(final T entity, final Walkable walkable, final Stackable stackable, int cycles) {
		super(cycles);
		this.entity = entity;
		this.walkable = walkable;
		this.stackable = stackable;
	}
	
	public Walkable getWalkable() {
		return walkable;
	}
	
	public Stackable getStackable() {
		return stackable;
	}
	
	public T getEntity() {
		return entity;
	}

	public enum Walkable {
		WALKABLE,
		BREAK_ON_WALKING
	}
	
	public enum Stackable {
		STACKABLE,
		BREAK_ON_STACK
	}
}
