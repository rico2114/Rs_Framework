package engine.interfaces;

/**
 * Created with eclipse 11/03/2015 10:26:00 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public interface Bundle<T> {

	T onCreate();
	
	T onStop();
}
