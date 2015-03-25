package engine;

/**
 * Created with eclipse 13/03/2015 11:17:57 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public enum Constants {

	/**
	 * Represents the maximum available players
	 */
	MAXIMUM_PLAYERS_SIZE(2000),
	CYCLE_RATE(600);
	
	private final Object value;
	
	Constants(final Object value) {
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue() {
		return (T) value;
	}
}
