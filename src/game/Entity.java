package game;

/**
 * Created with eclipse 11/03/2015 10:40:33 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class Entity {

	private final ActionRepository actionRepository = new ActionRepository();
	
	private final Position position = new Position(3500, 3500);
	private final int slot;
	
	public Entity(final int slot) {
		this.slot = slot;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public ActionRepository getActionRepository() {
		return actionRepository;
	}
}
