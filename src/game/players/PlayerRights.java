package game.players;

import utils.BitMask;

/**
 * Created with eclipse 25/03/2015 1:27:12 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class PlayerRights {

	private BitMask mask = new BitMask();
	
	public void setRights(final Rights right) {
		mask.mask(right.getMask());
	}
	
	public void clearRights() {
		mask.clear();
	}
	
	public enum Rights {
		NORMAL(0),
		MOD(2),
		ADMINISTRATOR(4);
		private final int mask;
		
		Rights(final int mask) {
			this.mask = mask;
		}
		
		public int getMask() {
			return mask;
		}
		
		public final boolean greater(final BitMask bitMask) {
			final int mask = bitMask.getMask();
			return mask > this.mask;
		}
	}
}
