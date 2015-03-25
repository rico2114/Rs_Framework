package utils;

/**
 * Created with eclipse 25/03/2015 1:23:13 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class BitMask {

	private int mask;
	
	public BitMask() {
		this(0);
	}
	
	public BitMask(final int mask) {
		this.mask = mask;
	}
	
	public void mask(final int value) {
		mask |= value;
	}
	
	public void unmask(final int value) {
		mask &= ~value;
	}
	
	public void clear() {
		mask = 0;
	}
	
	public boolean areMasksOn(final int ... values) {
		boolean state = true;
		for (int i : values) {
			if (!isMaskOn(i)) {
				state = false;
				break;
			}
		}
		return state;
	}
	
	public boolean isMaskOn(final int value) {
		return (mask & value) == value;
	}
	
	public int getMask() {
		return mask;
	}
}
