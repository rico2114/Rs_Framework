package engine;

import engine.interfaces.Bundle;
import engine.interfaces.Task;

/**
 * Created with eclipse 21/03/2015 9:50:34 a. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public interface EngineInterface extends Bundle<Void>, Runnable {

	boolean add(final Task task);
	
	void execute(final Task task);
}
