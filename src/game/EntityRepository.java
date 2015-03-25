package game;

import java.lang.reflect.Array;

import utils.ArrayUtils;
import engine.interfaces.Repository;
import game.updating.EntitySectorUpdating;
import game.updating.UpdatingRecursiveAction;

/**
 * Created with eclipse 11/03/2015 10:40:23 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public abstract class EntityRepository<E extends Entity> implements Repository<E> {

	private E [] entities;
	private Class<E> clasz;
	private int capacity;
	
	@SuppressWarnings("unchecked")
	public EntityRepository(final Class<E> clasz, final int capacity) {
		this.clasz = clasz;
		this.capacity = capacity;
		this.entities = (E[]) Array.newInstance(clasz, capacity);
	}
	
	@SuppressWarnings("unchecked")
	public void update(final int index, final int start, final int end) {
		if (ArrayUtils.exceedsSize(start, entities) || ArrayUtils.exceedsSize(end, entities))
			return;
		
		final int length = end - start;
		final E [] entityCopy = (E[]) Array.newInstance(clasz, length);
		System.arraycopy(entities, start, entityCopy, 0, length);
		
		final EntitySectorUpdating<E> updating = createUpdating(index, entityCopy);
		updating.update();
	}

	@Override
	public void add(E source) {
		final int slot = source.getSlot();
		if (ArrayUtils.exceedsSize(slot, entities))
			return;
		
		entities[slot] = source;
	}

	@Override
	public void remove(E source) {
		entities[source.getSlot()] = null;
	}

	@Override
	public E get(int slot) {
		if (ArrayUtils.exceedsSize(slot, entities))
			return null;
		
		return entities[slot];
	}

	@Override
	public int getCapacity() {
		return capacity;
	}
	
	public abstract UpdatingRecursiveAction createRecursiveAction();
	
	public abstract EntitySectorUpdating<E> createUpdating(final int index, final E [] elements);
	
	public Class<?> getType() {
		return clasz;
	}
}
