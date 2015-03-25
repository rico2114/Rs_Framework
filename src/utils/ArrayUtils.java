package utils;

/**
 * Created with eclipse 11/03/2015 10:55:08 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class ArrayUtils {

	public static final boolean exceedsSize(final int slot, final Object [] array) {
		return slot < 0 || slot >= array.length;
	}
}
