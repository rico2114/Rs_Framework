package engine.interfaces;

/**
 * Created with eclipse 11/03/2015 10:43:14 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public interface Repository<T> {

	void add(T source);
	
	void remove(T source);
	
	T get(final int slot);
	
	int getCapacity();
}
